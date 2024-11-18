package com.example.proyectofinalprogii.OperacionesBasicasJSON;

import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Usuario;

import java.util.Scanner;

public class Inicio {
    /**
     * @author Fede
     * Esta clase tiene un método estático `inicio` donde se gestiona el inicio del programa,
     * ofreciendo opciones para iniciar sesión o crear una cuenta.
     **/
    public static Usuario inicio() {
        Scanner scanner = new Scanner(System.in);
        ManejoUsuarios manejoJugadores = new ManejoUsuarios();

        // Cargar jugadores desde el archivo
        OperacionLecturaEscritura.archivoToJugadores(manejoJugadores.getJugadores());

        Usuario usuarioActivo = null;
        boolean exit = false;

        while (!exit) {
            mostrarMenuInicial();
            int opcion = leerOpcion(scanner);

            switch (opcion) {
                case 1:
                    usuarioActivo = iniciarSesion(manejoJugadores);
                    if (usuarioActivo != null) {
                        exit = true; // Salir al menú principal del usuario
                    }
                    break;
                case 2:
                    crearCuenta(manejoJugadores);
                    guardarCambios(manejoJugadores);
                    System.out.println("Cuenta creada exitosamente. Ahora debe iniciar sesión.");
                    usuarioActivo = iniciarSesion(manejoJugadores);
                    exit = usuarioActivo != null;
                    break;
                case 0:
                    exit = confirmarSalida(scanner);
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }

        if (usuarioActivo != null) {
            mostrarMenuPorRol(usuarioActivo, manejoJugadores);
        }

        return usuarioActivo;
    }

    private static void mostrarMenuInicial() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1- Iniciar sesión");
        System.out.println("2- Crear cuenta");
        System.out.println("0- Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int leerOpcion(Scanner scanner) {
        if (scanner.hasNextInt()) {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpia el buffer
            return opcion;
        } else {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next(); // Limpia el input no numérico
            return -1; // Valor que no corresponde a ninguna opción
        }
    }

    private static Usuario iniciarSesion(ManejoUsuarios manejoJugadores) {
        Usuario usuario = ManejoCuentas.iniciarSesion(manejoJugadores);
        if (usuario == null) {
            System.out.println("Inicio de sesión fallido. Intente nuevamente.");
        }
        return usuario;
    }

    private static void crearCuenta(ManejoUsuarios manejoJugadores) {
        ManejoCuentas.crearCuenta(manejoJugadores);
    }

    private static void guardarCambios(ManejoUsuarios manejoJugadores) {
        OperacionLecturaEscritura.jugadoresToArchivo(manejoJugadores.getJugadores());
    }

    private static boolean confirmarSalida(Scanner scanner) {
        System.out.print("¿Está seguro de que desea salir? (S/N): ");
        String respuesta = scanner.nextLine().trim().toUpperCase();
        return respuesta.equals("S");
    }

    private static void mostrarMenuPorRol(Usuario usuario, ManejoUsuarios manejoJugadores) {
        if (usuario.getEsAdmin()) {
            mostrarMenuAdmin(manejoJugadores);
        } else {
            mostrarMenuUsuario();
        }
    }

    private static void mostrarMenuAdmin(ManejoUsuarios manejoJugadores) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("1- Ver usuarios");
            System.out.println("2- Eliminar usuario");
            System.out.println("3- Modificar usuario");
            System.out.println("4- Ver escenarios");
            System.out.println("5- Crear escenario");
            System.out.println("6- Eliminar escenario");
            System.out.println("7- Modificar escenario");
            System.out.println("0- Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = leerOpcion(scanner);

            switch (opcion) {
                case 1:
                    FuncionesAdmin.verUsuarios(manejoJugadores);
                    break;
                case 2:
                    FuncionesAdmin.eliminarUsuario(manejoJugadores);
                    break;
                case 3:
                    FuncionesAdmin.modificarUsuario(manejoJugadores);
                    break;
                case 4:
                    FuncionesAdmin.verEscenarios();
                    break;
                case 5:
                    FuncionesAdmin.agregarEscenario();
                    break;
                case 6:
                    FuncionesAdmin.eliminarEscenario();
                    break;
                case 7:
                    FuncionesAdmin.modificarEscenario();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void mostrarMenuUsuario() {
        System.out.println("\n=== MENÚ USUARIO ===");
        System.out.println("1- Jugar partida");
        System.out.println("0- Salir");
        // implementar acciones
    }
}
