package com.example.proyectofinalprogii.Usuario.Mochila;

import com.example.proyectofinalprogii.ExcepcionesPersonalizadas.ExcJugador.NoItemsException;

import java.util.ArrayList;
import java.util.List;

/**
 * clase génerica que recibe cualquier tipo de Item y permite repetidos.
 * @author Balde
 * @ModifiedBy Fede. Agregue el getItems que para que luego las clases que utilicen mochila puedan retornar
 * todo lo que la mochila contenga
 */

public class Mochila <T extends Item>{
    private List<T> items; // porque List permite repetidos

    public Mochila() {
        this.items = new ArrayList<>();
    }

    public Mochila(List<T> items) {
        this.items = items;
    }

    public void agregarItem(T item){
        items.add(item);
    }

    public void removerItem(T item){
        items.remove(item);
    }

    public List<T> getItems() {
        return this.items;
    }

    public StringBuilder mostrarItems() throws NoItemsException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Item i: items) {
            stringBuilder.append(i.toString()+"\n"); // voy concatenando los toString de los distintos items
        }
        if (stringBuilder.isEmpty()) {
            throw new NoItemsException();
        }

        return stringBuilder;
    }
}
