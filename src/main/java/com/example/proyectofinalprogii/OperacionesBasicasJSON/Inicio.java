package com.example.proyectofinalprogii.OperacionesBasicasJSON;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Inicio {
    /**
     @author Fede
     Esta clase tiene un metodo estatico inicio en donde se llama un manejoJugador donde cuando se inicia se traen
     los jugadores del archivo, y tiene todas las funciones relacionadas a ellos
     **/
    public static void inicio() {
        Scanner scanner = new Scanner(System.in);

        // Se crea una instancia de ManejoJugador y se cargan los jugadores desde el archivo
        ManejoJugador manejoJugadores = new ManejoJugador();
        OperacionLecturaEscritura.archivoToJugadores(manejoJugadores.getJugadores());

        boolean exit = false;

        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1- Iniciar sesión");
            System.out.println("2- Crear cuenta");
            System.out.println("0- Salir");

            if (scanner.hasNextInt()) { // Verifica si el input es un número
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        ManejoUsuario.iniciarSesion(manejoJugadores);
                        exit = true;
                        break;
                    case 2:
                        ManejoUsuario.crearCuenta(manejoJugadores);
                        OperacionLecturaEscritura.jugadoresToArchivo(manejoJugadores.getJugadores());

                        System.out.println("Cuenta creada exitosamente. Ahora debe iniciar sesión.");
                        ManejoUsuario.iniciarSesion(manejoJugadores);
                        exit = true;
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        exit = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                        break;
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next(); // limpia el input no válido
            }
        }

    }
}