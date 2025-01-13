package com.aluracursos.songpop.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "artista")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String nombreArtista;
    private String nacionalidad;
    private Integer edad;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Musica> canciones;

    public Artista(){}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public List<Musica> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Musica> canciones) {
        this.canciones = canciones;
    }

    @Override
    public String toString() {
        return "Artista: " + nombreArtista +
                ", Nacionalidad: " + nacionalidad +
                ", Edad: " + edad;
    }
}
