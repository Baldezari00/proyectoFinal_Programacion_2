package com.example.proyectofinalprogii.Usuario.Manejo_Usuario;

public class Arquero extends Personaje implements CuracionEnergia {

    public Arquero() {
        super(120);
    }

    //Metodo interfaz
    @Override
    public String CuraRapida() {
        this.vida += 15;
        return "El personaje " + " tiene : " + super.getVida() + "hp\n";
    }


}