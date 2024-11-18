package com.example.proyectofinalprogii.Usuario.Manejo_Usuario;

public abstract class Personaje {
    protected int vida;

    public Personaje(int vida) {
        this.vida = vida;
    }

    public int getVida() {
        return vida;
    }

    public String getVidaString(){
        return String.valueOf(vida);
    }

    public void cambiarVida(int cantVidaACambiar) {
        this.vida += cantVidaACambiar;
    }

    @Override
    public String toString() {
        return "vida=" + vida;
    }
}


