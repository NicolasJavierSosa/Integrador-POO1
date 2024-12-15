package com.example.controllers;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.example.App;
import com.example.modelo.Miembro;
import com.example.modelo.Usuario;
import com.example.servicio.Servicio;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistroController {

    @FXML
    private Button BtnInicioSesion;

    @FXML
    private Button BtnRegistro;

    @FXML
    private PasswordField TxtCon;

    @FXML
    private TextField TxtDirec;

    @FXML
    private TextField TxtCorreo;

    @FXML
    private TextField txtApe;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtUsu;

    private Servicio servicio;

    @FXML
    public void initialize() throws IOException{
        servicio = App.getServicio();

        // Bloqueo de espacios en ciertos campos
        txtUsu.setTextFormatter(new javafx.scene.control.TextFormatter<>(change -> change.getText().contains(" ") ? null : change));
        TxtCon.setTextFormatter(new javafx.scene.control.TextFormatter<>(change -> change.getText().contains(" ") ? null : change));

        // Evento para el botón de registro
        BtnRegistro.setOnAction(event -> onRegistrar());

        // Evento para el botón de inicio de sesión
        BtnInicioSesion.setOnAction(event -> handleInicioSesion());
    }

    @FXML
    public void onRegistrar() {
        String nombre = txtNom.getText();
        String apellido = txtApe.getText();
        String usuario = txtUsu.getText();
        String contraseña = TxtCon.getText();
        String correo = TxtCorreo.getText();
        String direc = TxtDirec.getText();
        String rol = "Usuario";

        if (nombre.isEmpty() || apellido.isEmpty() || usuario.isEmpty() || contraseña.isEmpty()) {
            // Mostrar mensaje de error
            System.out.println("Todos los campos deben estar llenos.");
        return;
        }

        try {
            if(nombre.equals(null)|| nombre.equals("")|| nombre.length()<3){
                JOptionPane.showMessageDialog(null, "El usuario ingresado no es válido");
                throw new IllegalArgumentException("Usuario no válido.");
            }else if(apellido.equals(null)|| apellido.equals("")){
                JOptionPane.showMessageDialog(null, "Debe ingresar un apellido válido");
                throw new IllegalArgumentException("Error en apellido.");
            }else if(contraseña.equals(null)|| contraseña.equals("")){
                JOptionPane.showMessageDialog(null, "Debe ingresar una contraseña válida");
                throw new IllegalArgumentException("Error en contraseña.");
            }else if(correo.equals(null)|| correo.equals(" ")){
                JOptionPane.showMessageDialog(null, "Por favor ingrese un correo");
                throw new IllegalArgumentException("Error en correo.");
            }else if (direc.equals(null)|| direc.equals(" ")){
                JOptionPane.showMessageDialog(null, "Por favor ingrese una dirección");
                throw new IllegalArgumentException("Error en dirección.");
            }else{
                Miembro nuevoMiembro = new Usuario(usuario, nombre, contraseña, rol, correo, direc);
                servicio.registrarUsuario(nuevoMiembro);
                limpiarCampos();
                App.setRoot("inicioUsuario");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al registrar el usuario.");
        }
    }

    private void handleInicioSesion() {
        try {
            App.setRoot("sesion"); // Cambia a la pantalla de inicio de sesión
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la pantalla de inicio de sesión", AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String contenido, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }

    private void limpiarCampos() {
        txtNom.clear();
        txtApe.clear();
        txtUsu.clear();
        TxtCon.clear();
        TxtDirec.clear();
        TxtCorreo.clear();
    }

    public void iniciarSesion(ActionEvent event) throws IOException{
        App.setRoot("sesion");

    }
}
