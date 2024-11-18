package com.example.proyectofinalprogii.OperacionesBasicasJSON;

import com.example.proyectofinalprogii.Juego.Escenario;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Adulto;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Joven;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Usuario;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Viejo;

import java.util.HashSet;
import java.util.Scanner;

public class ManejoCuentas {
    /**
     @author Fede
     Esta clase maneja los metodos de crear cuenta e iniciar sesion.
     Tiene una clase ManejoJugador en donde estan los jugadores creados y se guardan los nuevos
     **/

    public static void crearCuenta(ManejoUsuarios manejoUsuarios) {
        Scanner scanner = new Scanner(System.in);
        Usuario nuevoJugador = null;
        boolean usuarioValido = false;
        String nombreUsuario = null;
        HashSet<Escenario> escenarios = OperacionLecturaEscritura.archivoToEscenarios();

        // Bucle para pedir el nombre de usuario hasta que se encuentre uno válido
        while (!usuarioValido) {
            System.out.println("Ingrese un nombre de usuario:");
            nombreUsuario = scanner.nextLine().trim();  // Usamos trim para evitar problemas con espacios

            if (!verificarUsuario(manejoUsuarios, nombreUsuario)) {
                usuarioValido = true; // Salir del bucle si el nombre de usuario es válido
            } else {
                System.out.println("ERROR, nombre de usuario en uso.");
            }
        }

        System.out.println("Ingrese una contraseña:");
        String contrasenia = scanner.nextLine().trim(); // Lee la contraseña

        System.out.println("Elija su personaje:");
        System.out.println("1-Joven (Nivel facil)");
        System.out.println("2-Adulto (Nivel medio)");
        System.out.println("3-Viejo (Nivel dificil)");

        int opcionPersonaje = scanner.nextInt();
        scanner.nextLine();

        switch (opcionPersonaje) {
            case 1:
                nuevoJugador = new Usuario(nombreUsuario, contrasenia, new Joven(), escenarios);
                break;
            case 2:
                nuevoJugador = new Usuario(nombreUsuario, contrasenia, new Adulto(), escenarios);
                break;
            case 3:
                nuevoJugador = new Usuario(nombreUsuario, contrasenia, new Viejo(), escenarios);
                break;
        }

        manejoUsuarios.agregarJugador(nuevoJugador);
    }

    public static boolean verificarUsuario(ManejoUsuarios manejoUsuarios, String nombreUsuario) {
        return manejoUsuarios.getJugadores().containsKey(nombreUsuario);
    }

    public static Usuario iniciarSesion(ManejoUsuarios manejoUsuarios) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su nombre de usuario");
        String nombreUsuario = scanner.nextLine().trim();

        System.out.println("Ingrese su contrasenia");
        String contrasenia = scanner.nextLine().trim();

        if (nombreUsuario.isEmpty() || contrasenia.isEmpty()) {
            System.out.println("ERROR: Nombre de usuario o contraseña vacíos.");
            return null;
        }

        if (verificarUsuario(manejoUsuarios, nombreUsuario)) {
            Usuario jugador = manejoUsuarios.getJugadores().get(nombreUsuario);

            if (contrasenia.equals(jugador.getContrasenia())) {
                System.out.println("Sesión iniciada con éxito.");
                return jugador;
            } else {
                System.out.println("Contraseña incorrecta.");
                return null;
            }
        } else {
            System.out.println("ERROR: Usuario inexistente.");
            return null;
        }
    }
}
