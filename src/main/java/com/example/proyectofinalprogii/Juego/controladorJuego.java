package com.example.proyectofinalprogii.Juego;

import com.example.proyectofinalprogii.Main;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Jugador;
import com.example.proyectofinalprogii.Usuario.Mochila.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class controladorJuego {
    // en esta clase se va a manejar la parte visual del juego y su control de acciones.
    // (ej: elegir opcion, utilizar item)
    @FXML
    private Label welcomeText;

    @FXML
    private Label historiaLabel;
    @FXML
    private Button opcion1;
    @FXML
    private Button opcion2;
    private Jugador jugadorLocal;
    private Stage stage;

    //inicializar
    @FXML
    public void initialize() {
        if (historiaLabel != null) {
            historiaLabel.setText("Iniciando...");
        } else {
            System.out.println("historiaLabel no está inicializado");
        }
    }



    // getter y setter

    public Jugador getJugadorLocal() {
        return jugadorLocal;
    }

    public void setJugadorLocal(Jugador jugadorLocal) {
        this.jugadorLocal = jugadorLocal;
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void agregarTextoBienvenida() {
        welcomeText.setText("Bienvenido al juego "+jugadorLocal.getNombreUsuario()+" !\n¿confirmas que quieres iniciar tu aventura?");
    }
    // acciones de botones

    @FXML
    protected void jugar(){
        try {
            // Cargar el archivo FXML de la nueva escena
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("juego.fxml"));
            Parent root = fxmlLoader.load();

            // Obtener el controlador de la nueva escena
            controladorJuego nuevoControlador = fxmlLoader.getController();

            // Transferir datos al nuevo controlador
            nuevoControlador.setStage(stage); // Mantener el mismo Stage
            nuevoControlador.setJugadorLocal(jugadorLocal); // Pasar al jugador
            nuevoControlador.inicializarEscena(); // Configurar datos iniciales en la nueva ventana
            // cargar textos
          /*  Escenario escenario = jugadorLocal.getEscenarios().iterator().next();
                historiaLabel.setText(escenario.getDescripcion());
                opcion1.setText(escenario.getOpcion1().getConsecuenciaTitulo());
                opcion2.setText(escenario.getOpcion2().getConsecuenciaTitulo());
            */

            // Cambiar la escena del Stage
            stage.setTitle("Juego Aventura en Acción!");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private void inicializarEscena() {
        if (jugadorLocal != null && jugadorLocal.getEscenarios() != null) {
            historiaLabel.setWrapText(true);  // Esto permite el ajuste de texto

            if(!jugadorLocal.getEscenarios().isEmpty()){
                Escenario escenario = jugadorLocal.getEscenarios().iterator().next();
                historiaLabel.setText(escenario.getDescripcion());
                opcion1.setText(escenario.getOpcion1().getConsecuenciaTitulo());
                opcion2.setText(escenario.getOpcion2().getConsecuenciaTitulo());
                jugadorLocal.getEscenarios().remove(escenario);
            }else{
                historiaLabel.setText("Felicidades, ganaste el juego!\n");
                // mostrar estadisticas como cant de opciones elegidas
            }


        } else {
            historiaLabel.setText("No se encontró un escenario inicial.");
        }
    }

 /*   @FXML
    protected void accionDeOpcion1(){
        jugadorLocal.getPersonajeElegido().cambiarVida(vidaAModificar);
        if(jugador.getPersonajeElegido().getVida()<=0){
            return descripcionDeOpcion+"\nperdiste el juego\n";
            // mostrar estadisticas como cant de opciones elegidas

        }else{
            return descripcionDeOpcion;
        }

    }
*/

}