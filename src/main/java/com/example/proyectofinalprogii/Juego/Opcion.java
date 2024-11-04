package com.example.proyectofinalprogii.Juego;

import com.example.proyectofinalprogii.Juego.ConsecuenciasEnum.ConsecuenciaBuena;
import com.example.proyectofinalprogii.Juego.ConsecuenciasEnum.ConsecuenciaMala;
/**
 * Opcion que solo puede tener una consecuencia (reestringido en los constructores).
 * @author Balde
 */
public class Opcion {

    private String consecuenciaTitulo; // será visible para el usuario. ej: "investigar bosque".
    private ConsecuenciaBuena consecuenciaBuena;
    private ConsecuenciaMala consecuenciaMala;

    public Opcion( String consecuenciaTitulo, ConsecuenciaBuena consecuenciaBuena) {
        this.consecuenciaTitulo = consecuenciaTitulo;
        this.consecuenciaBuena = consecuenciaBuena;
    }

    public Opcion( String consecuenciaTitulo, ConsecuenciaMala consecuenciaMala) {
        this.consecuenciaTitulo = consecuenciaTitulo;
        this.consecuenciaMala = consecuenciaMala;
    }

    public String Getconsecuencia(){
        return consecuenciaTitulo;
    }



}
