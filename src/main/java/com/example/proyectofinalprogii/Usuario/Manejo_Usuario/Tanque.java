package com.example.proyectofinalprogii.Usuario.Manejo_Usuario;

public class Tanque extends Personaje{

    public Tanque(String nombre) {
        super(nombre, 150);
    }

    @Override
    public String toString() {
        return "Tanque{" + super.toString() +
                "} ";
    }
}
