package com.example.proyectofinalprogii.Usuario.Manejo_Usuario;

public class Arquero extends Personaje implements CuracionEnergia{
    private int energia;

    public Arquero(int ID, String nombre) {
        super(ID, nombre, 120);
        this.energia = 5;
    }

    public int getEnergia() {
        return energia;
    }

    //Metodos Heredados
   /* @Override
    public String Ataque() {
        return "El personaje " + getNombre() + " realiza un ataque";
    }
    // Si la consecuencia es mala, recibe daño por parametro para asi mostrar la vida actual que queda
    @Override
    public String RecibirDanio(int danio) {
        this.vida -= danio;
        if (this.vida < 0) {
            this.vida = 0;
        }
        return "El personaje " + getNombre() + " sufre " + danio + " de daño, la salud actual es " + this.vida;
    }
*/

    //Metodo interfaz
    @Override
    public String CuraRapida() {
        if (this.energia>0) {
            this.vida +=15;
            this.energia--;
            return "El personaje " + getNombre() + " ah usado energia para curarse \n Energia actual: " + getEnergia();
        } else { // se podria crear una excepcion para esto
            return "No se tiene energia suficiente !!";
        }
    }


    @Override
    public String toString() {
        return "Arquero{" + super.toString() +
                "energia=" + energia +
                "} ";
    }
}
