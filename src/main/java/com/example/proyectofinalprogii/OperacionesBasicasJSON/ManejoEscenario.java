package com.example.proyectofinalprogii.OperacionesBasicasJSON;

import com.example.proyectofinalprogii.Juego.Escenario;
import com.example.proyectofinalprogii.Juego.Opcion;
import org.json.JSONObject;

public class ManejoEscenario {
    public static JSONObject escenarioToJson(Escenario escenario) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("idEscenario", escenario.getIdEscenario());
        jsonObject.put("descripcion", escenario.getDescripcion());

        JSONObject opcion1 = ManejoOpcion.opcionToJson(escenario.getOpcion1());
        jsonObject.put("opcion1", opcion1);

        JSONObject opcion2 = ManejoOpcion.opcionToJson(escenario.getOpcion2());
        jsonObject.put("opcion2", opcion2);

        return jsonObject;
    }

    public static Escenario jsonToEscenario(JSONObject jsonObject) {
        int idEscenario = jsonObject.getInt("idEscenario");
        String descripcion = jsonObject.getString("descripcion");
        Opcion opcion1 = ManejoOpcion.jsonToOpcion(jsonObject.getJSONObject("opcion1"));
        Opcion opcion2 = ManejoOpcion.jsonToOpcion(jsonObject.getJSONObject("opcion2"));

        Escenario escenarioAux = new Escenario(descripcion, opcion1, opcion2);
        escenarioAux.setIdEscenario(idEscenario);

        return escenarioAux;
    }
}
