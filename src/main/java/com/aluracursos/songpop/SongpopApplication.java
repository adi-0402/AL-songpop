package com.aluracursos.songpop;

import com.aluracursos.songpop.principal.Principal;
import com.aluracursos.songpop.repository.ArtistaRepository;
import com.aluracursos.songpop.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SongpopApplication implements CommandLineRunner {
	@Autowired
	private MusicaRepository musicaRepository;

	@Autowired
	private ArtistaRepository artistaRepository;

	public static void main(String[] args) {
		SpringApplication.run(SongpopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		Principal principal = new Principal(musicaRepository, artistaRepository);
		principal.mostrarMenu();
	}
}
