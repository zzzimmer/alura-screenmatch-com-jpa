package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.ECategoria;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long>{

    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String atores, Double avaliacao);

    List<Serie> findTop10ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(ECategoria genero);

//    List<Serie>

    List<Serie> findSerieByTotalTemporadasLessThanAndAvaliacaoGreaterThan(Integer totalTemporadas, Double avaliacao);

    @Query ("SELECT s FROM Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.avaliacao >= :avaliacao ORDER BY s.avaliacao ASC")
    List<Serie> listarPorTamanhoDeTemporadaEAvaliacao(Integer totalTemporadas, Double avaliacao);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trecho%")
    List<Episodio> buscarEpisodioPorTrecho(String trecho);

//    @Query("SELECT s FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacao ASC LIMIT 10")
//    List<Episodio> top10EpisodiosPorSerie(Serie serie);


    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacao DESC")
    List<Episodio> top10EpisodiosPorSerie(Serie serie, Pageable pageable);

//    @Query(value = """
//  SELECT *
//  FROM episodios e
//  WHERE e.serie_id = :#{#serie.id}
//  ORDER BY e.avaliacao DESC
//  LIMIT 10
//  """, nativeQuery = true)
//    List<Episodio> top10EpisodiosPorSerie(@Param("serie") Serie serie);

}
