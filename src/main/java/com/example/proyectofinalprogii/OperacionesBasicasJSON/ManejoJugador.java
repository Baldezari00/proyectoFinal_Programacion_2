package com.example.proyectofinalprogii.OperacionesBasicasJSON;

import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Jugador;
import com.example.proyectofinalprogii.Usuario.Mochila.Consumible;
import com.example.proyectofinalprogii.Usuario.Mochila.Item;
import com.example.proyectofinalprogii.Usuario.Mochila.Mochila;
import com.example.proyectofinalprogii.Usuario.Mochila.Objeto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ManejoJugador {
    /**
     @author Fede
     Esta clase tiene los metodos estaticos para pasar de json a jugador y viceversa
     **/

    //aca se van a guardar todos los jugadores creados, su key es el nombre de usuario
    private Map<String, Jugador> jugadores;

    public ManejoJugador() {
        this.jugadores = new HashMap<>();
    }

    public void agregarJugador(Jugador jugador) {
        if (!jugadores.containsKey(jugador.getNombreUsuario())) {
            jugadores.put(jugador.getNombreUsuario(), jugador);
        } else {
            //crear error de jugador existente
        }
    }

    public void jugadoresToArchivo() {
        String archivoJugadores = "jugadores.json";

        try (FileWriter file = new FileWriter(archivoJugadores)) {
            JSONArray jsonArray = new JSONArray();

            for (Map.Entry<String, Jugador> entry : jugadores.entrySet()) {
                Jugador jugadorAux = entry.getValue();

                JSONObject jugadorJson = jugadorToJson(jugadorAux);

                jsonArray.put(jugadorJson);
            }

            file.write(jsonArray.toString(4));

            System.out.println("Jugadores guardados en archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject jugadorToJson(Jugador jugador) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("idJugador", jugador.getId());
            jsonObject.put("nombreUsuario", jugador.getNombreUsuario());
            jsonObject.put("contraseña", jugador.getContrasenia());
            jsonObject.put("oroJugador", jugador.getOro());

            JSONArray jsonArray = new JSONArray();

            //Esto se podria mejorar para no tener que usar instance of ni castear

            for (Item item : jugador.getMochila().getItems()) {
                JSONObject jsonItem = null;
                if (item instanceof Consumible) {
                    jsonItem = ManejoItems.consumibleToJson((Consumible) item);
                } else if (item instanceof Objeto) {
                    jsonItem = ManejoItems.objetoToJson((Objeto) item);
                }

                jsonArray.put(jsonItem);

                jsonObject.put("items", jsonArray);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public static Jugador jsonToJugador(JSONObject jsonObject) {
        int idJugador = jsonObject.getInt("idJugador");
        String nombreUsuario = jsonObject.getString("nombreUsuario");
        String contrasenia = jsonObject.getString("contraseña");
        int oroJugador = jsonObject.getInt("oroJugador");

        Mochila<Item> mochila = new Mochila<>();

        JSONArray jsonArray = jsonObject.getJSONArray("items");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject itemJson = jsonArray.getJSONObject(i);

            if (itemJson.has("saludRecibida")) {
                Consumible aux = ManejoItems.jsonToConsumible(itemJson);
                mochila.agregarItem(aux);
            } else if (itemJson.has("codigoObj")) {
                Objeto aux = ManejoItems.jsonToObjeto(itemJson);
                mochila.agregarItem(aux);
            }
        }

        Jugador jugadorAux = new Jugador(nombreUsuario, contrasenia, mochila);
        jugadorAux.setId(idJugador);
        jugadorAux.setOro(oroJugador);

        return jugadorAux;
    }
}
