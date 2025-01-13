package com.aluracursos.songpop.repository;

import com.aluracursos.songpop.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
    Optional<Musica> findByCancionContainingIgnoreCase(String buscarCancion);

    Musica findByCancion(String nombreCancion);
}
