package com.example.controles;

import java.io.IOException;

import com.example.App;
import com.example.repositorio.Repositorio;
import com.example.servicio.Servicio;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class SesionController {

    @FXML
    private Button BtnIniciarSesion;

    @FXML
    private PasswordField TxtCon;

    @FXML
    private Label btnRegistro;

    @FXML
    private TextField txtUsu;
    
    @FXML
    private void eventKey(KeyEvent evento) {
        // Lógica para eventos de tecla si es necesario
    }

    private Servicio servicio;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Biblioteca");
    Repositorio repositorio = new Repositorio(emf);
    public SesionController() {
        this.servicio = new Servicio(repositorio);
    }

    @FXML
    private void iniciarSesion(ActionEvent evento) throws IOException {
        // Verifica si el evento es el del botón de inicio de sesión
        if (evento.getSource().equals(BtnIniciarSesion)) {
            // Verifica si los campos de nombre de usuario y contraseña no están vacíos
            String nombreUsuario = txtUsu.getText();
            String contraseña = TxtCon.getText();

            // Verifica si los campos están vacíos
            if (nombreUsuario.isEmpty() || contraseña.isEmpty()) {
                mostrarMensaje("Por favor ingrese nombre de usuario y contraseña", AlertType.WARNING);
                return; // Sale del método si los campos están vacíos
            }

            // Llama al servicio para autenticar al miembro
            String rol = servicio.autenticarMiembro(nombreUsuario, contraseña);

            if (rol != null) {
                // Redirige según el rol del miembro
                if (rol.equals("bibliotecario")) {
                    redirigirPantalla("inicioBibliotecario"); // Redirige a la pantalla de inicio de Bibliotecario
                } else if (rol.equals("usuario")) {
                    redirigirPantalla("inicioUsuario"); // Redirige a la pantalla de inicio de Usuario
                }
            } else {
                // Muestra mensaje de error si no se encuentra al usuario o usuario o contraseña son incorrectos
                mostrarMensaje("Usuario o contraseña incorrectos", AlertType.ERROR);
            }
        }
    }

    private void redirigirPantalla(String nombrePantalla) throws IOException {
        // Este método cambiará la vista a la pantalla indicada
        App.setRoot(nombrePantalla);  // App.setRoot es el método que maneja la carga de FXML
    }

    private void mostrarMensaje(String mensaje, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
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
    }
}
