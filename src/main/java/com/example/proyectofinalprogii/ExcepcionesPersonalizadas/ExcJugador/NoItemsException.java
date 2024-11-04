package com.example.proyectofinalprogii.ExcepcionesPersonalizadas.ExcJugador;

public class NoItemsException extends Exception{
    public NoItemsException() {}

    @Override
    public String getMessage() {
        return "\nno hay items para mostrar\n";
    }
}
