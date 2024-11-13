package com.example.proyectofinalprogii.OperacionesBasicasJSON;

import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Jugador;

import java.util.Scanner;

public class ManejoUsuario {
    /**
     @author Fede
     Esta clase maneja los metodos de crear cuenta e iniciar sesion.
     Tiene una clase ManejoJugador en donde estan los jugadores creados y se guardan los nuevos
     **/

    private ManejoJugador manejoJugador;

    public ManejoUsuario() {
        this.manejoJugador = new ManejoJugador();
    }

    public void crearCuenta() {
        Scanner scanner = new Scanner(System.in);

        int flag = 0;

        String nombreUsuario = null;

        while (flag == 0) {
            System.out.println("Ingrese un nombre de usuario");
            nombreUsuario = scanner.nextLine();

            if (!verificarUsuario(nombreUsuario)) {
                flag = 1;
            } else {
                System.out.println("ERROR, nombre de usuario en uso");
            }
        }

        System.out.println("Ingrese una contraseña");
        String contrasenia = scanner.nextLine();

        Jugador nuevoJugador = new Jugador(nombreUsuario, contrasenia);

        manejoJugador.agregarJugador(nuevoJugador);
    }

    public boolean verificarUsuario(String nombreUsuario) {
        return manejoJugador.getJugadores().containsKey(nombreUsuario);
    }

    public void iniciarSesion() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su nombre de usuario");
        String nombreUsuario = scanner.nextLine().trim();

        System.out.println("Ingrese su contrasenia");
        String contrasenia = scanner.nextLine().trim();

        if (nombreUsuario.isEmpty() || contrasenia.isEmpty()) {
            System.out.println("ERROR: Nombre de usuario o contraseña vacíos.");
            return;
        }

        if (verificarUsuario(nombreUsuario)) {
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
