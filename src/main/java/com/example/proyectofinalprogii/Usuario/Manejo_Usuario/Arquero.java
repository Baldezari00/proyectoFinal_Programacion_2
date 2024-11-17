package com.example.proyectofinalprogii.Usuario.Manejo_Usuario;

public class Arquero extends Personaje implements CuracionEnergia{

    public Arquero(int ID, String nombre) {
        super(ID, nombre, 120);
    }


    //Metodo interfaz
    @Override
    public String CuraRapida() {
            this.vida +=15;
            return "El personaje " + getNombre() + " tiene : " + super.getVida()+"hp\n";
    }


    @Override
    public String toString() {
        return "Arquero{" + super.toString() +
                "} ";
    }
}
