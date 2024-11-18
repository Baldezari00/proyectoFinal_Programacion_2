package com.example.proyectofinalprogii;

import com.example.proyectofinalprogii.ExcepcionesPersonalizadas.ExcJugador.NoItemsException;
import com.example.proyectofinalprogii.Juego.Escenario;
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

public class Main extends Application {

    // controlador de escenas
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vista-juego.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);


        // crear instancias aquí que necesiten ser transmitidas al controlador

        //testing de Usuario/Mochila
        Objeto llave = new Objeto("llave verde",12);
        Consumible cafe = new Consumible("cafe americano",30);
        Consumible comidaConRadiacion = new Consumible("pan erradiado",-10);

        Mochila<Item> mochila = new Mochila<>();
        mochila.agregarItem(llave);
        mochila.agregarItem(cafe);
        // mochila.agregarItem(comidaConRadiacion);

        try {
            System.out.println(mochila.mostrarItems());
        } catch (NoItemsException noItemsException){
            System.out.println(noItemsException.getMessage());
        }


        // escenario
//        Opcion opcion1= new Opcion("Revisar casa", 40);
//        Opcion opcion2= new Opcion("Seguir el camino", -10);
//        Escenario escenario = new Escenario("te encontras perdido en una especie de bosque con niebla, ves una casa a lo lejos y un camino formado al otro lado... como si alguien ya hubiese pasado por ahí",opcion1,opcion2);
//
//
//        Opcion opcion3= new Opcion("a", 20);
//        Opcion opcion4= new Opcion("b", -20);
//        Escenario escenario1 = new Escenario("abc",opcion3,opcion4);
//
//        Opcion opcion5= new Opcion("a", 25);
//        Opcion opcion6= new Opcion("b", -25);
//        Escenario escenario2 = new Escenario("ert",opcion5,opcion6);
//        //jugador
//        Personaje mago = new Viejo();
//        Usuario jugador = new Usuario("balde","1234", mago);
//        jugador.setPersonajeElegido(mago);
//        jugador.agregarEscenario(escenario);
//        jugador.agregarEscenario(escenario1);
//        jugador.agregarEscenario(escenario2);

        Usuario jugador = Inicio.inicio();
        jugador.setMochila(mochila);
        // controlador
        controladorJuego controlador = fxmlLoader.getController();
        controlador.setJugadorLocal(jugador);
        controlador.setStage(stage);

        stage.setTitle("juego aventura!");
        stage.setScene(scene);
        stage.show();
    }


    // este es el main de toda la vida, no se mareen con lo de arriba tranqui
    public static void main(String[] args) {

      launch();
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
}