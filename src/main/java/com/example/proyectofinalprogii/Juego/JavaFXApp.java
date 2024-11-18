package com.example.proyectofinalprogii.Juego;

import com.example.proyectofinalprogii.Main;
import com.example.proyectofinalprogii.Juego.ManejoInicio.Inicio;
import com.example.proyectofinalprogii.OperacionesBasicasJSON.OperacionLecturaEscritura;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Usuario;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class JavaFXApp extends Application {
    private static boolean isLaunched = false;
    private static Usuario usuarioActivo; // Usuario activo

    // elementos de la ventana de bienvenida





    public static void setUsuarioActivo(Usuario usuario) {
        JavaFXApp.usuarioActivo = usuario;
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Si la ventana ya está lanzada, simplemente la reutilizamos
        if (isLaunched) {

            stage.show();

            return; // Salimos sin crear una nueva ventana
        }

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("vista-juego.fxml"));
        Scene scene = new Scene(loader.load(), 1200, 720);

        controladorJuego controlador = loader.getController();
        controlador.setJugadorLocal(usuarioActivo);
        controlador.setStage(stage);

        stage.setTitle("¡Juego de Aventura!");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {

            // guardamos los datos
            OperacionLecturaEscritura.jugadoresToArchivo(Inicio.getManejoJugadores().getJugadores());
            System.out.println("Cerrando ventana gráfica...");
            stage.close();
            Platform.exit();
        });

        stage.show();
        isLaunched = true; // Marcamos que la interfaz se ha lanzado
    }

    public static void mostrarInterfaz() {

        // Si la interfaz ya está lanzada, no la lanzamos de nuevo
        if (!isLaunched) {
            launch(); // Lanza la aplicación si no está lanzada
        } else {
            Platform.runLater(() -> {
                // Aquí puedes gestionar el contenido de la interfaz si ya está lanzada
                System.out.println("La interfaz ya está lanzada.");
            });
        }
    }

    public static boolean isLaunched() {
        return isLaunched;
    }

}
