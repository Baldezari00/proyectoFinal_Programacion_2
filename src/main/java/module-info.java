module com.example.proyectofinalprogii {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.json;

    opens com.example.proyectofinalprogii to javafx.fxml;
    exports com.example.proyectofinalprogii;
}