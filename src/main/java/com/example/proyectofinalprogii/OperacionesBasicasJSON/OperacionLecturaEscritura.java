package com.example.proyectofinalprogii.OperacionesBasicasJSON;

import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Jugador;
import com.example.proyectofinalprogii.Usuario.Mochila.Consumible;
import com.example.proyectofinalprogii.Usuario.Mochila.Item;
import com.example.proyectofinalprogii.Usuario.Mochila.Objeto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OperacionLecturaEscritura {
    /**
     @author Fede
     Esta clase tiene los metodos estaticos para guardar jugadores o traerlos de archivos json
     **/

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

    //mas tarde hago de json al jugador y las funciones de crear cuenta e iniciar sesion
}