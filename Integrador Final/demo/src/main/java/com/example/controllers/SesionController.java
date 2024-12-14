package com.example.controllers;

import java.io.IOException;

import com.example.App;
import com.example.modelo.Miembro;
import com.example.servicio.Servicio;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

 /*   EntityManagerFactory emf = Persistence.createEntityManagerFactory("Biblioteca");
    Repositorio repositorio = new Repositorio(emf);
    public SesionController() {
        this.servicio = new Servicio(repositorio);
    }*/ 
/* 
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
*/
 /*  @FXML
    public void initialize() throws IOException{
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

    @FXML
    void Ingresar(ActionEvent event) throws IOException{
        servicio.autenticarMiembro(txtUsu.getText(), TxtCon.getText());
    }
    //REGISTRO
    @FXML
    private void registrarUsuario(ActionEvent evento) throws IOException {
        App.setRoot("registro"); // Esto cambiará a la pantalla de registro
    }*/  


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
            Miembro miembro1 = new Miembro("biancaa", "Bianca", "12345678", "Usuario", "bianca@gmail.com");
            Miembro miembro2 = new Miembro("biblitecario", "no Hernan", "12345678", "Bibliotecario", "Bibliotecario@gmail.com");
            servicio.agregarMiembro(miembro1);
            servicio.agregarMiembro(miembro2);

        }
        catch(Exception e){
        }
    }
        @FXML
    void Ingresar(ActionEvent event) throws IOException{
        servicio.autenticarMiembro(txtUsu.getText(), TxtCon.getText());
        
    }
}
