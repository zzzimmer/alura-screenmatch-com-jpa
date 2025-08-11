package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.model.ECategoria;
import jakarta.persistence.*;

public record SerieDTO(
                       Long id,
                       String titulo,Integer totalTemporadas,
                       Double avaliacao, ECategoria genero,
                       String atores,
                       String poster,
                       String sinopse) {


}
