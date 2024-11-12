package com.example.proyectofinalprogii.Usuario.Manejo_Usuario;

public abstract class Personaje {
    // TRABAJO DE MATI:
    // crear los 3 personajes necesarios junto con clase padre (esta misma), sus atributos y demás.
    // 1 interfaz que la utilicen 2 de los personajes
    // extra: seri  alizar personajes (convertir personaje a JSON y viceversa. Sin archivo y dentro de la misma clase).
    private static int contarId = 0;
    private int ID;
    private String Nombre;
    protected int vida;

    public Personaje(int ID, String nombre, int vida) {
        this.ID = contarId++;
        this.Nombre = nombre;
        this.vida = vida;
    }

    public int getID() {
        return ID;
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



    public abstract String Ataque();
    public abstract String RecibirDanio(int danio);

    @Override
    public String toString() {
        return "Personaje{" +
                "ID=" + ID +
                ", Nombre='" + Nombre + '\'' +
                ", vida=" + vida +
                ' ';
    }
}
