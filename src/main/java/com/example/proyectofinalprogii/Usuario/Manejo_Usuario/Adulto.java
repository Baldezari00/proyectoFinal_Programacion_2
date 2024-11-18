package com.example.proyectofinalprogii.Usuario.Manejo_Usuario;

public class Adulto extends Personaje implements CuracionVida {

    public Adulto() {
        super(120);
    }

    public Adulto(int vida, int vidaTotalGanada, int vidaTotalPerdida) { super(vida, vidaTotalGanada, vidaTotalPerdida); }

    //Metodo interfaz
    @Override
    public int CuraRapida() {
        if(cambiarVida(20)==0){
            return 0;
        }
        return 1;
    }

    @Override
    public int cambiarVida(int cantVidaACambiar) {
        // Si el personaje ya tiene la vida máxima y se intenta curar
        if (vida == 120 && cantVidaACambiar > 0) {
            return 0; // No se puede utilizar el consumible
        }

        // Calcula la nueva vida potencial
        int nuevaVida = vida + cantVidaACambiar;

        // Asegúrate de que no exceda el máximo
        if (nuevaVida > 120) {
            nuevaVida = 120;
        }

        // Asigna la vida actualizada
        super.setVida(nuevaVida);

        if (cantVidaACambiar > 0) {
            super.sumarVidaTotalGanada(cantVidaACambiar);
        } else {
            super.sumarVidalTotalPerdida(cantVidaACambiar);
        }
        return 1; // Se pudo realizar el cambio
    }

    @Override
    public String toString() {
        return "Adulto{" +
                "vida=" + vida +
                '}';
    }
}