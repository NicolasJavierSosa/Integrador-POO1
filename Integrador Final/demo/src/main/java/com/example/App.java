package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.repositorio.Repositorio;
import com.example.servicio.Servicio;

import jakarta.persistence.Persistence;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private static Scene scene;
    private static Servicio servicio;

    @Override
    public void start(Stage stage) throws IOException {
        var emf = Persistence.createEntityManagerFactory("Biblioteca");
        servicio = new Servicio(new Repositorio(emf));


        scene = new Scene(loadFXML("registro"), 900, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}