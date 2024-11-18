package com.example.proyectofinalprogii.OperacionesBasicasJSON;

import com.example.proyectofinalprogii.Juego.Escenario;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Personaje.Personaje;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Usuario;
import com.example.proyectofinalprogii.Usuario.Mochila.Consumible;
import com.example.proyectofinalprogii.Usuario.Mochila.Item;
import com.example.proyectofinalprogii.Usuario.Mochila.Mochila;
import com.example.proyectofinalprogii.Usuario.Mochila.Objeto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ManejoUsuarios {
    /**
     @author Fede
     Esta clase tiene los metodos estaticos para pasar de json a jugador y viceversa
     **/

    //aca se van a guardar todos los jugadores creados, su key es el nombre de usuario
    private Map<String, Usuario> jugadores;

    public ManejoUsuarios() {
        this.jugadores = new HashMap<>();
    }

    public Map<String, Usuario> getJugadores() {
        return jugadores;
    }

    public void agregarJugador(Usuario jugador) {
        if (!jugadores.containsKey(jugador.getNombreUsuario())) {
            jugadores.put(jugador.getNombreUsuario(), jugador);
        } else {
            //crear error de jugador existente
        }
    }

    public static JSONObject jugadorToJson(Usuario jugador) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("idJugador", jugador.getId());
            jsonObject.put("nombreUsuario", jugador.getNombreUsuario());
            jsonObject.put("contraseña", jugador.getContrasenia());
            jsonObject.put("esAdmin", jugador.getEsAdmin());


            JSONArray jsonItems = new JSONArray();

            for (Item item : jugador.getMochila().getItems()) {
                JSONObject jsonItem = null;
                if (item instanceof Consumible) {
                    jsonItem = ManejoItems.consumibleToJson((Consumible) item);
                } else if (item instanceof Objeto) {
                    jsonItem = ManejoItems.objetoToJson((Objeto) item);
                }

                jsonItems.put(jsonItem);
            }

            jsonObject.put("items", jsonItems);

            JSONObject jsonPersonaje = ManejoPersonaje.personajeToJson(jugador.getPersonajeElegido());
            jsonObject.put("personajeElegido", jsonPersonaje);

            JSONArray jsonEscenarios = new JSONArray();

            for (Escenario escenario : jugador.getEscenarios()) {
                JSONObject jsonEscenario = ManejoEscenario.escenarioToJson(escenario);

                jsonEscenarios.put(jsonEscenario);
            }

            jsonObject.put("escenarios", jsonEscenarios);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public static Usuario jsonToJugador(JSONObject jsonObject) {
        int idJugador = jsonObject.getInt("idJugador");
        String nombreUsuario = jsonObject.getString("nombreUsuario");
        String contrasenia = jsonObject.getString("contraseña");
        boolean esAdmin = jsonObject.getBoolean("esAdmin");

        Mochila<Item> mochila = new Mochila<>();

        JSONArray jsonItems = jsonObject.getJSONArray("items");

        for (int i = 0; i < jsonItems.length(); i++) {
            JSONObject itemJson = jsonItems.getJSONObject(i);

            if (itemJson.has("saludRecibida")) {
                Consumible aux = ManejoItems.jsonToConsumible(itemJson);
                mochila.agregarItem(aux);
            } else if (itemJson.has("codigoObj")) {
                Objeto aux = ManejoItems.jsonToObjeto(itemJson);
                mochila.agregarItem(aux);
            }
        }

        JSONObject jsonPersonaje = jsonObject.getJSONObject("personajeElegido");
        Personaje personajeElegido = ManejoPersonaje.jsonToPersonaje(jsonPersonaje);

        JSONArray jsonEscenarios = jsonObject.getJSONArray("escenarios");
        HashSet<Escenario> escenariosAux = new HashSet<>();

        for (int i = 0; i < jsonEscenarios.length(); i++) {
            JSONObject jsonEscenario = jsonEscenarios.getJSONObject(i);

            Escenario aux = ManejoEscenario.jsonToEscenario(jsonEscenario);

            escenariosAux.add(aux);
        }

        Usuario jugadorAux = new Usuario(nombreUsuario, contrasenia, mochila, personajeElegido, escenariosAux, esAdmin);
        jugadorAux.setId(idJugador);

        return jugadorAux;
    }
}
