package com.example.controllers;

import java.io.IOException;

import com.example.App;
import com.example.servicio.Servicio;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SesionController {

    @FXML
    private Button BtnIniciarSesion;

    @FXML
    private PasswordField TxtCon;

    @FXML
    private Label btnRegistro;

    @FXML
    private TextField txtUsu;
    

    private Servicio servicio;

    public void Ingresar(ActionEvent event) {
        try {
            servicio.autenticarMiembro(txtUsu.getText(), TxtCon.getText());
        } catch (IOException e) {
            mostrarMensajeError("Error inesperado durante la autenticación.");
        }
    } 

    @FXML
    public void initialize() throws IOException{
        servicio = App.getServicio();
        try{
            txtUsu.setTextFormatter(new javafx.scene.control.TextFormatter<>(change -> {
                if (change.getText().contains(" ")) {
                    return null; // Bloquea los espacios
                }
                return change;
            }));
        
            TxtCon.setTextFormatter(new javafx.scene.control.TextFormatter<>(change -> {
                if (change.getText().contains(" ")) {
                    return null; // Bloquea los espacios
                }
                return change;
            }));
        /*     Miembro miembro1 = new Miembro("biancaa", "Bianca", "12345678", "Usuario", "bianca@gmail.com");
            Miembro miembro2 = new Miembro("bibliotecario", "no Hernan", "12345678", "Bibliotecario", "Bibliotecario@gmail.com");
            servicio.agregarMiembro(miembro1);
            servicio.agregarMiembro(miembro2);*/

        }
        catch(Exception e){
        }
    }

    // Método auxiliar para mostrar mensajes de error en JavaFX
    private void mostrarMensajeError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
