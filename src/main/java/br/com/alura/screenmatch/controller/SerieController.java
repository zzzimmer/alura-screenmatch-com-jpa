package br.com.alura.screenmatch.controller;


import br.com.alura.screenmatch.dto.EpisodioDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping
    public List<SerieDTO> obterSerie() {
        return serieService.obterSerie();
    }

//    @GetMapping("/series/top10")
//    public List<SerieDTO> obterTop10Series(){
//        return serieService.obterTop10Series();
//    }

    @GetMapping("/top10PorAvaliacao")
    public List<SerieDTO> obterTop10SeriesPorAvaliacao() {
        return serieService.obterTop10PorAvaliacao();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obterTop5Series() {
        return serieService.top5();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> lancamento() {
        return serieService.top5Lancamento();
    }

    @GetMapping("/{id}")
    public SerieDTO obterPorId(@PathVariable Long id){
        return serieService.serieById(id);
    }

    @GetMapping("/{id}/temporadas/todas")
        public List<EpisodioDTO> temporadasSerie(@PathVariable Long id){
        return serieService.buscaTemporadas(id);
    }


}
