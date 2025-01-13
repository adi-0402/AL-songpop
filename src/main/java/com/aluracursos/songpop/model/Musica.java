package com.aluracursos.songpop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musica")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cancion;
    private Integer anoLanzamiento;

    @ManyToOne
    @JoinColumn(name ="artista_id", nullable = false)
    private Artista artista;

    public Musica(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCancion() {
        return cancion;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public Integer getAnoLanzamiento() {
        return anoLanzamiento;
    }

    public void setAnoLanzamiento(Integer anoLanzamiento) {
        this.anoLanzamiento = anoLanzamiento;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }


    @Override
    public String toString(){
        return "Canción: " + cancion +
                ", Artista: " + (artista != null ? artista.getNombreArtista() : "N/A") +
                ", Año de Lanzamiento: " + anoLanzamiento;
    }
}
