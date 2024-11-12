package com.example.proyectofinalprogii.Usuario.Manejo_Usuario;

public class Tanque extends Personaje{
    private int defensa;

    public Tanque(int ID, String nombre, int vida, int defensa) {
        super(ID, nombre, 150);
        this.defensa = 10;
    }

    public int getDefensa() {
        return defensa;
    }
//Metodos heredados
    @Override
    public String Ataque() {
        return "El personaje " + getNombre() + " realiza un ataque";
    }

    // Si la consecuencia es mala, recibe daño por parametro para asi mostrar la vida actual que queda
    //polimorfismo del metodo RecibirDanio
    @Override
    public String RecibirDanio(int danio) {
        int danioReducido = danio - this.defensa; // el tanque tendra un atributo def para reducir el daño
        this.vida -= (danioReducido > 0) ? danioReducido : 0;
        if (this.vida < 0) {
            this.vida = 0;
        }
        return "El personaje " + getNombre() + " sufre " + danioReducido + " de daño la salud actual es " + this.vida;
    }

    @Override
    public String toString() {
        return "Tanque{" + super.toString() +
                "defensa=" + defensa +
                "} ";
    }
}
