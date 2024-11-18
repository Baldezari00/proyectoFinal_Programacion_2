package com.example.proyectofinalprogii.Juego;
import com.example.proyectofinalprogii.Main;
import com.example.proyectofinalprogii.OperacionesBasicasJSON.Inicio;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Usuario;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXApp extends Application {
    private static Usuario usuarioActivo; // Variable estática para pasar el usuario

    public static void setUsuarioActivo(Usuario usuario) {
        usuarioActivo = usuario;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Platform.runLater(() -> {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vista-juego.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 720);

            controladorJuego controlador = fxmlLoader.getController();
            controlador.setJugadorLocal(usuarioActivo);
            controlador.setStage(stage);

            stage.setTitle("¡Juego de Aventura!");
            stage.setScene(scene);

            // Evento al cerrar la ventana
            stage.setOnCloseRequest(event -> {
                stage.close();
                System.out.println("Cerrando ventana gráfica...");
                if(usuarioActivo.getEsAdmin()){
                    Inicio.mostrarMenuAdmin(usuarioActivo);
                }

            });

            stage.show();
        } catch (Exception e) {
            System.out.println("Ocurrió un error al iniciar la interfaz gráfica: " + e.getMessage());
            e.printStackTrace();
        }
        });
    }

}
