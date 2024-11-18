package com.example.proyectofinalprogii.Juego.ManejoInicio;

import com.example.proyectofinalprogii.ExcepcionesPersonalizadas.ExcJugador.EscenarioNoEncontradoException;
import com.example.proyectofinalprogii.ExcepcionesPersonalizadas.ExcJugador.NoHayEscenariosException;
import com.example.proyectofinalprogii.ExcepcionesPersonalizadas.ExcJugador.NoHayUsuariosException;
import com.example.proyectofinalprogii.ExcepcionesPersonalizadas.ExcJugador.OpcionInvalidaException;
import com.example.proyectofinalprogii.Juego.Escenario;
import com.example.proyectofinalprogii.Juego.Opcion;
import com.example.proyectofinalprogii.OperacionesBasicasJSON.ManejoUsuarios;
import com.example.proyectofinalprogii.OperacionesBasicasJSON.OperacionLecturaEscritura;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Usuario;
import com.example.proyectofinalprogii.Usuario.Mochila.Consumible;
import com.example.proyectofinalprogii.Usuario.Mochila.Objeto;

import java.util.*;

public class FuncionesAdmin {
    public static void verEscenarios() {
        HashSet<Escenario> escenarios = OperacionLecturaEscritura.archivoToEscenarios();

        // Verificar si no hay escenarios
        if (escenarios.isEmpty()) {
            try {
                throw new NoHayEscenariosException("ERROR: No hay escenarios disponibles.");
            } catch (NoHayEscenariosException e) {
                System.out.println(e.getMessage());
                return;  // Salir del método si no hay escenarios
            }
        }

        // Ordenar los escenarios por ID
        List<Escenario> listaEscenarios = new ArrayList<>(escenarios);
        listaEscenarios.sort(Comparator.comparingInt(Escenario::getIdEscenario));

        // Mostrar los escenarios
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

        try {
            // Verificar si existen escenarios antes de continuar
            if (escenarios.isEmpty()) {
                throw new NoHayEscenariosException("ERROR: No hay escenarios disponibles para eliminar.");
            }

            System.out.println("Ingrese el ID del escenario a eliminar:");
            int idAEliminar = 0;
            boolean idValido = false;

            while (!idValido) {
                try {
                    idAEliminar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    idValido = true;  // Si no hay excepción, el ID es válido
                } catch (InputMismatchException e) {
                    System.out.println("ERROR: El ID debe ser un número válido. Intente nuevamente.");
                    scanner.nextLine(); // Limpiar el buffer para evitar bucles infinitos
                }
            }

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
                OperacionLecturaEscritura.escenariosToArchivo(escenarios); //Guardar los cambios en el archivo
            } else {
                System.out.println("ERROR: No se encontró un escenario con ese ID.");
            }

        } catch (NoHayEscenariosException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void modificarEscenario() {
        HashSet<Escenario> escenarios = OperacionLecturaEscritura.archivoToEscenarios();
        Scanner scanner = new Scanner(System.in);

        try {
            // Verificar si hay escenarios en el conjunto
            if (escenarios.isEmpty()) {
                throw new NoHayEscenariosException("ERROR: No hay escenarios disponibles.");
            }

            int id = 0;
            boolean idValido = false;

            while (!idValido) {
                System.out.println("Ingrese el ID del escenario a modificar.");

                try {
                    id = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    idValido = true;
                } catch (InputMismatchException e) {
                    System.out.println("ERROR: El ID debe ser un numero valido. Intente nuevamente.");
                    scanner.nextLine(); // Limpiar el buffer
                }
            }

            // Lanzamos una excepción personalizada si no se encuentra el escenario
            Escenario escenarioModificar = null;
            for (Escenario e : escenarios) {
                if (e.getIdEscenario() == id) {
                    escenarioModificar = e;
                    break;
                }
            }

            try {
                if (escenarioModificar == null) {
                    throw new EscenarioNoEncontradoException("ERROR: No se encontró un escenario con ese ID.");
                }

                int opcion = 0;
                boolean opcionValida = false;

                while (!opcionValida) {
                    System.out.println("1. Modificar descripción");
                    System.out.println("2. Modificar opciones");
                    System.out.println("3. Salir");

                    try {
                        opcion = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer

                        if (opcion < 1 || opcion > 3) {
                            throw new OpcionInvalidaException("Por favor ingrese una opción válida.");
                        } else {
                            opcionValida = true;
                        }
                    } catch (OpcionInvalidaException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR: Debe ingresar un número válido.");
                        scanner.nextLine(); // Limpiar el buffer
                    }
                }

                // Modificar el escenario basado en la opción seleccionada
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

                // Guardar los cambios en el archivo
                OperacionLecturaEscritura.escenariosToArchivo(escenarios);
                System.out.println("Escenario actualizado.");

            } catch (EscenarioNoEncontradoException e) {
                System.out.println(e.getMessage()); // Manejar la excepción personalizada
            }

        } catch (NoHayEscenariosException e) {
            System.out.println(e.getMessage()); // Manejar la excepción personalizada para no haber escenarios
        }
    }

    private static Opcion crearOpcion(Scanner scanner) {
        System.out.println("Ingrese el título de la opción:\n");
        String titulo = scanner.nextLine().trim();
        System.out.println("Ingrese la descripcion de la opcion\n:");
        String descOpcion = scanner.nextLine().trim();

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
                return new Opcion(titulo,descOpcion, vidaModificar);

            case 2:

                System.out.println("Ingrese el nombre del objeto:");
                String nombreObjeto = scanner.nextLine().trim();

                System.out.println("Ingrese el código del objeto:");
                int codigoObjeto = obtenerEntero(scanner);

                Objeto objAux = new Objeto(nombreObjeto, codigoObjeto);
                return new Opcion(titulo,descOpcion, objAux);

            case 3:
                System.out.println("Ingrese el nombre del consumible:");
                String nombreConsumible = scanner.nextLine().trim();

                System.out.println("Ingrese la salud recuperada con el consumible:");
                int saludRecuperada = obtenerEntero(scanner);

                Consumible consumible = new Consumible(nombreConsumible, saludRecuperada);
                return new Opcion(titulo,descOpcion, consumible);

            default:
                throw new IllegalStateException("Opción inválida. Este error no debería ocurrir.");
        }
    }

    public static void verUsuarios(ManejoUsuarios manejoUsuarios) {
        // Verificar si no hay usuarios
        if (manejoUsuarios.getJugadores().isEmpty()) {
            try {
                throw new NoHayUsuariosException("ERROR: No hay usuarios disponibles.");
            } catch (NoHayUsuariosException e) {
                System.out.println(e.getMessage());
                return;  // Salir del método si no hay usuarios
            }
        }

        // Si hay usuarios, los mostramos
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
            OperacionLecturaEscritura.jugadoresToArchivo(manejoUsuarios.getJugadores());
        } else {
            System.out.println("ERROR: Usuario no encontrado.");
        }
    }

    public static void modificarUsuario(ManejoUsuarios manejoUsuarios) {
        Scanner scanner = new Scanner(System.in);
        String nombreUsuario = "";

        // Validar que el nombre de usuario no esté vacío
        while (nombreUsuario.isEmpty()) {
            System.out.println("Ingrese el nombre del usuario a modificar:");
            nombreUsuario = scanner.nextLine().trim();

            if (nombreUsuario.isEmpty()) {
                System.out.println("ERROR: El nombre de usuario no puede estar vacío. Intente nuevamente.");
            }
        }

        Usuario usuario = manejoUsuarios.getJugadores().get(nombreUsuario);

        if (usuario != null) {
            boolean salir = false;

            while (!salir) {
                System.out.println("¿Qué desea modificar?");
                System.out.println("1. Modificar nombre de usuario");
                System.out.println("2. Modificar contraseña");
                System.out.println("3. Volver atrás");
                System.out.print("Seleccione una opción: ");

                try {
                    int opcion = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    switch (opcion) {
                        case 1:
                            String nuevoNombreUsuario = "";
                            boolean nombreValido = false;

                            // Validar que el nuevo nombre de usuario no esté vacío
                            while (!nombreValido) {
                                System.out.println("Ingrese el nuevo nombre de usuario:");
                                nuevoNombreUsuario = scanner.nextLine().trim();

                                if (nuevoNombreUsuario.isEmpty()) {
                                    System.out.println("ERROR: El nombre de usuario no puede estar vacío.");
                                } else if (manejoUsuarios.getJugadores().containsKey(nuevoNombreUsuario)) {
                                    System.out.println("ERROR: El nombre de usuario ya está en uso.");
                                } else {
                                    nombreValido = true;
                                }
                            }

                            // Cambiar el nombre de usuario si es válido
                            manejoUsuarios.getJugadores().remove(nombreUsuario); // Eliminar el usuario antiguo
                            usuario.setNombreUsuario(nuevoNombreUsuario); // Cambiar el nombre del usuario
                            manejoUsuarios.getJugadores().put(nuevoNombreUsuario, usuario); // Agregar el usuario con el nuevo nombre
                            OperacionLecturaEscritura.jugadoresToArchivo(manejoUsuarios.getJugadores());
                            System.out.println("Nombre de usuario actualizado.");
                            break;

                        case 2:
                            String nuevaContrasenia = "";
                            boolean contraseniaValida = false;

                            // Validar que la contraseña no esté vacía
                            while (!contraseniaValida) {
                                System.out.println("Ingrese la nueva contraseña:");
                                nuevaContrasenia = scanner.nextLine().trim();

                                if (nuevaContrasenia.isEmpty()) {
                                    System.out.println("ERROR: La contraseña no puede estar vacía.");
                                } else {
                                    contraseniaValida = true;
                                }
                            }

                            // Cambiar la contraseña si es válida
                            usuario.setContrasenia(nuevaContrasenia);
                            OperacionLecturaEscritura.jugadoresToArchivo(manejoUsuarios.getJugadores());
                            System.out.println("Contraseña actualizada.");
                            break;

                        case 3:
                            salir = true;
                            System.out.println("Regresando al menú anterior...");
                            break;

                        default:
                            System.out.println("Opción inválida. Intente nuevamente");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Opción inválida. Por favor, ingrese un número.");
                    scanner.nextLine(); // Limpiar el buffer
                }
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
