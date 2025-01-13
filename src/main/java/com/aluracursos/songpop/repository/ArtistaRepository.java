package com.aluracursos.songpop.repository;

import com.aluracursos.songpop.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Artista findByNombreArtista(String nombreArtista);

    Optional<Artista> findByNombreArtistaContainingIgnoreCase(String buscarArtista);
}
