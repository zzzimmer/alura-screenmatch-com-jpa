package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.ECategoria;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {


    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacao DESC")
    List<Episodio> top10EpisodiosPorSerie(Serie serie, Pageable pageable);

    List<Serie> findTop10ByOrderByAvaliacaoDesc();

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    //precisa ficar atento, essa query nativa do spring faz coisas estranhas
    List<Serie> findTop5ByOrderByEpisodiosDataLancamentoDesc();

    @Query("SELECT s FROM Serie s JOIN s.episodios e GROUP BY s " +
        "ORDER BY MAX(e.dataLancamento) DESC")
    List<Serie> findTop5ByOrderByEpisodiosDataLancamentoDesc(Pageable pageable);

    Optional<Serie> findSerieById(Long id);

    Optional<Serie> findById(Long id);


    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.id = :id AND e.temporada = :temp_id ")
    List<Episodio> obterEpisodiosPorTemporada(Long id, Long temp_id);



    List<Serie> findSerieByGenero(ECategoria genero);
}
//    @Query("SELECT s FROM Serie s WHERE s.genero = :categoriaNome")
//    List<Serie> obterSeriesPorCategoria(String categoriaNome);
