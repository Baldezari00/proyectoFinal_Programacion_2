package com.example.proyectofinalprogii.Usuario.Mochila;
/**
 * todo tipo de comida/bebida que pueda restar o sumar vida.
 * @author Balde
 * @ModifiedBy Fede. Agregue setters y getters.
 */
public class Consumible extends Item {
    private int saludRecibida;
    public Consumible(String nombre, int saludRecuperada) {
        super(nombre);
        this.saludRecibida = saludRecuperada;
    }

    public int getSaludRecibida() {
        return saludRecibida;
    }

    public void setSaludRecibida(int saludRecibida) {
        this.saludRecibida = saludRecibida;
    }


    @Override
    // retorna la salud que luego será aplicada al jugador
    public int utilizar() {
            return saludRecibida;
        }
        @Override
        public StringBuilder mostrarItem() {
        StringBuilder consumibleMostrar= new StringBuilder();
        consumibleMostrar.append("Consumible: "+super.getNombre());
        if(saludRecibida<0){
            consumibleMostrar.append(" perdida: "+getSaludRecibida()+"hp");
        }else{
            consumibleMostrar.append(" ganancia: "+getSaludRecibida()+"hp");
        }

        return consumibleMostrar;
    }

    @Override
    public String toString() {
        return "Consumible { nombre=" + super.getNombre() +
                " saludRecibida=" + saludRecibida +
                "} ";
    }
}
