package com.example.proyectofinalprogii.OperacionesBasicasJSON;

import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Jugador;
import com.example.proyectofinalprogii.Usuario.Mochila.Consumible;
import com.example.proyectofinalprogii.Usuario.Mochila.Item;
import com.example.proyectofinalprogii.Usuario.Mochila.Mochila;
import com.example.proyectofinalprogii.Usuario.Mochila.Objeto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class OperacionLecturaEscritura {
    /**
     @author Fede
     Esta clase tiene los metodos estaticos para guardar jugadores o traerlos de archivos json
     **/

    public static void jugadoresToArchivo(Map<String, Jugador> jugadores) {
         String archivoJugadores = "jugadores.json";

         try (FileWriter file = new FileWriter(archivoJugadores)) {
              JSONArray jsonArray = new JSONArray();

              for (Map.Entry<String, Jugador> entry : jugadores.entrySet()) {
                   Jugador jugadorAux = entry.getValue();

                   JSONObject jugadorJson = ManejoJugador.jugadorToJson(jugadorAux);

                   jsonArray.put(jugadorJson);
              }

              file.write(jsonArray.toString(4));

              System.out.println("Jugadores guardados en archivo");
         } catch (IOException e) {
              e.printStackTrace();
         }
    }

     public static void archivoToJugadores(Map<String, Jugador> mapJugadores) {
         String archivo = "jugadores.json";

         try {
              JSONArray arrJsonJugadores = new JSONArray(leerArchivo(archivo));

               cargarMapJugadores(arrJsonJugadores, mapJugadores);

         } catch (Exception e) {
              e.printStackTrace();
         }
     }

     public static void cargarMapJugadores(JSONArray arrJsonJugadores, Map<String, Jugador> mapJugadores) {
          mapJugadores.clear();

          for (int i = 0; i < arrJsonJugadores.length(); i++) {
               JSONObject jsonJugador = arrJsonJugadores.getJSONObject(i);

               Jugador jugador = ManejoJugador.jsonToJugador(jsonJugador);
               mapJugadores.put(jugador.getNombreUsuario(), jugador);
          }
     }

     public static JSONTokener leerArchivo(String archivo) {
          JSONTokener tokener = null;

          try {
               tokener = new JSONTokener(new FileReader(archivo));
          } catch (FileNotFoundException e) {
               e.printStackTrace();
          }

          return tokener;
     }

    //mas tarde hago las funciones de crear cuenta e iniciar sesion
}