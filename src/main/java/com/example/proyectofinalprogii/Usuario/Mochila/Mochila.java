package com.example.proyectofinalprogii.Usuario.Mochila;

import com.example.proyectofinalprogii.ExcepcionesPersonalizadas.ExcJugador.NoItemsException;

import java.util.ArrayList;
import java.util.List;

/**
 * clase génerica que recibe cualquier tipo de Item y permite repetidos.
 * @author Balde
 */

public class Mochila <t extends Item>{
    private List<t> items; // porque List permite repetidos

    public Mochila() {
        this.items = new ArrayList<>();
    }

    public Mochila(List<t> items) {
        this.items = items;
    }

    public void agregarItem(t item){
        items.add(item);
    }
    public void removerItem(t item){
        items.remove(item);
    }

    public StringBuilder mostrarItems() throws NoItemsException {
        StringBuilder stringBuilder = new StringBuilder();
        for(Item i: items){
            stringBuilder.append(i.toString()+"\n"); // voy concatenando los toString de los distintos items
        }
        if(stringBuilder.isEmpty()){
            throw new NoItemsException();
        }

        return stringBuilder;
    }
}
