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
        jsonObject.put("vidaTotalPerdida", personaje.getVidaTotalPerdida());

        return jsonObject;
    }

    public static Personaje jsonToPersonaje(JSONObject jsonObject) {
        String tipo = jsonObject.getString("tipo");
        int vida = jsonObject.getInt("vida");
        int vidaTotalGanada = jsonObject.getInt("vidaTotalGanada");
        int vidaTotalPerdida = jsonObject.getInt("vidaTotalPerdida");

        switch (tipo) {
            case "Joven":
                return new Joven(vida, vidaTotalGanada, vidaTotalPerdida);
            case "Adulto":
                return new Adulto(vida, vidaTotalGanada, vidaTotalPerdida);
            case "Viejo":
                return new Viejo(vida, vidaTotalGanada, vidaTotalPerdida);
            default:
                throw new IllegalArgumentException("Tipo de personaje desconocido: " + tipo);
        }
    }
}
