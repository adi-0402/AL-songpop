package com.aluracursos.songpop.principal;

import com.aluracursos.songpop.model.Artista;
import com.aluracursos.songpop.model.Musica;
import com.aluracursos.songpop.repository.ArtistaRepository;
import com.aluracursos.songpop.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final ArtistaRepository artistaRepository;
    private final MusicaRepository musicaRepository;

    @Autowired
    public Principal(MusicaRepository musicaRepository,
                     ArtistaRepository artistaRepository){
        this.musicaRepository = musicaRepository;
        this.artistaRepository = artistaRepository;
    }

    public void mostrarMenu(){
        System.out.println("Bienvenido a SongPop!");

        var opcion = -1;
        while (opcion != 0){
            var menu = """
                        Elije una opcion:
                        1 - Registrar datos de cantantes
                        2 - Registrar datos de canciones
                        3 - Buscar canciones por cantante
                        4 - Buscar canciones por titulo
                        5 - Mostrar canciones
                        6 - Mostrar artistas
      
                        0 - Salir
                        """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    registrarArtista();
                    break;
                case 2:
                    registrarCancion();
                    break;
                case 3:
                    cancionPorArtista();
                    break;
                case 4:
                    cancionPorTitulo();
                    break;
                case 5:
                    mostrarCanciones();
                    break;
                case 6:
                    mostrarArtistas();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción Invalida");
            }
        }
    }

    private Artista registrarArtista() {

        System.out.println("Escriba el nombre del artista: ");
        var nombreArtista = teclado.nextLine();

        Artista artistaExistente = artistaRepository.findByNombreArtista(nombreArtista);

        if (artistaExistente != null) {
            System.out.println("El artista '" + nombreArtista + "' ya está registrado.");
            return artistaExistente;
        }

        System.out.println("Escriba la nacionalidad del artista: ");
        var nacionalidad = teclado.nextLine();

        System.out.println("Escriba la edad del artista: ");
        var edad = teclado.nextInt();
        teclado.nextLine();

        Artista artista = new Artista();
        artista.setNombreArtista(nombreArtista);
        artista.setNacionalidad(nacionalidad);
        artista.setEdad(edad);

        artistaRepository.save(artista);
        return artista;
    }



    private void registrarCancion() {
        System.out.println("Escriba el nombre de la canción: ");
        var nombreCancion = teclado.nextLine();

        Musica musicaExistente = musicaRepository.findByCancion(nombreCancion);

        if (musicaExistente != null) {
            System.out.println("La cancion '" + nombreCancion + "' ya está registrado.");
        } else {
            System.out.println("Escriba el año de lanzamiento: ");
            var anoLanzamiento = teclado.nextInt();
            teclado.nextLine();

            System.out.println("Escriba el nombre del artista:");
            var nombreArtista = teclado.nextLine();

            Artista artista = artistaRepository.findByNombreArtista(nombreArtista);

            if (artista == null) {
                System.out.println("Artista no encontrado. Creando un nuevo artista...");
                artista = registrarArtista();
            }

            Musica registro = new Musica();
            registro.setCancion(nombreCancion);
            registro.setArtista(artista);
            registro.setAnoLanzamiento(anoLanzamiento);

            musicaRepository.save(registro);
        }
    }

    private void cancionPorArtista() {
        System.out.println("Escriba el cantante a buscar: ");
        var buscarArtista = teclado.nextLine();

        Optional<Artista> artistaBuscada = artistaRepository.findByNombreArtistaContainingIgnoreCase(buscarArtista);
        if (artistaBuscada.isPresent()){
            System.out.println("Datos del cantante: " + artistaBuscada.get());
        } else {
            System.out.println("Cantante no encontrado");
        }
    }

    private void cancionPorTitulo() {
        System.out.println("Escriba la cancion a buscar: ");
        var buscarCancion = teclado.nextLine();

        Optional<Musica> cancionBuscada = musicaRepository.findByCancionContainingIgnoreCase(buscarCancion);
        if (cancionBuscada.isPresent()){
            System.out.println("Datos de la cancion: " + cancionBuscada.get());
        } else {
            System.out.println("Cancion no encontrada");
        }
    }

    private void mostrarCanciones() {
        List<Musica> canciones = musicaRepository.findAll();
        canciones.stream()
                .sorted((a, b) -> a.getCancion().compareToIgnoreCase(b.getCancion()))
                .forEach(System.out::println);
    }

    private void mostrarArtistas() {
        List<Artista> artistas = artistaRepository.findAll();
        artistas.stream()
                .sorted((a, b) -> a.getNombreArtista().compareToIgnoreCase(b.getNombreArtista()))
                .forEach(System.out::println);
    }


}
