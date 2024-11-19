package com.example.proyectofinalprogii.Juego;
import com.example.proyectofinalprogii.Main;
import com.example.proyectofinalprogii.Juego.ManejoInicio.Inicio;
import com.example.proyectofinalprogii.OperacionesBasicasJSON.OperacionLecturaEscritura;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Personaje.Adulto;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Personaje.Joven;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Usuario;
import com.example.proyectofinalprogii.Usuario.Mochila.Consumible;
import com.example.proyectofinalprogii.Usuario.Mochila.Item;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

public class controladorJuego {
    // en esta clase se va a manejar la parte visual del juego y su control de acciones.
    // (ej: elegir opcion, utilizar item)


    @FXML
    private Label bienvenida;

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
    @FXML
    private Label siguienteNotificador;
    @FXML
    private Button curaRapida;
    @FXML
    private Label estadisticaVidaGanada;
    @FXML
    private Label reiniciarPartidaAviso;

    // para saber si eligió una opcion y puede pulsar siguiente
    AtomicBoolean eleccionHecha = new AtomicBoolean(false);

    @FXML
    private Label gananciaItemLabel;
    // guardar partida y salir
    @FXML
    private Button guardarPartida;
    @FXML
    private Label avisoGuardado;
    @FXML
    private Label salir;
    @FXML
    private Button siSalir;
    @FXML
    private Button noSalir;
    @FXML
    private Label vidaActual;
    // detectar si ya perdio/gano y vió las estadisticas
    boolean flagGanarPerder = false;
    // mochila
    @FXML
    private Button mochilaBoton;
    @FXML
    private VBox contenedorItems;
    private Boolean toggleMochila=false; // false = no se ven los items
    // jugador
    private Usuario jugadorLocal;
    private Stage stage;

    // para cerrar la ventana
    private Runnable onCloseRequest;

    public void setOnCloseRequest(Runnable onCloseRequest) {
        this.onCloseRequest = onCloseRequest;
    }

    // ventana inicio
    public void cargarVentana(){
        if(!(bienvenida==null)){
            if(jugadorLocal.getPersonajeElegido() instanceof Joven){
                bienvenida.setText("Bienvenido "+jugadorLocal.getNombreUsuario()+"\nPersonaje: Joven (vida máxima 150)\nVida actual: "+jugadorLocal.getPersonajeElegido().getVida()+"hp");

            }else if(jugadorLocal.getPersonajeElegido() instanceof Adulto){
                bienvenida.setText("Bienvenido "+jugadorLocal.getNombreUsuario()+"\nPersonaje: Adulto (vida máxima 120)\nVida actual: "+jugadorLocal.getPersonajeElegido().getVida()+"hp");

            }else{
                bienvenida.setTextFill(Paint.valueOf("red"));
                bienvenida.setText("Bienvenido "+jugadorLocal.getNombreUsuario()+"\nPersonaje: Viejo (vida máxima 80)\nVida actual: "+jugadorLocal.getPersonajeElegido().getVida()+"hp");
            }
        }
    }

    //mochila

    public void cargarItemsEnVBox() {
        contenedorItems.getChildren().clear(); // Limpiar elementos previos
        if(jugadorLocal.getMochila().getItems().isEmpty()){
            Label notificadorMochilaVacia =  new Label("no tienes items para mostrar aún");
            contenedorItems.getChildren().add(notificadorMochilaVacia);
            contenedorItems.setAlignment(Pos.TOP_CENTER);
            vidaActual.setText("vida actual: "+jugadorLocal.getPersonajeElegido().getVida()+"hp");
        }else{
            for (Item item : jugadorLocal.getMochila().getItems()) {
                VBox itemInteractivo = crearRepresentacionItemInteractiva(item);
                contenedorItems.getChildren().add(itemInteractivo);
            }

            vidaActual.setText("vida actual: "+jugadorLocal.getPersonajeElegido().getVida()+"hp");

        }
        if(jugadorLocal.getPersonajeElegido().getVida()>=50){
            vidaActual.setTextFill(Paint.valueOf("green"));
        }else{
            vidaActual.setTextFill(Paint.valueOf("red"));
        }
    }



    public VBox crearRepresentacionItemInteractiva(Item item) {
        Label labelItem = new Label(String.valueOf(item.mostrarItem()));
        Button btnUsar = new Button("Usar");

        labelItem.setWrapText(true);

        btnUsar.setOnAction(event -> {
            // Lógica para usar el ítem
            usarItem(item);
        });

        VBox contenedorItem = new VBox(10, labelItem, btnUsar); // 1er Separación entre elementos
        contenedorItem.setStyle("-fx-padding: 10; -fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 5;");

        // Cambia el color de fondo según el tipo de ítem
        if (item instanceof Consumible) {
            contenedorItem.setStyle(contenedorItem.getStyle() + "-fx-background-color: #d4edda;"); // Verde claro
        } else {
            contenedorItem.setStyle(contenedorItem.getStyle() + "-fx-background-color: #f8d7da;"); // Rojo claro
        }

        contenedorItem.setAlignment(Pos.CENTER);


        return contenedorItem;
    }

    // logica de uso
    public void usarItem(Item item) {
        if (item instanceof Consumible) {
            Consumible consumible = (Consumible) item;
            if(jugadorLocal.consumir(consumible)==0){ // devuelve 0 cuando tiene la vida al maximo y el objeto suma vida
                notificadorVida.setText("ya tienes la vida al maximo, no puedes utilizar este consumible");
            }else {
                jugadorLocal.getMochila().removerItem(item); // Elimina el ítem de la mochila
                cargarItemsEnVBox(); // Actualiza la interfaz
                notificadorVida.setText("utilizaste "+item.getNombre()+" \ntienes "+jugadorLocal.getPersonajeElegido().getVida()+"hp");
            }

        } else {
            notificadorVida.setTextFill(Paint.valueOf("black"));
            notificadorVida.setText("Has utilizado "+item.getNombre()+"!");
            jugadorLocal.getMochila().removerItem(item);
            cargarItemsEnVBox();
        }
    }



    // accion del boton mochila
    @FXML
    protected void mostrarItems(){
        if(toggleMochila){
            contenedorItems.setVisible(false);
            toggleMochila = false;
            vidaActual.setVisible(false);
        }else{
            vidaActual.setVisible(true);


            cargarItemsEnVBox();
            contenedorItems.setVisible(true);
            toggleMochila = true;
        }

    }


    // getter y setter

    public Usuario getJugadorLocal() {
        return jugadorLocal;
    }

    public void setJugadorLocal(Usuario jugadorLocal) {
        this.jugadorLocal = jugadorLocal;
        cargarVentana();
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }


    // acciones de botones

    @FXML
    protected void jugar(){
        Platform.runLater(() -> {
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

            // Cambiar la escena del Stage
            stage.setTitle("Juego Aventura en Acción!");
            stage.setScene(new Scene(root,1200,720));
            stage.show();

        } catch (IOException io) {
            io.printStackTrace();
        }
        });
    }
    private void inicializarEscena() {
        if (jugadorLocal != null && jugadorLocal.getEscenarios() != null) {
            // boton de curacion
            if(jugadorLocal.getPersonajeElegido() instanceof Joven || jugadorLocal.getPersonajeElegido() instanceof Adulto){
                curaRapida.setVisible(true);
            }

            historiaLabel.setWrapText(true);  // Esto permite el ajuste de texto

            if(jugadorLocal.getEscenarios().iterator().hasNext()){
                cargarEscenario();
                siguiente.setOnAction(actionEvent -> {
                    if(eleccionHecha.get()){
                        if(jugadorLocal.getEscenarios().iterator().hasNext()) {
                            cargarEscenario();
                        }else{
                            cargarEscenarioPerdedorGanador("Ganaste el juego !");
                        }
                    }else{
                        siguienteNotificador.setVisible(true);
                        //Timeline para esconder texto
                        Timeline timeline = new Timeline(
                                new KeyFrame(
                                        Duration.seconds(2),
                                        event -> siguienteNotificador.setVisible(false)
                                )
                        );
                        timeline.setCycleCount(1); // Se ejecuta una sola vez
                        timeline.play(); // Inicia el temporizador

                    }

                });

            }else{
                cargarEscenarioPerdedorGanador("Ganaste el juego !");
            }


        } else {
            historiaLabel.setText("No se encontró un escenario inicial.");
        }
    }


    public void cargarEscenario(){
        eleccionHecha.set(false);

        Escenario escenario = jugadorLocal.obtenerEscenarioRandom();
        notificadorVida.setText("");
        gananciaItemLabel.setText("");
        // textos
        historiaLabel.setText(escenario.getDescripcion());
        opcion1.setText(escenario.getOpcion1().getConsecuenciaTitulo());
        opcion2.setText(escenario.getOpcion2().getConsecuenciaTitulo());


        // setear la accion de cada boton
            opcion1.setOnAction(actionEvent -> {
                if(!eleccionHecha.get()){
                    eleccionHecha.set(true);
                    accionDeOpcion(escenario,escenario.getOpcion1());
                    // remover escenario solo al presionar opcion, porque sino cuando salga de la partida se le va a remover sin haber jugado
                    jugadorLocal.getEscenarios().remove(escenario);
                }

            });

            opcion2.setOnAction(actionEvent -> {
                if(!eleccionHecha.get()){
                    eleccionHecha.set(true);
                    accionDeOpcion(escenario,escenario.getOpcion2());
                    // remover escenario solo al presionar opcion, porque sino cuando salga de la partida se le va a remover sin haber jugado
                    jugadorLocal.getEscenarios().remove(escenario);

                }

            });

    }


    public void accionDeOpcion(Escenario escenario, Opcion opcion) {
        String descripcion = opcion.getDescripcionDeOpcion();

        // Verificar si se gana un consumible o un objeto antes de acceder a sus métodos
        if (opcion.getConsumibleGanado() != null || opcion.getObjetoGanado() != null) {
            if (opcion.getConsumibleGanado() != null) {
                if (jugadorLocal.getMochila().agregarItem(opcion.getConsumibleGanado()) == 0) {
                    historiaLabel.setText(descripcion);
                    gananciaItemLabel.setText("Encontraste " + opcion.getConsumibleGanado().getNombre() + ". ¡Pero tienes la mochila llena!");
                } else {
                    historiaLabel.setText(descripcion);
                    gananciaItemLabel.setText("Encontraste " + opcion.getConsumibleGanado().getNombre());
                    cargarItemsEnVBox();
                }
            } else if (opcion.getObjetoGanado() != null) {
                if (jugadorLocal.getMochila().agregarItem(opcion.getObjetoGanado()) == 0) {
                    historiaLabel.setText(descripcion);
                    gananciaItemLabel.setText("Encontraste " + opcion.getObjetoGanado().getNombre() + ". ¡Pero tienes la mochila llena!");
                } else {
                    historiaLabel.setText(descripcion);
                    gananciaItemLabel.setText("Encontraste " + opcion.getObjetoGanado().getNombre());
                    cargarItemsEnVBox();
                }
            }

            gananciaItemLabel.setTextFill(Paint.valueOf("green"));
            notificadorVida.setText("");

        } else {
            // No se gana ningún item, solo se modifica la vida
            historiaLabel.setText(descripcion);
            if (jugadorLocal.getPersonajeElegido().cambiarVida(opcion.getVidaAModificar()) == 0) {
                notificadorVida.setTextFill(Paint.valueOf("green"));
                notificadorVida.setText("Has ganado " + opcion.getVidaAModificar() + "hp pero ya tienes vida máxima: " + jugadorLocal.getPersonajeElegido().getVida() + "hp");
            } else {
                if (jugadorLocal.getPersonajeElegido().getVida() <= 0) {
                    cargarEscenarioPerdedorGanador("Has perdido el juego...");
                } else {
                    if (opcion.getVidaAModificar() > 0) {
                        notificadorVida.setText("Has ganado " + opcion.getVidaAModificar() + "hp. Te quedan " + jugadorLocal.getPersonajeElegido().getVidaString() + "hp restantes.");
                    } else {
                        notificadorVida.setText("Has perdido " + Math.abs(opcion.getVidaAModificar()) + "hp. Te quedan " + jugadorLocal.getPersonajeElegido().getVidaString() + "hp restantes.");
                    }
                    if (jugadorLocal.getPersonajeElegido().getVida() < 50) {
                        notificadorVida.setTextFill(Paint.valueOf("red"));
                    } else {
                        notificadorVida.setTextFill(Paint.valueOf("green"));
                    }
                }
            }
            if (jugadorLocal.getPersonajeElegido().getVida() >= 50) {
                vidaActual.setTextFill(Paint.valueOf("green"));
            } else {
                vidaActual.setTextFill(Paint.valueOf("red"));
            }
            vidaActual.setText("Vida actual: " + jugadorLocal.getPersonajeElegido().getVida() + "hp");
        }
    }



    // curacion rapida
    @FXML
    protected void curacionRapida(){
        notificadorVida.setText("");
        if(jugadorLocal.getPersonajeElegido() instanceof Joven){
            if( ((Joven) jugadorLocal.getPersonajeElegido()).CuraRapida()==0){
                notificadorVida.setText("no puedes usar cura rapida, tienes la vida maxima "+jugadorLocal.getPersonajeElegido().getVida()+"hp");
            }else{
                if(jugadorLocal.getPersonajeElegido().getVida()>=50){
                    notificadorVida.setTextFill(Paint.valueOf("green"));
                }else{notificadorVida.setTextFill(Paint.valueOf("red"));}
                notificadorVida.setText("usaste cura rapida, tienes "+jugadorLocal.getPersonajeElegido().getVida()+"hp");
                curaRapida.setVisible(false);
            }

        }else {
            if( ((Adulto) jugadorLocal.getPersonajeElegido()).CuraRapida()==0){
                notificadorVida.setText("no puedes usar cura rapida, tienes la vida maxima "+jugadorLocal.getPersonajeElegido().getVida()+"hp");
            }else{
                if(jugadorLocal.getPersonajeElegido().getVida()>=50){
                    notificadorVida.setTextFill(Paint.valueOf("green"));
                }else{notificadorVida.setTextFill(Paint.valueOf("red"));}
                notificadorVida.setText("usaste cura rapida, tienes "+jugadorLocal.getPersonajeElegido().getVida()+"hp");
                curaRapida.setVisible(false);
            }

        }
        if(jugadorLocal.getPersonajeElegido().getVida()>=50){
            vidaActual.setTextFill(Paint.valueOf("green"));
        }else{
            vidaActual.setTextFill(Paint.valueOf("red"));
        }
        vidaActual.setText("vida actual: "+jugadorLocal.getPersonajeElegido().getVida()+"hp");

    }

    // guardar partida
    @FXML
    protected void guardarDatos() {
        try {
            OperacionLecturaEscritura.jugadoresToArchivo(Inicio.getManejoJugadores().getJugadores());
            avisoGuardado.setText("Partida guardada!");
            avisoGuardado.setTextFill(Paint.valueOf("green"));



        } catch (Exception e) {
            e.printStackTrace();
            avisoGuardado.setText("Error guardando datos");
            avisoGuardado.setTextFill(Paint.valueOf("red"));
        }finally {
            salir.setVisible(true);
            siSalir.setVisible(true);
            noSalir.setVisible(true);
        }

        // Crear una Timeline para limpiar el texto después de 5 segundos
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(2),
                        event -> avisoGuardado.setText("") // Limpiar el texto
                )
        );
        timeline.setCycleCount(1); // Se ejecuta una sola vez
        timeline.play(); // Inicia el temporizador

        noSalir.setOnAction(actionEvent -> {
            salir.setVisible(false);
            siSalir.setVisible(false);
            noSalir.setVisible(false);
        });

        siSalir.setOnAction(actionEvent -> {
                System.out.println("Cerrando ventana gráfica...");
                stage.close();
                Platform.exit();
        });
    }


    // ganar y perder, escenarios

    public void cargarEscenarioPerdedorGanador(String texto){
        //cargarle nuevamente los escenarios
        HashSet<Escenario> escenarios = OperacionLecturaEscritura.archivoToEscenarios();
        jugadorLocal.setEscenarios(escenarios);
        jugadorLocal.getMochila().getItems().clear();

        historiaLabel.setText(texto);
        opcion1.visibleProperty().set(false);
        opcion2.visibleProperty().set(false);
        notificadorVida.visibleProperty().set(false);
        mochilaBoton.setVisible(false);
        curaRapida.setVisible(false);
        contenedorItems.setVisible(false);
        guardarPartida.setVisible(false);
        gananciaItemLabel.setVisible(false);
        vidaActual.setVisible(false);
        reiniciarPartidaAviso.setVisible(true);

        siguiente.setText("ver estadisticas");
        siguiente.setOnAction(actionEvent -> {
            siguiente.setText("salir");
            if(flagGanarPerder){
                stage.close();
                Platform.exit();
            }else{
                flagGanarPerder = true;
                estadisticaVidaGanada.setTextFill(Paint.valueOf("black"));
                estadisticaVidaGanada.setText("Vida total ganada: "+jugadorLocal.getPersonajeElegido().getVidaTotalGanada()+"hp");

                notificadorVida.visibleProperty().set(true);
                notificadorVida.setTextFill(Paint.valueOf("black"));
                notificadorVida.setText("Vida total perdida: "+jugadorLocal.getPersonajeElegido().getVidaTotalPerdida()+"hp");
                // mostrar estadisticas como cant de opciones elegidas
            }

        });

    }



}