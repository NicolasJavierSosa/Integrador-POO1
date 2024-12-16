package com.example.controllers;

import java.io.IOException;

import com.example.App;

import javafx.event.ActionEvent;

public class inicioBibliotecarioController {
    public void irAlibros(ActionEvent event) throws IOException{
        App.setRoot("librosBibliotecario");
    }
}
