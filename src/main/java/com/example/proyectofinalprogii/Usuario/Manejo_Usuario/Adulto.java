package com.example.proyectofinalprogii.Usuario.Manejo_Usuario;

public class Adulto extends Personaje implements CuracionVida {

    public Adulto() {
        super(120);
    }

    public Adulto(int vida) { super(vida); }

    //Metodo interfaz
    @Override
    public String CuraRapida() {
        super.cambiarVida(10);
        return "El personaje ahora tiene : " + super.getVida() + "hp";
    }

    @Override
    public String toString() {
        return "Adulto {" + super.toString() + "}";
    }
}