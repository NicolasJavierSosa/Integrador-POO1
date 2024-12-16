package com.example.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.example.App;
import com.example.modelo.Categoria;
import com.example.modelo.Copia;
import com.example.modelo.Libro;
import com.example.modelo.Tipo;
import com.example.servicio.Servicio;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class librosBibliotecarioController {

    @FXML
    private TableView<Libro> TablaLibros;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnLibros;

    @FXML
    private Button btnMultas;

    @FXML
    private Button btnPrestam;

    @FXML
    private Button btnRegNuevo;

    @FXML
    private Button btnUsu;

    @FXML
    private TableColumn<Libro, String> columAutor;

    @FXML
    private TableColumn<Libro, Categoria> columCat;

    @FXML
    private TableColumn<Libro, Integer> columCopias;

    @FXML
    private TableColumn<Libro, Integer> columDispo;

    @FXML
    private TableColumn<Libro, String> columISBN;

    @FXML
    private TableColumn<Copia, Integer> columRack;

    @FXML
    private TableColumn<Libro, String> columTitulo;

    @FXML
    private TableColumn<Copia, Tipo> columtipo;

    @FXML
    private ComboBox<String> menuBtnAutor;

    @FXML
    private ComboBox<String> menuBtnCat;

    @FXML
    private ComboBox<String> menuBtnEditorial;

    @FXML
    private ComboBox<String> menuBtnIdioma;

    @FXML
    private ComboBox<String> menuBtnRack;

    @FXML
    private MenuButton menuUsu;

    @FXML
    private TextField txtBuscar;

    Servicio servicio;


    @FXML
    public void initialize() throws IOException {
        servicio = App.getServicio();
        configurarTabla();
        configurarComboBox();
        configurarAutoCompletar();
        cargarLibros();
    }

    private void configurarTabla() {
        columISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        columTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        columAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        columCat.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        columCopias.setCellValueFactory(new PropertyValueFactory<>("nrcopias"));
        columDispo.setCellValueFactory(new PropertyValueFactory<>("cantDisponible"));
        columRack.setCellValueFactory(new PropertyValueFactory<>("rack"));
    }
    private void configurarComboBox() throws IOException { 
        List<Categoria> categorias = servicio.obtenerCategorias(); 
        List<String> nombresCategorias = categorias.stream() 
                                        .map(Categoria::getCategoria) 
                                        .collect(Collectors.toList()); 
        menuBtnCat.setItems(FXCollections.observableArrayList(nombresCategorias)); 
        menuBtnCat.getItems().add(null); 
    }
    private void configurarAutoCompletar() throws IOException {
        List<Libro> libros = servicio.obtenerLibros();
        List<String> titulos = libros.stream()
                                .map(Libro::getTitulo)
                                .collect(Collectors.toList());
        //ContextMenu para las sugerencias
        ContextMenu contextMenu = new ContextMenu();

        // Agregar un Listener al TextField
        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
        contextMenu.getItems().clear(); // Limpiar sugerencias previas

        if (!newValue.isEmpty()) {
            List<String> coincidencias = titulos.stream()
                                                .filter(titulo -> titulo.toLowerCase().contains(newValue.toLowerCase()))
                                                .collect(Collectors.toList());

            // Crear los ítems del ContextMenu
            for (String coincidencia : coincidencias) {
                MenuItem item = new MenuItem(coincidencia);
                item.setOnAction(event -> txtBuscar.setText(coincidencia)); // Setear el texto seleccionado
                contextMenu.getItems().add(item);
            }
            // Mostrar el ContextMenu si hay coincidencias
            if (!coincidencias.isEmpty()) {
                contextMenu.show(txtBuscar, javafx.geometry.Side.BOTTOM, 0, 0);
            } else {
                contextMenu.hide();
            }
        } else {
            contextMenu.hide();
        }
    });

        // Ocultar el ContextMenu al perder el foco
        txtBuscar.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) {
                contextMenu.hide();
            }
        });
}
    @FXML
    private void cargarLibros() throws IOException {
        List<Libro> libros = servicio.obtenerLibros();
        TablaLibros.setItems(FXCollections.observableArrayList(libros));
    }

    @FXML
    void buscar(ActionEvent event) throws IOException {
        String titulo = txtBuscar.getText().trim().isEmpty() ? null : txtBuscar.getText().trim().toLowerCase();
        String categoria = menuBtnCat.getValue();
        List<Libro> resultados = filtrarLibros(titulo, categoria);
        TablaLibros.setItems(FXCollections.observableArrayList(resultados));
    }

    private List<Libro> filtrarLibros(String titulo, String categoria) throws IOException {
        return servicio.obtenerLibros().stream()
            .filter(libro -> (titulo == null || libro.getTitulo().equalsIgnoreCase(titulo)) &&
                            (categoria == null || (libro.getCategString() != null && libro.getCategString().contains(categoria))))
            .collect(Collectors.toList());
    }
 

}
