package com.example;

import java.io.IOException;

import com.example.repositorio.Repositorio;
import com.example.servicio.Servicio;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
        try{
            emf = Persistence.createEntityManagerFactory("Biblioteca");
            servicio = new Servicio(new Repositorio(emf));
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("sesion.fxml"));
            scene = new Scene(fxmlLoader.load(), 900, 600);
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e){
            System.err.println("Fallo al cargar archivo fxml: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static Servicio getServicio(){
        return servicio;
    }

    public static FXMLLoader setRoot(String fxml) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        scene.setRoot(fxmlLoader.load());
        return fxmlLoader;

    }

    public static void main(String[] args) {
        launch();
    }

}