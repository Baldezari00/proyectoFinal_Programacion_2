package com.example.proyectofinalprogii.Juego;

import com.example.proyectofinalprogii.Main;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
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
    @FXML
    private Label notificadorVida;
    @FXML
    private Button siguiente;
    private Usuario jugadorLocal;
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

    public Usuario getJugadorLocal() {
        return jugadorLocal;
    }

    public void setJugadorLocal(Usuario jugadorLocal) {
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

            if(jugadorLocal.getEscenarios().iterator().hasNext()){
              cargarEscenario();
              siguiente.setOnAction(actionEvent -> {
                  if(jugadorLocal.getEscenarios().iterator().hasNext()) {
                      cargarEscenario();
                  }else{
                     cargarEscenarioGanador();
                  }
              });

            }else{
               cargarEscenarioGanador();
            }


        } else {
            historiaLabel.setText("No se encontró un escenario inicial.");
        }
    }


    public void cargarEscenario(){

            Escenario escenario = jugadorLocal.getEscenarios().iterator().next();
            notificadorVida.setText("");
            // textos
            historiaLabel.setText(escenario.getDescripcion());
            opcion1.setText(escenario.getOpcion1().getConsecuenciaTitulo());
            opcion2.setText(escenario.getOpcion2().getConsecuenciaTitulo());

            opcion1.setOnAction(actionEvent -> {
                String descripcion = escenario.getOpcion1().accionDeOpcion(jugadorLocal,"algo paso y ganaste "+escenario.getOpcion1().getVidaAModificar()+"hp");
                if(jugadorLocal.getPersonajeElegido().getVida()<=0){
                    historiaLabel.setText(descripcion+"\nperdiste el juego...");
                    historiaLabel.setTextFill(Paint.valueOf("red"));
                    notificadorVida.setText("");

                }else{
                    historiaLabel.setText(descripcion);
                    notificadorVida.setText("te quedan "+jugadorLocal.getPersonajeElegido().getVidaString()+"hp restantes");


                    if(jugadorLocal.getPersonajeElegido().getVida()<50){
                        notificadorVida.setTextFill(Paint.valueOf("red"));
                    }else {
                        notificadorVida.setTextFill(Paint.valueOf("green"));
                    }
                }

            });

            opcion2.setOnAction(actionEvent -> {
                String descripcion = escenario.getOpcion2().accionDeOpcion(jugadorLocal,"algo paso y perdiste "+escenario.getOpcion2().getVidaAModificar()+"hp");
                if(jugadorLocal.getPersonajeElegido().getVida()<=0){
                    historiaLabel.setText(descripcion+"\nperdiste el juego...");
                    historiaLabel.setTextFill(Paint.valueOf("red"));
                    notificadorVida.setText("");

                }else{
                    historiaLabel.setText(descripcion);
                    notificadorVida.setText("te quedan "+jugadorLocal.getPersonajeElegido().getVidaString()+"hp restantes");


                    if(jugadorLocal.getPersonajeElegido().getVida()<50){
                        notificadorVida.setTextFill(Paint.valueOf("red"));

                    }else {
                        notificadorVida.setTextFill(Paint.valueOf("green"));
                    }


                }

            });


            // remover escenario
            jugadorLocal.getEscenarios().remove(escenario);

    }

    public void cargarEscenarioGanador(){
        historiaLabel.setText("Felicidades, ganaste el juego!\n");
        opcion1.visibleProperty().set(false);
        opcion2.visibleProperty().set(false);
        notificadorVida.visibleProperty().set(false);

        siguiente.setText("ver estadisticas");
        siguiente.setOnAction(actionEvent -> {
            // mostrar estadisticas como cant de opciones elegidas
        });

    }


}