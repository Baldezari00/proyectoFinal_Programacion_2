package com.example.proyectofinalprogii.Juego;

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

    public void mostrarEscenario(){
        System.out.println("\n------------------------\n");
        System.out.println(descripcion+"\n");
        System.out.println("Opción 1: "+opcion1.getConsecuenciaTitulo());
        System.out.println("Opción 2: "+opcion2.getConsecuenciaTitulo());
        System.out.println("\n------------------------\n");

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Opcion getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(Opcion opcion1) {
        this.opcion1 = opcion1;
    }

    public Opcion getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(Opcion opcion2) {
        this.opcion2 = opcion2;
    }
}
