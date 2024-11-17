package com.example.proyectofinalprogii.Juego;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Usuario;
import com.example.proyectofinalprogii.Usuario.Mochila.Item;

/**
 * Opcion que solo puede tener una consecuencia (reestringido en los constructores).
 * @author Balde
 */
public class Opcion {

    private String consecuenciaTitulo; // será visible para el usuario. ej: "investigar bosque".
    private int vidaAModificar;
    private Item itemGanado;




    public String getConsecuenciaTitulo() {
        return consecuenciaTitulo;
    }

    public void setConsecuenciaTitulo(String consecuenciaTitulo) {
        this.consecuenciaTitulo = consecuenciaTitulo;
    }

    public int getVidaAModificar() {
        return vidaAModificar;
    }

    public void setVidaAModificar(int vidaAModificar) {
        this.vidaAModificar = vidaAModificar;
    }

    public Item getItemGanado() {
        return itemGanado;
    }

    public void setItemGanado(Item itemGanado) {
        this.itemGanado = itemGanado;
    }

    public Opcion(String consecuenciaTitulo, int vidaAModificar) {
        this.consecuenciaTitulo = consecuenciaTitulo;
        this.vidaAModificar = vidaAModificar;
    }

    public Opcion(String consecuenciaTitulo, Item itemGanado) {
        this.consecuenciaTitulo = consecuenciaTitulo;
        this.itemGanado = itemGanado;
    }

    public String accionDeOpcion(Usuario jugador, String descripcionDeOpcion){
        jugador.getPersonajeElegido().cambiarVida(vidaAModificar);
        return descripcionDeOpcion;
    }
    public String accionDeOpcion(Usuario jugador, String descripcionDeOpcion, Item itemGanado){
        jugador.getMochila().agregarItem(itemGanado);
        return descripcionDeOpcion;
    }

}
