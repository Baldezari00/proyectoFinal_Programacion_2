package com.example.proyectofinalprogii.Juego;
import com.example.proyectofinalprogii.Main;
import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Usuario;
import com.example.proyectofinalprogii.Usuario.Mochila.Consumible;
import com.example.proyectofinalprogii.Usuario.Mochila.Item;
import com.example.proyectofinalprogii.Usuario.Mochila.Mochila;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

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
    // mochila
    @FXML
    private Button mochilaBoton;
    @FXML
    private VBox contenedorItems;
    private Boolean toggleMochila=false; // false = no se ven los items
    private Usuario jugadorLocal;
    private Stage stage;


    //inicializar mochila y texto bienvenida
    @FXML
    public void initialize() {
        if (jugadorLocal != null) {
            agregarTextoBienvenida();

        } else {
            System.out.println("El jugador local no está inicializado aún.");
        }
    }


// cargar en la vbox los elementos
  /*  public void cargarItemsEnVBox() {
        contenedorItems.getChildren().clear(); // Limpiar elementos previos
        for (Item item : jugadorLocal.getMochila().getItems()) {
            Label itemLabel = crearRepresentacionItem(item);
            contenedorItems.getChildren().add(itemLabel);
        }
    }

    // presentacion de cada item dentro de la vbox
   public Label crearRepresentacionItem(Item item) {
        Label labelItem = new Label(String.valueOf(item.mostrarItem()));
        labelItem.setWrapText(true);
        return labelItem;
    }


   */

    public void cargarItemsEnVBox() {
        contenedorItems.getChildren().clear(); // Limpiar elementos previos
        for (Item item : jugadorLocal.getMochila().getItems()) {
            VBox itemInteractivo = crearRepresentacionItemInteractiva(item);
            contenedorItems.getChildren().add(itemInteractivo);
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
            jugadorLocal.consumir(consumible); // Método que actualiza atributos del personaje
            jugadorLocal.getMochila().removerItem(item); // Elimina el ítem de la mochila
            cargarItemsEnVBox(); // Actualiza la interfaz
        } else {
            System.out.println("No se puede usar este tipo de ítem.");
        }
    }



    // accion del boton mochila
    @FXML
    protected void mostrarItems(){
        if(toggleMochila){
            contenedorItems.setVisible(false);
            toggleMochila = false;
        }else{
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
            stage.setScene(new Scene(root,1200,720));
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
        AtomicBoolean eleccionHecha = new AtomicBoolean(false);

        Escenario escenario = jugadorLocal.getEscenarios().iterator().next();
        notificadorVida.setText("");
        // textos
        historiaLabel.setText(escenario.getDescripcion());
        opcion1.setText(escenario.getOpcion1().getConsecuenciaTitulo());
        opcion2.setText(escenario.getOpcion2().getConsecuenciaTitulo());


            opcion1.setOnAction(actionEvent -> {
                if(!eleccionHecha.get()){
                    eleccionHecha.set(true);
                    String descripcion = escenario.getOpcion1().accionDeOpcion(jugadorLocal,"algo paso y ganaste ",escenario.getOpcion1().getVidaAModificar());
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
                }

            });

            opcion2.setOnAction(actionEvent -> {
                if(!eleccionHecha.get()){
                    eleccionHecha.set(true);
                    String descripcion = escenario.getOpcion2().accionDeOpcion(jugadorLocal,"algo paso y perdiste ",escenario.getOpcion2().getVidaAModificar());
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