package com.example.proyectofinalprogii.Juego.ManejoInicio;

import com.example.proyectofinalprogii.ExcepcionesPersonalizadas.ExcJugador.OpcionInvalidaException;
import com.example.proyectofinalprogii.Juego.Escenario;
import com.example.proyectofinalprogii.OperacionesBasicasJSON.ManejoUsuarios;
import com.example.proyectofinalprogii.OperacionesBasicasJSON.OperacionLecturaEscritura;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Personaje.Adulto;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Personaje.Joven;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Usuario;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Personaje.Viejo;

import java.util.HashSet;
import java.util.InputMismatchException;
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

            if (nombreUsuario.isEmpty()) {
                System.out.println("ERROR: El nombre de usuario no puede estar vacío. Intente nuevamente.");
            } else if (!verificarUsuario(manejoUsuarios, nombreUsuario)) {
                usuarioValido = true; // Salir del bucle si el nombre de usuario es válido
            } else {
                System.out.println("ERROR: Nombre de usuario en uso.");
            }
        }

        // Validación de la contraseña
        String contrasenia = "";
        while (contrasenia.isEmpty()) {
            System.out.println("Ingrese una contraseña:");
            contrasenia = scanner.nextLine().trim(); // Lee la contraseña

            if (contrasenia.isEmpty()) {
                System.out.println("ERROR: La contraseña no puede estar vacía. Intente nuevamente.");
            }
        }

        boolean personajeSeleccionado = false;

        while (!personajeSeleccionado) {
            try {
                System.out.println("Elija su personaje:");
                System.out.println("1-Joven (Nivel fácil)");
                System.out.println("2-Adulto (Nivel medio)");
                System.out.println("3-Viejo (Nivel difícil)");

                int opcionPersonaje = scanner.nextInt();
                scanner.nextLine(); // Limpia el buffer

                switch (opcionPersonaje) {
                    case 1:
                        nuevoJugador = new Usuario(nombreUsuario, contrasenia, new Joven(), escenarios);
                        personajeSeleccionado = true;
                        break;
                    case 2:
                        nuevoJugador = new Usuario(nombreUsuario, contrasenia, new Adulto(), escenarios);
                        personajeSeleccionado = true;
                        break;
                    case 3:
                        nuevoJugador = new Usuario(nombreUsuario, contrasenia, new Viejo(), escenarios);
                        personajeSeleccionado = true;
                        break;
                    default:
                        throw new OpcionInvalidaException("La opción seleccionada no es válida. Intente nuevamente.");
                }
            } catch (OpcionInvalidaException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número.");
                scanner.nextLine();
            }
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
