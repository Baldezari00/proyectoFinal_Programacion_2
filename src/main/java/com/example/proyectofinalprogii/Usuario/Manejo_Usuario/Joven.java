package com.example.proyectofinalprogii.Usuario.Manejo_Usuario;

public class Joven extends Personaje implements CuracionVida {
    public Joven() {
        super(150);
    }

    public Joven(int vida) { super(vida); }

    @Override
    public String CuraRapida() {
        super.cambiarVida(15);
        return "El personaje ahora tiene " + super.getVida() + "hp";
    }
}