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
    // que tenga capacidad maxima
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

        if (items.isEmpty()) {  // Verificamos si la lista de items está vacía antes de empezar a agregar texto
            throw new NoItemsException();
        }

        for (Item i : items) {
            stringBuilder.append(i.toString()).append("\n");  // Concatenamos el toString de cada item
        }

        return stringBuilder;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mochila{");
        sb.append("items=").append(items);  // Imprime la lista de items
        sb.append('}');
        return sb.toString();
    }
}
