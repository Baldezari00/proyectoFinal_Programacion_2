package com.example.proyectofinalprogii.Juego;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Jugador;
import com.example.proyectofinalprogii.Usuario.Mochila.Item;

/**
 * Opcion que solo puede tener una consecuencia (reestringido en los constructores).
 * @author Balde
 */
public class Opcion {

    private String consecuenciaTitulo; // será visible para el usuario. ej: "investigar bosque".
    private int vidaAModificar;
    private Item itemGanado;




    public String Getconsecuencia(){
        return consecuenciaTitulo;
    }

    public String getConsecuenciaTitulo() {
        return consecuenciaTitulo;
    }

    public Opcion(String consecuenciaTitulo, int vidaAModificar) {
        this.consecuenciaTitulo = consecuenciaTitulo;
        this.vidaAModificar = vidaAModificar;
    }

    public Opcion(String consecuenciaTitulo, Item itemGanado) {
        this.consecuenciaTitulo = consecuenciaTitulo;
        this.itemGanado = itemGanado;
    }

    public String accionDeOpcion(Jugador jugador, String descripcionDeOpcion, int vidaAModificar){
        jugador.getPersonajeElegido().cambiarVida(vidaAModificar);
        if(jugador.getPersonajeElegido().getVida()<=0){
            return descripcionDeOpcion+"\nperdiste el juego\n";
        }else{
            return descripcionDeOpcion;
        }

    }
    public String accionDeOpcion(Jugador jugador, String descripcionDeOpcion, Item itemGanado){
        jugador.getMochila().agregarItem(itemGanado);
        return descripcionDeOpcion;
    }

}
