package com.example.proyectofinalprogii;

import com.example.proyectofinalprogii.ExcepcionesPersonalizadas.ExcJugador.NoItemsException;
import com.example.proyectofinalprogii.Usuario.Mochila.Consumible;
import com.example.proyectofinalprogii.Usuario.Mochila.Item;
import com.example.proyectofinalprogii.Usuario.Mochila.Mochila;
import com.example.proyectofinalprogii.Usuario.Mochila.Objeto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    // controlador de escenas
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vista-juego.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("juego aventura!");
        stage.setScene(scene);
        stage.show();
    }


    // este es el main de toda la vida, no se mareen con lo de arriba tranqui
    public static void main(String[] args) throws NoItemsException {
        //launch(); // método para ejecutar escena (dejar comentado para que no les joda)

        //testing de Usuario/Mochila
        Objeto llave = new Objeto("llave verde",12);
        Consumible cafe = new Consumible("cafe americano",30);
        Consumible comidaConRadiacion = new Consumible("pan erradiado",-10);

        Mochila<Item> mochila = new Mochila<>();
       // mochila.agregarItem(llave);
       // mochila.agregarItem(cafe);
       // mochila.agregarItem(comidaConRadiacion);

        try {
            System.out.println(mochila.mostrarItems());
        }catch (NoItemsException noItemsException){
            System.out.println(noItemsException.getMessage());
        }

        System.out.println("prueba github");


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