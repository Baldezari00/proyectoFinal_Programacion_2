package com.example.proyectofinalprogii.OperacionesBasicasJSON;

import com.example.proyectofinalprogii.Juego.Escenario;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Usuario;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.HashSet;
import java.util.Map;

public class OperacionLecturaEscritura {
    /**
     @author Fede
     Esta clase tiene los metodos estaticos para guardar jugadores o traerlos de archivos json
     **/

    public static void jugadoresToArchivo(Map<String, Usuario> jugadores) {
         String archivoJugadores = "jugadores.json";

         try (FileWriter file = new FileWriter(archivoJugadores)) {
              JSONArray jsonArray = new JSONArray();

              for (Map.Entry<String, Usuario> entry : jugadores.entrySet()) {
                   Usuario jugadorAux = entry.getValue();

                   JSONObject jugadorJson = ManejoUsuarios.jugadorToJson(jugadorAux);

                   jsonArray.put(jugadorJson);
              }

              file.write(jsonArray.toString(4));

              System.out.println("Jugadores guardados en archivo");
         } catch (IOException e) {
              e.printStackTrace();
         }
    }

     public static void archivoToJugadores(Map<String, Usuario> mapJugadores) {
         String archivo = "jugadores.json";

         try {
             File file = new File(archivo);

             //verificar si el archivo existe
             if (!file.exists()) {
                 crearArchivoJugadores(archivo);
             }

              JSONArray arrJsonJugadores = new JSONArray(leerArchivo(archivo));

               cargarMapJugadores(arrJsonJugadores, mapJugadores);

         } catch (Exception e) {
              e.printStackTrace();
         }
     }

    private static void crearArchivoJugadores(String archivo) {
        File file = new File(archivo);

        try (PrintWriter salida = new PrintWriter(file)) {
            salida.println("[]"); //escribe un arreglo JSON vacío
        } catch (FileNotFoundException e) {
            System.out.println("Error al crear el archivo " + archivo + ": " + e.getMessage());
        }
    }

     public static void escenariosToArchivo(HashSet<Escenario> escenarios) {
        String archivoEscenarios = "escenarios.json";

        try (FileWriter file = new FileWriter(archivoEscenarios)) {
            JSONArray jsonArray = new JSONArray();

            for (Escenario escenario : escenarios) {
                JSONObject jsonEscenario = ManejoEscenario.escenarioToJson(escenario);

                jsonArray.put(jsonEscenario);
            }

            file.write(jsonArray.toString(4));

            System.out.println("Escenarios guardados en archivo correctamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
     }

     public static HashSet<Escenario> archivoToEscenarios() {
        String archivo = "escenarios.json";

        HashSet<Escenario> escenarios = new HashSet<>();

         // Verificar si el archivo existe y crearlo si es necesario
         File file = new File(archivo);
         if (!file.exists()) {
             crearArchivoEscenarios(archivo); // Crear archivo vacío si no existe
         }

        try {
            JSONArray arrJsonEscenarios = new JSONArray(leerArchivo(archivo));

            cargarSetEscenarios(arrJsonEscenarios, escenarios);

            return escenarios;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return escenarios;
    }

    private static void crearArchivoEscenarios(String archivo) {
        File file = new File(archivo);

        try (PrintWriter salida = new PrintWriter(file)) {
            salida.println("[]"); // Escribe un arreglo JSON vacío
        } catch (FileNotFoundException e) {
            System.out.println("Error al crear el archivo " + archivo + ": " + e.getMessage());
        }
    }

    public static void cargarSetEscenarios(JSONArray arrJsonEscenarios, HashSet<Escenario> escenarios) {
        for (int i = 0; i < arrJsonEscenarios.length(); i++) {
            JSONObject jsonEscenario = arrJsonEscenarios.getJSONObject(i);

            Escenario escenario = ManejoEscenario.jsonToEscenario(jsonEscenario);
            escenarios.add(escenario);
        }
    }

     public static void cargarMapJugadores(JSONArray arrJsonJugadores, Map<String, Usuario> mapJugadores) {
          mapJugadores.clear();

          for (int i = 0; i < arrJsonJugadores.length(); i++) {
               JSONObject jsonJugador = arrJsonJugadores.getJSONObject(i);

               Usuario jugador = ManejoUsuarios.jsonToJugador(jsonJugador);
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

}