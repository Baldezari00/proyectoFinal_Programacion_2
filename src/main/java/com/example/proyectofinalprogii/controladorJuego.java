package com.example.proyectofinalprogii;

import com.example.proyectofinalprogii.Usuario.Manejo_Usuario.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class controladorJuego {
    // en esta clase se va a manejar la parte visual del juego y su control de acciones.
    // (ej: elegir opcion, utilizar item)
    @FXML
    private Label welcomeText;
    private Usuario jugadorLocal;


    // getter y setter

    public Usuario getJugadorLocal() {
        return jugadorLocal;
    }

    public void setJugadorLocal(Usuario jugadorLocal) {
        this.jugadorLocal = jugadorLocal;
    }

    // acciones de botones
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Bienvenido al juego "+jugadorLocal.getNombreUsuario()+" !");
    }
}