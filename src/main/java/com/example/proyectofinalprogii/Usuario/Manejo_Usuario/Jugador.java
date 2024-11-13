package com.example.proyectofinalprogii.Usuario.Manejo_Usuario;

import com.example.proyectofinalprogii.Usuario.Mochila.Item;
import com.example.proyectofinalprogii.Usuario.Mochila.Mochila;
import org.json.JSONArray;

import java.util.Objects;

public class Jugador {
    /**
    serializar y deserealizar jugador, luego de eso guardarlo en un archivo y poder leerlo retornando un jugador.
    TIP: en el proyecto ya cree la carpeta OperacionesBasicasJSON, donde podes poner lo de escribir a archivo y leer de archivo.

    @author Fede
    EXTRA: que el jugador pueda iniciar sesion. Si se te complica o ya se te hace mucho trabajo lo vemos en clase
    **/
    private static int contadorId = 0;
    private int id;
    private String nombreUsuario;
    private String contrasenia;
    private int oro;
    private Mochila<Item> mochila;

    public Jugador(String nombreUsuario, String contrasenia) {
        this.id = contadorId++;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.oro = 0;
        this.mochila = new Mochila<>();
    }

    public Jugador(String nombreUsuario, String contrasenia, Mochila<Item> mochila) {
        this.id = contadorId++;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.oro = 0;
        this.mochila = mochila;
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

    public int getOro() {
        return this.oro;
    }

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

    public void setOro(int oro) {
        this.oro = oro;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jugador{");
        sb.append("id=").append(id).append(", ");
        sb.append("nombreUsuario='").append(nombreUsuario).append("', ");
        sb.append("contraseña='").append(contrasenia).append("', ");
        sb.append("oro=").append(oro).append(", ");
        sb.append("mochila=").append(mochila.getItems());  // Imprime los items de la mochila
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jugador jugador)) return false;
        return id == jugador.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
