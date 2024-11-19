package com.example.proyectofinalprogii.Juego;
import com.example.proyectofinalprogii.Usuario.Mochila.Consumible;
import com.example.proyectofinalprogii.Usuario.Mochila.Objeto;

/**
 * Opcion que solo puede tener una consecuencia (reestringido en los constructores).
 * @author Balde y Fede
 */
public class Opcion {

    private String consecuenciaTitulo; // sera visible para el usuario. ej: "investigar bosque".
    private int vidaAModificar;
    private Objeto objetoGanado;
    private Consumible consumibleGanado;
    private String descripcionDeOpcion;

    public String getConsecuenciaTitulo() {
        return consecuenciaTitulo;
    }

    public Opcion(String consecuenciaTitulo,String descripcionDeOpcion, int vidaAModificar) {
        this.consecuenciaTitulo = consecuenciaTitulo;
        this.descripcionDeOpcion = descripcionDeOpcion;

        this.vidaAModificar = vidaAModificar;

        this.objetoGanado = null;
        this.consumibleGanado = null;
    }

    public Opcion(String consecuenciaTitulo,String descripcionDeOpcion, Objeto objetoGanado) {
        this.consecuenciaTitulo = consecuenciaTitulo;
        this.descripcionDeOpcion = descripcionDeOpcion;

        this.objetoGanado = objetoGanado;

        this.consumibleGanado = null;
        this.vidaAModificar = 0;
    }

    public Opcion(String consecuenciaTitulo,String descripcionDeOpcion, Consumible consumibleGanado) {
        this.consecuenciaTitulo = consecuenciaTitulo;
        this.descripcionDeOpcion = descripcionDeOpcion;
        this.consumibleGanado = consumibleGanado;

        this.objetoGanado = null;
        this.vidaAModificar = 0;
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

    public String getDescripcionDeOpcion() {
        return descripcionDeOpcion;
    }

    public void setDescripcionDeOpcion(String descripcionDeOpcion) {
        this.descripcionDeOpcion = descripcionDeOpcion;
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