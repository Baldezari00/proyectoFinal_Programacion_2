package com.example.proyectofinalprogii.Usuario.Manejo_Usuario;



public class Mago extends Personaje implements CuracionEnergia{

    public Mago() {
        super(100);
    }


    //Metodo interfaz
    @Override
    public String CuraRapida() {
        this.vida +=15;
        return "El personaje tiene " + super.getVida()+"hp\n";
    }



}