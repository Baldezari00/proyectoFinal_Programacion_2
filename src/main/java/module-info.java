module com.example.proyectofinalprogii {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.json;
    requires jdk.compiler;

    opens com.example.proyectofinalprogii to javafx.fxml;
    exports com.example.proyectofinalprogii;
    exports com.example.proyectofinalprogii.Juego;
    opens com.example.proyectofinalprogii.Juego to javafx.fxml;
}