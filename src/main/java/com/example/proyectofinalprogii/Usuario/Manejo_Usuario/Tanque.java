package com.example.proyectofinalprogii.Usuario.Manejo_Usuario;

public class Tanque extends Personaje{

    public Tanque(int ID, String nombre) {
        super(150);
    }

    @Override
    public String toString() {
        return "Tanque{" + super.toString() +
                "} ";
    }
}
