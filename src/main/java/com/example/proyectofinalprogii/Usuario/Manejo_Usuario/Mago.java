package com.example.proyectofinalprogii.Usuario.Manejo_Usuario;



public class Mago extends Personaje implements CuracionEnergia{

    public Mago(String nombre) {
        super(nombre, 100);
    }


//Metodo interfaz
    @Override
    public String CuraRapida() {
            this.vida +=15;
            return "El personaje " + getNombre() + " tiene "+super.getVida()+"hp\n";
    }

    @Override
    public String toString() {
        return "Mago{" + super.toString() +
                "} " ;
    }

}
