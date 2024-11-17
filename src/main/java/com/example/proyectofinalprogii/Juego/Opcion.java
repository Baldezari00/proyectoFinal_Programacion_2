package com.example.proyectofinalprogii.Juego;
import com.example.proyectofinalprogii.OperacionesBasicasJSON.ManejoItems;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Usuario;
import com.example.proyectofinalprogii.Usuario.Mochila.Consumible;
import com.example.proyectofinalprogii.Usuario.Mochila.Item;
import com.example.proyectofinalprogii.Usuario.Mochila.Objeto;
import org.json.JSONObject;

/**
 * Opcion que solo puede tener una consecuencia (reestringido en los constructores).
 * @author Balde
 */
public class Opcion {

    private String consecuenciaTitulo; // serÃ¡ visible para el usuario. ej: "investigar bosque".
    private int vidaAModificar;
    private Objeto objetoGanado;
    private Consumible consumibleGanado;

    public String getConsecuenciaTitulo() {
        return consecuenciaTitulo;
    }

    public Opcion(String consecuenciaTitulo, int vidaAModificar) {
        this.consecuenciaTitulo = consecuenciaTitulo;
        this.vidaAModificar = vidaAModificar;

        this.objetoGanado = null;
        this.consumibleGanado = null;
    }

    public Opcion(String consecuenciaTitulo, Objeto objetoGanado) {
        this.consecuenciaTitulo = consecuenciaTitulo;
        this.objetoGanado = objetoGanado;

        this.consumibleGanado = null;
        this.vidaAModificar = 0;
    }

    public Opcion(String consecuenciaTitulo, Consumible consumibleGanado) {
        this.consecuenciaTitulo = consecuenciaTitulo;
        this.consumibleGanado = consumibleGanado;

        this.objetoGanado = null;
        this.vidaAModificar = 0;
    }

    public String accionDeOpcion(Usuario jugador, String descripcionDeOpcion, int vidaAModificar){
        jugador.getPersonajeElegido().cambiarVida(vidaAModificar);

        if (jugador.getPersonajeElegido().getVida()<=0){
            return descripcionDeOpcion+"\nperdiste el juego\n";
        } else{
            return descripcionDeOpcion;
        }

    }
    public String accionDeOpcion(Usuario jugador, String descripcionDeOpcion, Item itemGanado){
        jugador.getMochila().agregarItem(itemGanado);
        return descripcionDeOpcion;
    }

    public Objeto getObjetoGanado() {
        return this.objetoGanado;
    }

    public Consumible getConsumibleGanado() {
        return this.consumibleGanado;
    }

    public int getVidaAModificar() {
        return this.vidaAModificar;
    }

    @Override
    public String toString() {
        return "Opcion{" +
                "consecuenciaTitulo='" + this.consecuenciaTitulo + '\'' +
                ", vidaAModificar=" + this.vidaAModificar +
                ", objetoGanado=" + this.objetoGanado +
                ", consumibleGanado=" + this.consumibleGanado +
                '}';
    }
}