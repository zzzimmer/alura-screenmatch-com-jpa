package br.com.alura.screenmatch;

import br.com.alura.screenmatch.principal.Principal;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication  {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);

//		List<Integer> lista = new ArrayList<>();
//
//		for (int i = 0; i < 101; i++){
//			lista.add(i);
//		}
//
//		lista.stream().forEach(i -> {
//			if (i%15 == 0){
//				System.out.println("FizBuzz");
//			} else if (i%5 == 0){
//				System.out.println("Buzz");
//			} else if (i%3 == 0) {
//				System.out.println("Fiz");
//			} else {
//				System.out.println(i);
//			}
//		});


	}




}
