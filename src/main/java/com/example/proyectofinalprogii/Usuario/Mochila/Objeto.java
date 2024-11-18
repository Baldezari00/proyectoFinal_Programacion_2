package com.example.proyectofinalprogii.Usuario.Mochila;
/**
 * Objeto que de momento solo puede ser una llave que contiene un código que abre caminos nuevos
 * depués en clase vemos bien como aplicarlo.
 * @author Balde
 * @ModifiedBy Fede. Agregue setters y getters
 */
public class Objeto extends Item{
    private int codigo;

    public Objeto(String nombre, int codigo) {
        super(nombre);
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public int utilizar() {
        return codigo;
    }

    @Override
    public StringBuilder mostrarItem() {
        return new StringBuilder().append("Objeto: ").append(super.getNombre());
    }

    @Override
    public String toString() {
        return "Objeto{ nombre=" + super.getNombre() +
                " codigo=" + codigo +
                "} ";
    }
}
