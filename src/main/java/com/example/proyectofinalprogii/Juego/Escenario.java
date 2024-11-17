package com.example.proyectofinalprogii.Juego;

import java.util.Objects;

/**
 * Escenario que cuenta con solo 2 opciones y una descripción.
 * @author Balde
 */
public class Escenario {
    private static int contadorId = 0;
    private int idEscenario;
    private String descripcion; // se mostrará al usuario
    private Opcion opcion1;
    private Opcion opcion2;


    public Escenario(String descripcion, Opcion opcion1, Opcion opcion2) {
        this.idEscenario = contadorId++;
        this.descripcion = descripcion;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
    }

    @Override
    public String toString() {
        return "Escenario{" +
                "idEscenario=" + idEscenario +
                ", descripcion='" + descripcion + '\'' +
                ", opcion1=" + opcion1.toString() +
                ", opcion2=" + opcion2.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Escenario escenario)) return false;
        return idEscenario == escenario.idEscenario;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idEscenario);
    }

    public int getIdEscenario() { return this.idEscenario; }

    public void setIdEscenario(int idEscenario) { this.idEscenario = idEscenario; }

    public String getDescripcion() {
        return this.descripcion;
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
