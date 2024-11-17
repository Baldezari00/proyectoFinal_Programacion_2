package com.example.proyectofinalprogii.Usuario.Manejo_Usuario;

public abstract class Personaje {
    // TRABAJO DE MATI:
    // crear los 3 personajes necesarios junto con clase padre (esta misma), sus atributos y demás.
    // 1 interfaz que la utilicen 2 de los personajes
    // extra: serializar personajes (convertir personaje a JSON y viceversa. Sin archivo y dentro de la misma clase).

    private String Nombre;
    protected int vida;

    public Personaje(String nombre, int vida) {
        this.Nombre = nombre;
        this.vida = vida;
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getVida() {
        return vida;
    }
    public String getVidaString() {
        return String.valueOf(vida);
    }

    public void cambiarVida(int cantVidaACambiar) {
        this.vida = this.vida + (cantVidaACambiar);
    }

    @Override
    public String toString() {
        return "Personaje{" +
                ", Nombre='" + Nombre + '\'' +
                ", vida=" + vida +
                ' ';
    }


}
