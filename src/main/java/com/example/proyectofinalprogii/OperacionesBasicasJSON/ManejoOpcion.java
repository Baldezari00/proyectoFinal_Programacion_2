package com.example.proyectofinalprogii.OperacionesBasicasJSON;

import com.example.proyectofinalprogii.Juego.Opcion;
import com.example.proyectofinalprogii.Usuario.Mochila.Consumible;
import com.example.proyectofinalprogii.Usuario.Mochila.Objeto;
import org.json.JSONObject;

public class ManejoOpcion {
    public static JSONObject opcionToJson(Opcion opcion) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("consecuenciaTitulo", opcion.getConsecuenciaTitulo());
        jsonObject.put("descOpcion",opcion.getDescripcionDeOpcion());

        // Verificar si el itemGanado es null
        if (opcion.getObjetoGanado() != null) {
            jsonObject.put("objetoGanado", ManejoItems.objetoToJson(opcion.getObjetoGanado()));
        } else if (opcion.getConsumibleGanado() != null) {
            jsonObject.put("consumibleGanado", ManejoItems.consumibleToJson(opcion.getConsumibleGanado()));
        } else if (opcion.getVidaAModificar() != 0) {
            jsonObject.put("vidaAModificar", opcion.getVidaAModificar());
        }

        return jsonObject;
    }

    public static Opcion jsonToOpcion(JSONObject jsonObject) {
        String consecuenciaTitulo = jsonObject.getString("consecuenciaTitulo");
        String descOpcion = jsonObject.getString("descOpcion");

        if (jsonObject.has("vidaAModificar")) {
            int vidaAModificar = jsonObject.getInt("vidaAModificar");
            return new Opcion(consecuenciaTitulo,descOpcion, vidaAModificar);
        } else if (jsonObject.has("objetoGanado")) {
            Objeto objeto = ManejoItems.jsonToObjeto(jsonObject.getJSONObject("objetoGanado"));
            return new Opcion(consecuenciaTitulo,descOpcion, objeto);
        } else if (jsonObject.has("consumibleGanado")) {
            Consumible consumible = ManejoItems.jsonToConsumible(jsonObject.getJSONObject("consumibleGanado"));
            return new Opcion(consecuenciaTitulo,descOpcion, consumible);
        }

        throw new IllegalArgumentException("El JSON no contiene suficiente información para crear una Opcion.");
    }
}
