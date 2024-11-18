package com.example.proyectofinalprogii.Usuario.Manejo_Usuario;

import com.example.proyectofinalprogii.Juego.Escenario;
import com.example.proyectofinalprogii.Usuario.Mochila.Consumible;
import com.example.proyectofinalprogii.Usuario.Mochila.Item;
import com.example.proyectofinalprogii.Usuario.Mochila.Mochila;
import com.example.proyectofinalprogii.Usuario.Mochila.Objeto;

import java.util.HashSet;

public class Usuario {
    /**
    serializar y deserealizar jugador, luego de eso guardarlo en un archivo y poder leerlo retornando un jugador.
    TIP: en el proyecto ya cree la carpeta OperacionesBasicasJSON, donde podes poner lo de escribir a archivo y leer de archivo.

    @author Fede
    EXTRA: que el jugador pueda iniciar sesion. Si se te complica o ya se te hace mucho trabajo lo vemos en clase
    **/
    private HashSet<Escenario> escenarios; // cada jugador va a tener el set de escenarios donde se iran eliminando a medida que este los va jugando.
                                            //al crear el jugador, en el constructor automaticamente se le cargaran los escenarios y cuando termine de jugarlos todos (hasSet vacio) gana el juego
    private static int contadorId = 0;
    private int id;
    private String nombreUsuario;
    private String contrasenia;
    private Personaje personajeElegido;
    private Mochila<Item> mochila;
    private boolean esAdmin = true;

    public Usuario(String nombreUsuario, String contrasenia, Personaje personaje, HashSet<Escenario> escenariosJugador) {
        //falta cargar escenarios del archivo
        this.escenarios = escenariosJugador;
        this.id = contadorId++;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.mochila = new Mochila<>();
        this.personajeElegido = personaje;
    }

    public Usuario(String nombreUsuario, String contrasenia, Mochila<Item> mochila, Personaje personaje, HashSet<Escenario> escenarios) {
        this.escenarios = escenarios;
        this.id = contadorId++;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.mochila = mochila;
        this.personajeElegido = personaje;
    }

    public int getId() {
        return this.id;
    }

    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public String getContrasenia() {
        return this.contrasenia;
    }

    public boolean getEsAdmin() { return this.esAdmin; }

    public Mochila<Item> getMochila() {
        return this.mochila;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public HashSet<Escenario> getEscenarios() {
        return escenarios;
    }

    public void setEscenarios(HashSet<Escenario> escenarios) {
        this.escenarios = escenarios;
    }

    public Personaje getPersonajeElegido() {
        return personajeElegido;
    }

    public void setPersonajeElegido(Personaje personajeElegido) {
        this.personajeElegido = personajeElegido;
    }

    public void setMochila(Mochila<Item> mochila) {
        this.mochila = mochila;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", personajeElegido=" + personajeElegido +
                ", mochila=" + mochila +
                ", esAdmin=" + esAdmin +
                ", escenarios=" + escenarios +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario jugador)) return false;
        return id == jugador.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    public void agregarEscenario(Escenario escenario){
        escenarios.add(escenario);
    }

    //metodo para consumir item
    public void consumir(Item item){
        if(item instanceof Consumible){
            personajeElegido.cambiarVida(((Consumible) item).getSaludRecibida());
            System.out.println("objeto usado, te queda: "+personajeElegido.getVida()+"hp\n");
        }else if(item instanceof Objeto){

        }
    }
}
