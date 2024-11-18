package com.example.proyectofinalprogii.Usuario.Manejo_Usuario;

public abstract class Personaje {
    protected int vida;
    protected int vidaTotalGanada;

    public Personaje(int vida) {
        this.vida = vida;
        this.vidaTotalGanada = 0;
    }

    public Personaje(int vida, int vidaTotalGanada) {
        this.vida = vida;
        this.vidaTotalGanada = 0;
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

    public int getVidaTotalGanada() { return vidaTotalGanada; }

    public void sumarVidaTotalGanada(int vidaTotalGanada) { this.vidaTotalGanada += vidaTotalGanada; }
}


