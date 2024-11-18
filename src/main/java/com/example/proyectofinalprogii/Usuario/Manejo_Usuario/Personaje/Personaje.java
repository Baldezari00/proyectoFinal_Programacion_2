package com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Personaje;

public abstract class Personaje {
    protected int vida;
    protected int vidaTotalGanada;
    protected int vidaTotalPerdida;

    public Personaje(int vida) {
        this.vida = vida;
        this.vidaTotalGanada = 0;
        this.vidaTotalPerdida = 0;
    }

    public Personaje(int vida, int vidaTotalGanada, int vidaTotalPerdida) {
        this.vida = vida;
        this.vidaTotalGanada = vidaTotalGanada;
        this.vidaTotalPerdida = vidaTotalPerdida;
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

    public int getVidaTotalPerdida() { return vidaTotalPerdida; }

    public void sumarVidaTotalGanada(int vidaTotalGanada) { this.vidaTotalGanada += vidaTotalGanada; }

    public void sumarVidalTotalPerdida(int vidaTotalPerdida) { this.vidaTotalPerdida -= vidaTotalPerdida; }
}


