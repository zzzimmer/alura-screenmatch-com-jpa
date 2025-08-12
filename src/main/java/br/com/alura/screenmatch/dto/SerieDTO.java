package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.model.ECategoria;
import br.com.alura.screenmatch.model.Serie;
import jakarta.persistence.*;

public record SerieDTO(
                       Long id,
                       String titulo,Integer totalTemporadas,
                       Double avaliacao, ECategoria genero,
                       String atores,
                       String poster,
                       String sinopse) {

//    public SerieDTO (Serie serie){
//        this.id = serie.getId()
//    }

//    public SerieDTO(Serie serie) {
//        this(serie.getId(), serie.getTitulo(), serie.getTotalTemporadas(), serie.getAvaliacao(), serie.getGenero(), serie.getAtores(), serie.getPoster(), serie.getSinopse());
//    }


}
