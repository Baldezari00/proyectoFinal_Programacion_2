package com.example.proyectofinalprogii.OperacionesBasicasJSON;

import com.example.proyectofinalprogii.Juego.Escenario;
import com.example.proyectofinalprogii.Juego.Opcion;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Usuario;
import com.example.proyectofinalprogii.Usuario.Mochila.Consumible;
import com.example.proyectofinalprogii.Usuario.Mochila.Objeto;

import java.util.*;

public class FuncionesAdmin {
    public static void verEscenarios() {
        HashSet<Escenario> escenarios = OperacionLecturaEscritura.archivoToEscenarios();

        // Ordenar los escenarios por ID
        List<Escenario> listaEscenarios = new ArrayList<>(escenarios);
        listaEscenarios.sort(Comparator.comparingInt(Escenario::getIdEscenario));

        for (Escenario escenario : listaEscenarios) {
            System.out.println(escenario.toString());
        }
    }

    public static void agregarEscenario() {
        HashSet<Escenario> escenarios = OperacionLecturaEscritura.archivoToEscenarios();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese una descripción para el escenario:");
        String descripcion = scanner.nextLine().trim();

        System.out.println("Ahora crearemos la opción 1:");
        Opcion opcion1 = crearOpcion(scanner);

        System.out.println("Ahora crearemos la opción 2:");
        Opcion opcion2 = crearOpcion(scanner);

        // Crear y agregar el nuevo escenario
        Escenario nuevoEscenario = new Escenario(descripcion, opcion1, opcion2);
        escenarios.add(nuevoEscenario);
        System.out.println("Escenario agregado con éxito.");

        // Guardar el HashSet actualizado en el archivo
        OperacionLecturaEscritura.escenariosToArchivo(escenarios);
        System.out.println("Cambios guardados correctamente.");
    }

    public static void eliminarEscenario() {
        HashSet<Escenario> escenarios = OperacionLecturaEscritura.archivoToEscenarios();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el ID del escenario a eliminar:");
        int idAEliminar = scanner.nextInt();
        scanner.nextLine();

        Escenario escenarioAEliminar = null;

        // Buscar el escenario con el ID proporcionado
        for (Escenario escenario : escenarios) {
            if (escenario.getIdEscenario() == idAEliminar) {
                escenarioAEliminar = escenario;
                break;
            }
        }

        if (escenarioAEliminar != null) {
            escenarios.remove(escenarioAEliminar);
            System.out.println("Escenario eliminado exitosamente.");
            OperacionLecturaEscritura.escenariosToArchivo(escenarios); // Guardar los cambios en el archivo
        } else {
            System.out.println("ERROR: No se encontró un escenario con ese ID.");
        }
    }

    public static void modificarEscenario() {
        HashSet<Escenario> escenarios = OperacionLecturaEscritura.archivoToEscenarios();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el ID del escenario a modificar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        Escenario escenarioModificar = null;
        for (Escenario e : escenarios) {
            if (e.getIdEscenario() == id) {
                escenarioModificar = e;
                break;
            }
        }

        if (escenarioModificar != null) {
            System.out.println("1. Modificar descripción");
            System.out.println("2. Modificar opciones");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la nueva descripción:");
                    escenarioModificar.setDescripcion(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Modificar opción 1:");
                    escenarioModificar.setOpcion1(crearOpcion(scanner));
                    System.out.println("Modificar opción 2:");
                    escenarioModificar.setOpcion2(crearOpcion(scanner));
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
            OperacionLecturaEscritura.escenariosToArchivo(escenarios);
            System.out.println("Escenario actualizado.");
        } else {
            System.out.println("ERROR: Escenario no encontrado.");
        }
    }

    private static Opcion crearOpcion(Scanner scanner) {
        System.out.println("Ingrese el título de la opción:");
        String titulo = scanner.nextLine().trim();

        int opcionElegida = -1;
        while (opcionElegida < 1 || opcionElegida > 3) {
            System.out.println("¿Cuál será su consecuencia?");
            System.out.println("1- Se modificará la vida del usuario");
            System.out.println("2- El usuario ganará un objeto");
            System.out.println("3- El usuario ganará un consumible");

            if (scanner.hasNextInt()) {
                opcionElegida = scanner.nextInt();
                scanner.nextLine(); // Limpia el buffer
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpia el buffer
            }
        }

        switch (opcionElegida) {
            case 1:
                System.out.println("Ingrese la cantidad de vida a sumar/restar:");
                int vidaModificar = obtenerEntero(scanner);
                return new Opcion(titulo, vidaModificar);

            case 2:
                System.out.println("Ingrese el nombre del objeto:");
                String nombreObjeto = scanner.nextLine().trim();

                System.out.println("Ingrese el código del objeto:");
                int codigoObjeto = obtenerEntero(scanner);

                Objeto objAux = new Objeto(nombreObjeto, codigoObjeto);
                return new Opcion(titulo, objAux);

            case 3:
                System.out.println("Ingrese el nombre del consumible:");
                String nombreConsumible = scanner.nextLine().trim();

                System.out.println("Ingrese la salud recuperada con el consumible:");
                int saludRecuperada = obtenerEntero(scanner);

                Consumible consumible = new Consumible(nombreConsumible, saludRecuperada);
                return new Opcion(titulo, consumible);

            default:
                throw new IllegalStateException("Opción inválida. Este error no debería ocurrir.");
        }
    }

    public static void verUsuarios(ManejoUsuarios manejoUsuarios) {
        for (Usuario usuario : manejoUsuarios.getJugadores().values()) {
            System.out.println(usuario);
        }
    }

    public static void eliminarUsuario(ManejoUsuarios manejoUsuarios) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del usuario a eliminar:");
        String nombreUsuario = scanner.nextLine().trim();

        if (manejoUsuarios.getJugadores().remove(nombreUsuario) != null) {
            System.out.println("Usuario eliminado con éxito.");
        } else {
            System.out.println("ERROR: Usuario no encontrado.");
        }
    }

    public static void modificarUsuario(ManejoUsuarios manejoUsuarios) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del usuario a modificar:");
        String nombreUsuario = scanner.nextLine().trim();

        Usuario usuario = manejoUsuarios.getJugadores().get(nombreUsuario);

        if (usuario != null) {
            System.out.println("¿Qué desea modificar?");
            System.out.println("1. Modificar nombre de usuario");
            System.out.println("2. Modificar contraseña");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nuevo nombre de usuario:");
                    String nuevoNombreUsuario = scanner.nextLine().trim();

                    // Verificar si el nuevo nombre de usuario ya existe
                    if (manejoUsuarios.getJugadores().containsKey(nuevoNombreUsuario)) {
                        System.out.println("ERROR: El nombre de usuario ya está en uso.");
                    } else {
                        // Cambiar el nombre de usuario
                        manejoUsuarios.getJugadores().remove(nombreUsuario); // Eliminar el usuario antiguo
                        usuario.setNombreUsuario(nuevoNombreUsuario); // Cambiar el nombre del usuario
                        manejoUsuarios.getJugadores().put(nuevoNombreUsuario, usuario); // Agregar el usuario con el nuevo nombre
                        System.out.println("Nombre de usuario actualizado.");
                    }
                    break;

                case 2:
                    System.out.println("Ingrese la nueva contraseña:");
                    String nuevaContrasenia = scanner.nextLine().trim();
                    usuario.setContrasenia(nuevaContrasenia);
                    System.out.println("Contraseña actualizada.");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } else {
            System.out.println("ERROR: Usuario no encontrado.");
        }
    }

    private static int obtenerEntero(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.nextLine(); // Limpia el buffer
        }
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpia el buffer
        return numero;
    }
}
