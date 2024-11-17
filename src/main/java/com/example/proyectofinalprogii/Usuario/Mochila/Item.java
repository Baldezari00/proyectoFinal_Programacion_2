package com.example.proyectofinalprogii.Usuario.Mochila;

import org.json.JSONObject;

import java.util.Objects;

/**
 * clase Item padre.
 * @author Balde
 * @ModifiedBy Fede. Agregue setters, getters, equals y hashcode para poder convertir cada tipo de objeto a json
 * y viceversa, ademas cree un id que comparten todos los objetos para que sea mas facil hacer busquedas futuras
 */
public abstract class Item {
    private static int contadorId = 0;
    private int id;
    private String nombre;

    public Item(String nombre) {
        this.id = contadorId++;
        this.nombre = nombre;
    }

    public abstract int utilizar();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public StringBuilder mostrarItem(){
        return new StringBuilder();
    }
}
