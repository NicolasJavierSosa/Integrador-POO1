module integrador.definitivo {
    requires javafx.controls;
    requires javafx.fxml;

    opens integrador.definitivo to javafx.fxml;
    exports integrador.definitivo;
}
