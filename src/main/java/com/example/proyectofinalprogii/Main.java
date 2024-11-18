package com.example.proyectofinalprogii;

import com.example.proyectofinalprogii.ExcepcionesPersonalizadas.ExcJugador.NoItemsException;
import com.example.proyectofinalprogii.Juego.Escenario;
import com.example.proyectofinalprogii.Juego.JavaFXApp;
import com.example.proyectofinalprogii.Juego.Opcion;
import com.example.proyectofinalprogii.Juego.controladorJuego;
import com.example.proyectofinalprogii.OperacionesBasicasJSON.Inicio;
import com.example.proyectofinalprogii.OperacionesBasicasJSON.OperacionLecturaEscritura;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Viejo;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Personaje;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Usuario;
import com.example.proyectofinalprogii.Usuario.Mochila.Consumible;
import com.example.proyectofinalprogii.Usuario.Mochila.Item;
import com.example.proyectofinalprogii.Usuario.Mochila.Mochila;
import com.example.proyectofinalprogii.Usuario.Mochila.Objeto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;

/*
public class Main extends Application {


    // controlador de escenas
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vista-juego.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 720);

        // Controlador de la vista
        controladorJuego controlador = fxmlLoader.getController();
        Usuario usuarioActivo = Inicio.inicio();
        controlador.setJugadorLocal(usuarioActivo);


        // Cierra la ventana JavaFX y regresa al menú de consola
        controlador.setOnCloseRequest(() -> {
            stage.close(); // Cierra la ventana
           // volverAConsola(usuarioActivo); // Regresa al flujo de la consola
        });

        stage.setTitle("Juego de aventura!");
        stage.setScene(scene);
        stage.show();
    }

    private void volverAConsola(Usuario usuarioActivo) {
        Inicio.inicio();
        System.out.println("Regresaste al menú de consola.");
    }
*/


import com.example.proyectofinalprogii.OperacionesBasicasJSON.Inicio;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Usuario;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        // Ejecuta la lógica de inicio de sesión en consola
        Usuario usuario = Inicio.inicio();

        if (usuario != null) {
            // Si el usuario inicia sesión correctamente, lanza la interfaz gráfica
            JavaFXApp.setUsuarioActivo(usuario); // Pasa el usuario activo
            Application.launch(JavaFXApp.class, args);
        } else {
            System.out.println("No se inició sesión. Cerrando programa.");
        }
    }
}


    /**
     * @avisos (agregen lo que quieran)
     * -si cambian código de algún otro lado dejenlo comentado para avisar.
     * -si agregan una función o cambian alguna clase que no hayan creado ustedes, ponganse también como autores
     *  en el lugar donde dice @author, o si quieren pongan @modifiedBy X y lo que modificaron en pocas palabras.
     * -usar jdk 21
     * -no hace falta trabajar sobre este proyecto, si ustedes prefieren creeense el suyo para testear y luego unimos todo.
     *
     * @ideas
     * -para presentar el proyecto cargar todos los escenarios y objetos en archivos json para luego leerlos y convertirlos.
     *  así no manchamos el main y queda más prolijo
     *
     * -
     * @loQueQuieran
     * -
     */
