package com.example.proyectofinalprogii.Juego.ManejoInicio;
import com.example.proyectofinalprogii.ExcepcionesPersonalizadas.ExcJugador.UsuarioEnUsoException;
import com.example.proyectofinalprogii.Juego.JavaFXApp;
import com.example.proyectofinalprogii.OperacionesBasicasJSON.ManejoUsuarios;
import com.example.proyectofinalprogii.OperacionesBasicasJSON.OperacionLecturaEscritura;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Usuario;

import java.util.InputMismatchException;
import java.util.Scanner;



public class Inicio {
    /**
     * @author Fede
     * Esta clase tiene un método estático `inicio` donde se gestiona el inicio del programa,
     * ofreciendo opciones para iniciar sesión o crear una cuenta.
     **/
    private static ManejoUsuarios manejoJugadores = new ManejoUsuarios();

    public static Usuario inicio() {
        Scanner scanner = new Scanner(System.in);

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
                        boolean sesionActiva = true;
                        while (sesionActiva) {
                            // Menú según rol
                            if (usuarioActivo.getEsAdmin()) {
                                sesionActiva = mostrarMenuAdmin(usuarioActivo);
                            } else {
                                sesionActiva = mostrarMenuUsuario(usuarioActivo);
                            }
                        }
                    }
                    break;
                case 2:
                    crearCuenta(manejoJugadores);
                    guardarCambios(manejoJugadores);
                    System.out.println("Cuenta creada exitosamente. Ahora debe iniciar sesión.");
                    break;
                case 0:
                    exit = confirmarSalida(scanner);
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        return null; // Salida definitiva
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

    private static void mostrarMenuPorRol(Usuario usuario) {
        if (usuario.getEsAdmin()) {
            mostrarMenuAdmin(usuario);
        } else {
            mostrarMenuUsuario(usuario);
        }
    }

    public static boolean mostrarMenuAdmin(Usuario usuarioActivo) {
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
            System.out.println("8- Jugar");
            System.out.println("0- Cerrar sesión");
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
                case 8:
                    if (JavaFXApp.isLaunched()) {
                        System.out.println("Ya has jugado una vez, reinicia para volver a jugar.");
                    } else {
                        System.out.println("Iniciando la interfaz gráfica...");
                        JavaFXApp.setUsuarioActivo(usuarioActivo);
                        JavaFXApp.mostrarInterfaz();
                    }
                    break;
                case 0:
                    return false; // Cierra sesión y vuelve al menú inicial
                default:
                    System.out.println("Opción inválida.");
            }
        }
        return true; // Mantiene la sesión activa
    }

    public static boolean mostrarMenuUsuario(Usuario usuarioActivo) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== MENÚ USUARIO ===");
            System.out.println("1- Jugar partida");
            System.out.println("2- Ver mis datos");
            System.out.println("3- Editar datos");
            System.out.println("0- Cerrar sesión");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    if (JavaFXApp.isLaunched()) {
                        System.out.println("Ya has jugado una vez, reinicia para volver a jugar.");
                    } else {
                        System.out.println("Iniciando la interfaz gráfica...");
                        JavaFXApp.setUsuarioActivo(usuarioActivo);
                        JavaFXApp.mostrarInterfaz();
                    }
                    break;
                case 2:
                    mostrarUsuario(usuarioActivo);
                    break;
                case 3:
                    editarDatos(usuarioActivo, manejoJugadores);
                    break;
                case 0:
                    return false; // Cierra sesión y vuelve al menú inicial
                default:
                    System.out.println("Opción inválida.");
            }
        }
        return true; // Mantiene la sesión activa
    }

    private static void mostrarUsuario(Usuario usuario) {
        System.out.println("DATOS PERSONALES");
        System.out.println("Nombre de usuario: " + usuario.getNombreUsuario());
        System.out.println("Contraseña: " + usuario.getContrasenia());
    }

    private static void editarDatos(Usuario usuario, ManejoUsuarios manejoUsuarios) {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("¿Qué desea editar?");
            System.out.println("1- Nombre de usuario");
            System.out.println("2- Contraseña");
            System.out.println("0- Volver al menú principal");

            int opcion = 0;
            boolean opcionValida = false;

            // Validar que la opción sea un número
            while (!opcionValida) {
                try {
                    opcion = scanner.nextInt();
                    scanner.nextLine();

                    if (opcion < 0 || opcion > 2) {
                        throw new IllegalArgumentException("Opción inválida. Ingrese 1, 2 o 0.");
                    }

                    opcionValida = true;
                } catch (InputMismatchException e) {
                    System.out.println("ERROR: Debe ingresar un número válido.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            switch (opcion) {
                case 1:
                    // Editar nombre de usuario
                    System.out.print("Ingrese el nuevo nombre de usuario: ");
                    String nuevoNombreUsuario = scanner.nextLine();

                    // Validar que no esté vacío
                    if (nuevoNombreUsuario.trim().isEmpty()) {
                        System.out.println("ERROR: El nombre de usuario no puede estar vacío.");
                    } else {
                        try {
                            if (manejoUsuarios.getJugadores().containsKey(nuevoNombreUsuario)) {
                                throw new UsuarioEnUsoException("El nombre de usuario ya está en uso. Ingrese otro.");
                            }
                            usuario.setNombreUsuario(nuevoNombreUsuario);
                            System.out.println("Nombre de usuario actualizado a: " + nuevoNombreUsuario);
                            // Guardar los cambios en el archivo
                            OperacionLecturaEscritura.jugadoresToArchivo(manejoUsuarios.getJugadores());
                            exit = true;  // Salir después de la modificación
                        } catch (UsuarioEnUsoException e) {
                            System.out.println("ERROR: " + e.getMessage());
                        }
                    }
                    break;

                case 2:
                    // Editar contraseña
                    System.out.print("Ingrese la nueva contraseña: ");
                    String nuevaContrasenia = scanner.nextLine();

                    // Validar que no esté vacía
                    if (nuevaContrasenia.trim().isEmpty()) {
                        System.out.println("ERROR: La contraseña no puede estar vacía.");
                    } else {
                        usuario.setContrasenia(nuevaContrasenia);
                        System.out.println("Contraseña actualizada correctamente.");
                        // Guardar los cambios en el archivo
                        OperacionLecturaEscritura.jugadoresToArchivo(manejoUsuarios.getJugadores());
                        exit = true;  // Salir después de la modificación
                    }
                    break;
                case 0:
                    // Volver al menú principal
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }

    public static ManejoUsuarios getManejoJugadores() {
        return manejoJugadores;
    }
}



