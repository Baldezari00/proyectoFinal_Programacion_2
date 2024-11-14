package com.example.proyectofinalprogii.Juego;

import com.example.proyectofinalprogii.Juego.ConsecuenciasEnum.ConsecuenciaMala;

/**
 * Escenario que cuenta con solo 2 opciones y una descripción.
 * @author Balde
 */
public class Escenario {
    private String descripcion; // se mostrará al usuario
    private Opcion opcion1;
    private Opcion opcion2;


    public Escenario(String descripcion, Opcion opcion1, Opcion opcion2) {
        this.descripcion = descripcion;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
    }

    public void accionEscenario(){
        // recibiría el jugador por parametro y luego determinaría, según la
        // opción elegiga, la consecuencia.
        // (recordar que una opción solo puede tener una sola consecuencia)

    }
}
