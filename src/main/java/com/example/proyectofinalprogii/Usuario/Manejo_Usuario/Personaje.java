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

    public void setVida(int vida) {
        this.vida = vida;
    }

    public abstract int cambiarVida(int cantVidaACambiar);


}


