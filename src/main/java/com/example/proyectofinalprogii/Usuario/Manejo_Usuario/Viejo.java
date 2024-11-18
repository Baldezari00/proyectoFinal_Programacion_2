package com.example.proyectofinalprogii.Usuario.Manejo_Usuario;



public class Viejo extends Personaje {
    public Viejo() {
        super(80);
    }

    public Viejo(int vida, int vidaTotalGanada) {
        super(vida, vidaTotalGanada);
    }

    @Override
    public int cambiarVida(int cantVidaACambiar) {
        // Si el personaje ya tiene la vida máxima y se intenta curar
        if (vida == 80 && cantVidaACambiar > 0) {
            return 0; // No se puede utilizar el consumible
        }

        // Calcula la nueva vida potencial
        int nuevaVida = vida + cantVidaACambiar;

        // Asegúrate de que no exceda el máximo
        if (nuevaVida > 80) {
            nuevaVida = 80;
        }

        // Asigna la vida actualizada
        super.setVida(nuevaVida);
        super.sumarVidaTotalGanada(cantVidaACambiar);

        return 1; // Se pudo realizar el cambio
    }

    @Override
    public String toString() {
        return "Viejo{" +
                "vida=" + vida +
                '}';
    }
}