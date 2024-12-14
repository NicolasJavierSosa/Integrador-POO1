package com.example;

import java.io.IOException;

import com.example.repositorio.Repositorio;
import com.example.servicio.Servicio;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private static Scene scene;
    private static Servicio servicio;
    private static EntityManagerFactory emf;

    @Override
    public void start(Stage stage) {
        try {
            emf = Persistence.createEntityManagerFactory("Biblioteca");
            servicio = new Servicio(new Repositorio(emf));
    
            Parent root = loadFXML("sesion");
            scene = new Scene(root, 900, 600); // Inicializa escena correctamente
    
            stage.setTitle("Proyecto integrador POO 1");
            stage.setScene(scene); // Asocia la escena al stage
            stage.show(); // Muestra la ventana principal
        } catch (IOException e) {
            System.err.println("Fallo al cargar archivo FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Servicio getServicio(){
        return servicio;
    } 
    
    public static void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        if (scene == null) {
            scene = new Scene(root); // Inicializa la escena si aún no está configurada
        } else {
            scene.setRoot(root); // Cambia solo la raíz si ya existe
        }
    }
    

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    

    public static void main(String[] args) {
        launch();
    }

}