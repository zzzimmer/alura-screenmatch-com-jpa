package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String NUM_API_KEY = System.getenv("API_IMDB");// para evitar refatorar
    private final String API_KEY = "&apikey="+NUM_API_KEY;
    private List<DadosSerie> dadosSeries = new ArrayList<>();

    private SerieRepository serieRepository;
    private List<Serie> series = new ArrayList<>();

    private Optional<Serie> serieGlobal;

    public Principal(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public void exibeMenu() {
        var opcao = -1;
        int i = 0;
        while (opcao !=0) {
            var menu = """
                    1 - Adicionar série ao banco de dados
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                    4 - Buscar séries por título
                    5 - Buscar séries por ator
                    6 - Buscar top 10
                    7 - Busca por categoria
                    
                    0 - Sair                                 
                    """;

            System.out.println(menu);
//            System.out.println("DEBUG"+i);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 4:
                    buscarSeriePorTitulo();
                    break;
                case 5:
                    buscarSeriePorAtor();
                    break;
                case 6:
                    buscarTop5Series();
                    break;
                case 7:
                    buscarSeriesPorCategoria();
                    break;
                case 8:
                    buscarSeriesPorTemporadaEAvaliacao();
                    break;
                case 9:
                    buscarEpisodioPorTrecho();
                case 10:
                    top10MelhoresEpisodiosSerie();
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }




    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        Serie serie = new Serie(dados);
        serieRepository.save(serie);
//        dadosSeries.add(dados);

        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie(){
        listarSeriesBuscadas();
        System.out.println("Escolha uma série pelo nome");
        String nomeSerie = leitura.nextLine();
//        String nomeSerie = leitura.next(); ISSO DA ERRO

//        series.stream().filter(serie -> serie.getTitulo().toLowerCase() == nomeSerie.toLowerCase());

//        Optional<Serie> first = series.stream().filter(s -> s.getTitulo().toLowerCase()
//                .contains(nomeSerie.toLowerCase())).findFirst();

        Optional<Serie> first = serieRepository.findByTituloContainingIgnoreCase(nomeSerie);

        if (first.isPresent()){
            Serie serieEncontrada = first.get();
            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {

            var json = consumo.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
            temporadas.forEach(System.out::println);

            List <Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map( e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);
            serieRepository.save(serieEncontrada);
        } else {
            System.out.println("Série não encontrada");
        }

    }

    private void listarSeriesBuscadas(){
        series = serieRepository.findAll();
        series.stream().sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    private void buscarSeriePorTitulo() {
//        listarSeriesBuscadas();
        System.out.println("Escolha uma série pelo nome");
        String nomeSerie = leitura.nextLine();

        serieGlobal = serieRepository.findByTituloContainingIgnoreCase(nomeSerie);

        if (serieGlobal.isPresent()){
            System.out.println("Dados da série: " + serieGlobal.get());
        } else {
            System.out.println("Não encontrada");
        }
    }

    private void buscarSeriePorAtor(){

        System.out.println("Digite o nome para busca: ");
        String nomeAtor = leitura.nextLine();
        System.out.println("Avaliações a partir de que valor?");
        var avaliação = leitura.nextDouble();
        List<Serie> seriesEncontradas = serieRepository.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor,avaliação);
        System.out.println(seriesEncontradas);
        System.out.println("Series que "+ nomeAtor+ " trabalhou: ");
        seriesEncontradas.forEach(s -> System.out.println(s.getTitulo() + " avaliação: " + s.getAvaliacao()) );

    }

    private void buscarTop5Series(){
        List<Serie> seriesTop5 = serieRepository.findTop10ByOrderByAvaliacaoDesc();
        seriesTop5.forEach(s -> System.out.println(s.getTitulo() + " Avaliação: " + s.getAvaliacao()));
    }

    private void buscarSeriesPorCategoria(){
        System.out.println("Qual categoria?");
        var nomeGenero = leitura.nextLine();
        ECategoria categoria = ECategoria.fromPortugues(nomeGenero);
        List<Serie> retorno = serieRepository.findByGenero(categoria);
        retorno.forEach(System.out::println);

    }

    private void buscarSeriesPorTemporadaEAvaliacao(){
        System.out.println("Quantas temporadas no máximo?");
        var totalTemporadas = leitura.nextInt();
        System.out.println("Avaliacao minima: ");
        var minAval = leitura.nextDouble();
//        List<Serie> retorno = serieRepository.findSerieByTotalTemporadasLessThanAndAvaliacaoGreaterThan(totalTemporadas, minAval);
        List<Serie> retorno = serieRepository.listarPorTamanhoDeTemporadaEAvaliacao(totalTemporadas, minAval);
        retorno.forEach(System.out::println);
    }

    private void buscarEpisodioPorTrecho() {
        System.out.println("Que trecho se deseja buscar? ");
        var trecho = leitura.nextLine();

        List<Episodio> retorno = serieRepository.buscarEpisodioPorTrecho(trecho);
        retorno.forEach(System.out::println);
    }

    private void top10MelhoresEpisodiosSerie(){
        Pageable pageable = PageRequest.of(0, 10);
        buscarSeriePorTitulo();
        if (serieGlobal.isPresent()){
            Serie serie = serieGlobal.get();
            List<Episodio> topEpisodios = serieRepository.top10EpisodiosPorSerie(serie, pageable);
            topEpisodios.forEach(System.out::println);
        }

    }

//    {
//
//        1 - Crie uma consulta que retorne os produtos com preço maior que um valor
//
//        2 - Crie uma consulta que retorne os produtos ordenados pelo preço crescente.
//
//        3 - Crie uma consulta que retorne os produtos ordenados pelo preço decrescente.
//
//        4 - Crie uma consulta que retorne os produtos que comecem com uma letra específica.
//
//        5 - Crie uma consulta que retorne os pedidos feitos entre duas datas.
//
//        6 - Crie uma consulta que retorne a média de preços dos produtos.
//
//        7 - Crie uma consulta que retorne o preço máximo de um produto em uma categoria
//
//        8 - Crie uma consulta para contar o número de produtos por categoria.
//
//        9 - Crie uma consulta para filtrar categorias com mais de 10 produtos.
//
//        10 - Crie uma consulta para retornar os produtos filtrados por nome ou por categoria.
//
//        11 - Crie uma consulta nativa para buscar os cinco produtos mais caros

    // Aula 5 - Atividade 11
//
//    }
}
