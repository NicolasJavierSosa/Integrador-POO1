package com.example.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import com.example.App;
import com.example.modelo.Autor;
import com.example.modelo.Categoria;
import com.example.modelo.Copia;
import com.example.modelo.Libro;
import com.example.modelo.Rack;
import com.example.modelo.Tipo;
import com.example.servicio.Servicio;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class librosBibliotecarioController {

    @FXML
    private ComboBox<Autor> CbAutor;

    @FXML
    private ComboBox<Categoria> CbCat;

    @FXML
    private TableView<Libro> TablaLibros;

    @FXML
    private Button btnAñadirAutor;

    @FXML
    private Button btnAñadirCat;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnImportImg;

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnLibros;

    @FXML
    private Button btnModif;

    @FXML
    private Button btnMultas;

    @FXML
    private Button btnPrestam;

    @FXML
    private Button btnUsu;

    @FXML
    private TableColumn<Libro, Autor> columAutor;

    @FXML
    private TableColumn<Libro, Categoria> columCat;

    @FXML
    private TableColumn<Libro, Integer> columCopias;

    @FXML
    private TableColumn<Libro, Integer> columDispo;

    @FXML
    private TableColumn<Libro, Long> columISBN;

    @FXML
    private TableColumn<Copia, Rack> columRack;

    @FXML
    private TableColumn<Libro, String> columTitulo;

    @FXML
    private TableColumn<Copia, Tipo> columtipo;

    @FXML
    private ImageView importImg;

    @FXML
    private ListView<Copia> listaCopias;

    @FXML
    private ComboBox<Autor> menuBtnAutor;

    @FXML
    private ComboBox<Categoria> menuBtnCat;

    @FXML
    private ComboBox<String> menuBtnEditorial;

    @FXML
    private ComboBox<String> menuBtnIdioma;

    @FXML
    private ComboBox<Rack> menuBtnRack;

    @FXML
    private MenuButton menuUsu;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TextArea txtDesc;

    @FXML
    private TextField txtEditorial;

    @FXML
    private TextField txtISBN;

    @FXML
    private TextField txtIdioma;

    @FXML
    private TextField txtTitulo;
    
    @FXML
    private Button btnSalir;
    
    private Alert alerta;
    
    private Servicio servicio;


    @FXML
    void cancelarRegistro(ActionEvent event) {

    }

    @FXML
    void configurarAutoCompletar(ActionEvent event) {

    }

    @FXML
    void irAinicio(ActionEvent event) {

    }

    @FXML
    void irAmultas(ActionEvent event) {

    }

    @FXML
    void irAprestamos(ActionEvent event) {

    }

    @FXML
    void irAusuarios(ActionEvent event) {

    }

    @FXML
    void registrarLibro(ActionEvent event) {

    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        try{
            alerta = new Alert(AlertType.CONFIRMATION);
            alerta.setTitle("Cerrar sesion");
            alerta.setHeaderText(null);
            alerta.setContentText("Está segur@ que desea salir?");
            Optional<ButtonType> option = alerta.showAndWait();

            if(option.get().equals(ButtonType.OK)){
                App.setRoot("sesion");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    public void seleccionarCat(){
        try {
        // Obtener las categorías desde la base de datos
        List<Categoria> categorias = servicio.obtenerCategorias();

        // Convertir la lista de categorías a un ObservableList y cargarla en el ComboBox
        menuBtnCat.setItems(FXCollections.observableArrayList(categorias));


        // Establecer cómo se debe mostrar cada categoría en el ComboBox
        menuBtnCat.setCellFactory(param -> new ListCell<Categoria>() {
            @Override
            protected void updateItem(Categoria item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getCategoria());  // Mostrar el nombre de la categoría
                }
            }
        });

        // Establecer el texto mostrado en el botón del ComboBox
        menuBtnCat.setButtonCell(new ListCell<Categoria>() {
            @Override
            protected void updateItem(Categoria item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getCategoria());  // Mostrar el nombre de la categoría en el botón del ComboBox
                }
            }
        });
        
        CbCat.setItems(FXCollections.observableArrayList(categorias));

        // Establecer cómo se debe mostrar cada categoría en el ComboBox
        CbCat.setCellFactory(param -> new ListCell<Categoria>() {
            @Override
            protected void updateItem(Categoria item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getCategoria());  // Mostrar el nombre de la categoría
                }
            }
        });

        // Establecer el texto mostrado en el botón del ComboBox
        CbCat.setButtonCell(new ListCell<Categoria>() {
            @Override
            protected void updateItem(Categoria item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getCategoria());  // Mostrar el nombre de la categoría en el botón del ComboBox
                }
            }
        });

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar las categorías.");
    }

    }

    @FXML
    public void initialize() throws IOException {
        servicio = App.getServicio();
        insertarCategoria();
        // Llamar al método seleccionarCat() que obtiene las categorías
        seleccionarCat();


        /*
        servicio = App.getServicio();
        configurarTabla();
        //configurarComboBox();
        configurarAutoCompletar();
        cargarLibros();*/
    }
    public void insertarCategoria() {
        try {
            // Crear nuevas instancias de la clase Categoria
            Categoria categoria1 = new Categoria("Ficción");
            Categoria categoria2 = new Categoria("Ciencia");
            Categoria categoria3 = new Categoria("Historia");
    
            // Usar el servicio para agregar las categorías a la base de datos
            servicio.agregarCategoria(categoria1);
            servicio.agregarCategoria(categoria2);
            servicio.agregarCategoria(categoria3);
    
            // Confirmación
            System.out.println("Categorías insertadas correctamente.");
        } catch (IllegalArgumentException e) {
            // Manejar errores en caso de que los datos sean inválidos
            System.err.println("Error al insertar categorías: " + e.getMessage());
        } catch (Exception e) {
            // Manejar otros errores no esperados
            e.printStackTrace();
            System.err.println("Error inesperado al insertar categorías.");
        }
    }
/*     @FXML
    private void configurarTabla() {
        columISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        columTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        columAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        columCat.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        columCopias.setCellValueFactory(new PropertyValueFactory<>("nrcopias"));
        columDispo.setCellValueFactory(new PropertyValueFactory<>("cantDisponible"));
        columRack.setCellValueFactory(new PropertyValueFactory<>("rack"));
    }/* 
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
        Categoria categoria = menuBtnCat.getValue();
        List<Libro> resultados = filtrarLibros(titulo, categoria);
        TablaLibros.setItems(FXCollections.observableArrayList(resultados));
    }

    private List<Libro> filtrarLibros(String titulo, Categoria categoria) throws IOException {
            return servicio.obtenerLibros().stream()
                .filter(libro -> (titulo == null || libro.getTitulo().equalsIgnoreCase(titulo)) &&
                                (categoria == null || (libro.getCategString() != null && libro.getCategString().contains(categoria))))
            .collect(Collectors.toList());
    }

    */
}
