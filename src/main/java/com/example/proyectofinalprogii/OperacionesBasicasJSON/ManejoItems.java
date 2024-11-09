package com.example.proyectofinalprogii.OperacionesBasicasJSON;

import com.example.proyectofinalprogii.Usuario.Mochila.Consumible;
import com.example.proyectofinalprogii.Usuario.Mochila.Objeto;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Fede
 * Aca esta todo el manejo de items a json y json a items, para modularizar y no tener que usar las funciones
 * en cada clase. Todas los metodos son static para que puedan ser llamados en cualquier lado
 */

public class ManejoItems {
    public static JSONObject consumibleToJson(Consumible consumible) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("idItem", consumible.getId());
            jsonObject.put("nombre", consumible.getNombre());
            jsonObject.put("saludRecibida", consumible.getSaludRecibida());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public static Consumible jsonToConsumible(JSONObject jsonObject) {
        int id = jsonObject.getInt("idItem");
        String nombre = jsonObject.getString("nombre");
        int saludRecibida = jsonObject.getInt("saludRecibida");

        Consumible aux = new Consumible(nombre, saludRecibida);
        aux.setId(id);

        return aux;
    }

    public static JSONObject objetoToJson(Objeto objeto) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("idItem", objeto.getId());
            jsonObject.put("nombre", objeto.getNombre());
            jsonObject.put("codigoObj", objeto.getCodigo());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public static Objeto jsonToObjeto(JSONObject jsonObject) {
        int id = jsonObject.getInt("idItem");
        String nombre = jsonObject.getString("nombre");
        int codigoObj = jsonObject.getInt("codigoObj");

        Objeto aux = new Objeto(nombre, codigoObj);
        aux.setId(id);

        return aux;
    }
}
