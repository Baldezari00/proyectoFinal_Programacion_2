package com.example.proyectofinalprogii;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class controladorJuego {
    // en esta clase se va a manejar la parte visual del juego y su control de acciones.
    // (ej: elegir opcion, utilizar item)
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Bienvenido al juego!");
    }
}