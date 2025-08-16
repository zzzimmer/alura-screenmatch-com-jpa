package br.com.alura.screenmatch.service;


import br.com.alura.screenmatch.dto.EpisodioDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.ECategoria;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    private List<SerieDTO> conversor (List<Serie> serieList){
     return  serieList.stream().map(s -> new SerieDTO(s.getId(), s.getTitulo(),
                s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(),
             s.getPoster(), s.getSinopse())).collect(Collectors.toList());
    }

    public List<SerieDTO> obterSerie (){
        return conversor(serieRepository.findAll());
    }

    public List<SerieDTO> obterTop10PorAvaliacao(){
        return conversor(serieRepository.findTop10ByOrderByAvaliacaoDesc());
    }

    public List<SerieDTO> top5(){
        return conversor(serieRepository.findTop5ByOrderByAvaliacaoDesc());
    }

    public List<SerieDTO> top5Lancamento() {
        Pageable pageable = PageRequest.of(0, 5);
        return conversor(serieRepository.findTop5ByOrderByEpisodiosDataLancamentoDesc(pageable));
    }

    public SerieDTO serieById(Long id){
        Optional<Serie> serie = serieRepository.findSerieById(id);
        if (serie.isPresent()){
            Serie s = serie.get();
            return new SerieDTO(s.getId(), s.getTitulo(),
                    s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(),
                    s.getPoster(), s.getSinopse());
        }
        return null; // segundo o curso, o front end acerta isso aqui
    }

    public List<EpisodioDTO> buscaTemporadas (Long id){
        Optional<Serie> serie = serieRepository.findSerieById(id);
        if (serie.isPresent()){
            Serie s = serie.get();
            return s.getEpisodios().stream().map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo()
            , e.getNumeroEpisodio())).collect(Collectors.toList());
        }
        return null; // segundo o curso, o front end acerta isso aqui
    }


    public List<EpisodioDTO> buscaTemporadasPorNumero(Long id, Long temp_id) {
        return serieRepository.obterEpisodiosPorTemporada(id, temp_id)
                .stream().map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo()
                , e.getNumeroEpisodio())).collect(Collectors.toList());


    }

    public List<SerieDTO> obterSeriesPorCategoria(String categoriaNome) {
        ECategoria eCategoria = ECategoria.fromString(categoriaNome);
        return conversor(serieRepository.findSerieByGenero(eCategoria));

//        return serieRepository.obterSeriesPorCategoria(categoriaNome).stream().map(s -> new SerieDTO(s.getId(), s.getTitulo(),
//                s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(),
//                s.getPoster(), s.getSinopse())).collect(Collectors.toList());
    }

    public List<EpisodioDTO> obterTop5PorSerie(Long id) {
            return serieRepository.findSerieById(id)
                    .map(s -> s.getEpisodios().stream()
                            .filter(e -> e.getAvaliacao() != null)
                            .sorted(Comparator.comparing(Episodio::getAvaliacao).reversed())
                            .limit(5)
                            .map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
                            .toList()
                    )
                    .orElse(Collections.emptyList());
    }
}
