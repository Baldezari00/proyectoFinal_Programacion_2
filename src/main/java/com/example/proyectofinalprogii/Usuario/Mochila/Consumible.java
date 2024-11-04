package com.example.proyectofinalprogii.Usuario.Mochila;
/**
 * todo tipo de comida/bebida que pueda restar o sumar vida.
 * @author Balde
 */
public class Consumible extends Item{
        private int saludRecibida;
        public Consumible(String nombre,int saludRecuperada) {
            super(nombre);
            this.saludRecibida=saludRecuperada;
        }

        @Override
        // retorna la salud que luego será aplicada al jugador
        public int utilizar() {
            return saludRecibida;
        }

        @Override
        public String toString() {
            return super.toString()+"Comida{" +
                    "saludRecuperada=" + saludRecibida +
                    '}';
        }


}
