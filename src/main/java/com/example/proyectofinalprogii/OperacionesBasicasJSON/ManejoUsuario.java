package com.example.proyectofinalprogii.OperacionesBasicasJSON;

import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Jugador;

import java.util.Map;
import java.util.Scanner;

public class ManejoUsuario {
    /**
     @author Fede
     Esta clase maneja los metodos de crear cuenta e iniciar sesion.
     Tiene una clase ManejoJugador en donde estan los jugadores creados y se guardan los nuevos
     **/

    public void crearCuenta(ManejoJugador manejoJugador) {
        Scanner scanner = new Scanner(System.in);
        boolean usuarioValido = false;
        String nombreUsuario = null;

        // Bucle para pedir el nombre de usuario hasta que se encuentre uno válido
        while (!usuarioValido) {
            System.out.println("Ingrese un nombre de usuario:");
            nombreUsuario = scanner.nextLine().trim();  // Usamos trim para evitar problemas con espacios

            if (!verificarUsuario(manejoJugador, nombreUsuario)) {
                usuarioValido = true; // Salir del bucle si el nombre de usuario es válido
            } else {
                System.out.println("ERROR, nombre de usuario en uso.");
            }
        }

        System.out.println("Ingrese una contraseña:");
        String contrasenia = scanner.nextLine().trim(); // Lee la contraseña

        Jugador nuevoJugador = new Jugador(nombreUsuario, contrasenia);
        manejoJugador.agregarJugador(nuevoJugador);

        System.out.println("Cuenta creada exitosamente.");
    }

    public boolean verificarUsuario(ManejoJugador manejoJugador, String nombreUsuario) {
        return manejoJugador.getJugadores().containsKey(nombreUsuario);
    }

    public void iniciarSesion(ManejoJugador manejoJugador) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su nombre de usuario");
        String nombreUsuario = scanner.nextLine().trim();

        System.out.println("Ingrese su contrasenia");
        String contrasenia = scanner.nextLine().trim();

        if (nombreUsuario.isEmpty() || contrasenia.isEmpty()) {
            System.out.println("ERROR: Nombre de usuario o contraseña vacíos.");
            return;
        }

        if (verificarUsuario(manejoJugador, nombreUsuario)) {
            Jugador jugador = manejoJugador.getJugadores().get(nombreUsuario);

            if (contrasenia.equals(jugador.getContrasenia())) {
                System.out.println("Sesión iniciada con éxito.");
            } else {
                System.out.println("Contraseña incorrecta.");
            }
        } else {
            System.out.println("ERROR: Usuario inexistente.");
        }
    }
}
