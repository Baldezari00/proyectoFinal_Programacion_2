package com.example.proyectofinalprogii.Usuario.Mochila;
/**
 * clase Item padre.
 * @author Balde
 */
public abstract class Item {
    private String nombre;

    public Item(String nombre) {
        this.nombre = nombre;
    }

    public abstract int utilizar();

    @Override
    public String toString() {
        return "Item{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
