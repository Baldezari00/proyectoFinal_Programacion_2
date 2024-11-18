package com.example.proyectofinalprogii.OperacionesBasicasJSON;

import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Adulto;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Joven;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Personaje;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Viejo;
import org.json.JSONArray;
import org.json.JSONObject;

public class ManejoPersonaje {
    public static JSONObject personajeToJson(Personaje personaje) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("tipo", personaje.getClass().getSimpleName());
        jsonObject.put("vida", personaje.getVida());
        jsonObject.put("vidaTotalGanada", personaje.getVidaTotalGanada());

        return jsonObject;
    }

    public static Personaje jsonToPersonaje(JSONObject jsonObject) {
        String tipo = jsonObject.getString("tipo");
        int vida = jsonObject.getInt("vida");
        int vidaTotalGanada = jsonObject.getInt("vidaTotalGanada");

        switch (tipo) {
            case "Joven":
                return new Joven(vida, vidaTotalGanada);
            case "Adulto":
                return new Adulto(vida, vidaTotalGanada);
            case "Viejo":
                return new Viejo(vida, vidaTotalGanada);
            default:
                throw new IllegalArgumentException("Tipo de personaje desconocido: " + tipo);
        }
    }
}
