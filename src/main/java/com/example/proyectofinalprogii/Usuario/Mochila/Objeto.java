package com.example.proyectofinalprogii.Usuario.Mochila;
/**
 * Objeto que de momento solo puede ser una llave que contiene un código que abre caminos nuevos
 * depués en clase vemos bien como aplicarlo.
 * @author Balde
 */
public class Objeto extends Item{
    private int codigo;
    public Objeto(String nombre,int codigo) {
        super(nombre);
        this.codigo = codigo;
    }

    @Override
    public int utilizar() {
        return codigo;
    }

    @Override
    public String toString() {
        return super.toString()+"Objeto{" +
                "codigo=" + codigo +
                '}';
    }
}
