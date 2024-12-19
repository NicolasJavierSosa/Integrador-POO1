package com.example.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.example.App;
import com.example.modelo.Autor;
import com.example.modelo.Categoria;
import com.example.modelo.Copia;
import com.example.modelo.Estado;
import com.example.modelo.Libro;
import com.example.modelo.Rack;
import com.example.modelo.Tipo;
import com.example.servicio.Servicio;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class librosBibliotecarioController {

    @FXML
    private ComboBox<Autor> CbAutor;

    @FXML
    private ComboBox<Categoria> CbCat;

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
    private TableView<Libro> TablaLibros;

    @FXML
    private TableColumn<Libro, Long> columISBN;

    @FXML
    private TableColumn<Libro, String> columTitulo;

    @FXML
    private TableColumn<Libro, String> columEditorial;

    @FXML
    private TableColumn<Libro, String> columCat;

    @FXML
    private TableColumn<Libro, String> columAutor;
    @FXML
    private TableColumn<Libro, Integer> columCopias;

    @FXML
    private ImageView importImg;

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
    @FXML
    private TableView<Copia> tablaCopias;
    @FXML
    private TableColumn<Copia, String> columnId;
    @FXML
    private TableColumn<Copia, String> columTipo;

    @FXML
    private TableColumn<Copia, String> columRackCopia;

    private Alert alerta;

    private Servicio servicio;

    List<Autor> autores = new ArrayList<>();
    List<Categoria> categorias = new ArrayList<>();

    @FXML
    void cancelarRegistro(ActionEvent event) {

    }

    @FXML
    void configurarAutoCompletar(ActionEvent event) {

    }

    @FXML
    void irAinicio(ActionEvent event) throws IOException {
        App.setRoot("inicioBibliotecario");
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
    void cerrarSesion(ActionEvent event) {
        try {
            alerta = new Alert(AlertType.CONFIRMATION);
            alerta.setTitle("Cerrar sesion");
            alerta.setHeaderText(null);
            alerta.setContentText("Está segur@ que desea salir?");
            Optional<ButtonType> option = alerta.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                App.setRoot("sesion");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void seleccionarIdioma() {
        try {
            // Obtener la lista de libros desde la base de datos
            List<Libro> libros = servicio.obtenerLibros();
            // Extraer los idiomas de los libros, asegurando que no haya duplicados
            Set<String> idiomasUnicos = libros.stream()
                    .map(Libro::getIdioma) // Extraer el atributo idioma
                    .filter(Objects::nonNull) // Filtrar nulos para evitar errores
                    .collect(Collectors.toSet()); // Usar un Set para eliminar duplicados
            // Convertir el Set a una lista observable y cargarla en el ComboBox
            ObservableList<String> idiomasObservable = FXCollections.observableArrayList(idiomasUnicos);
            menuBtnIdioma.setItems(idiomasObservable);
            // Establecer cómo se deben mostrar los idiomas en el ComboBox
            menuBtnIdioma.setCellFactory(param -> new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);  // Mostrar el nombre del idioma
                    }
                }
            });
            // Establecer el texto mostrado en el botón del ComboBox
            menuBtnIdioma.setButtonCell(new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);  // Mostrar el nombre del idioma en el botón del ComboBox
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los Idiomas.");
        }
    }

    @FXML
    public void seleccionarEditorial() {
        try {
            // Obtener la lista de libros desde la base de datos
            List<Libro> libros = servicio.obtenerLibros();

            // Extraer las editoriales de los libros, asegurando que no haya duplicados
            Set<String> editorialesUnicas = libros.stream()
                    .map(Libro::getEditorial) // Extraer el atributo editorial
                    .filter(Objects::nonNull) // Filtrar nulos para evitar errores
                    .collect(Collectors.toSet()); // Usar un Set para eliminar duplicados

            // Convertir el Set a una lista observable y cargarla en el ComboBox
            ObservableList<String> editorialesObservable = FXCollections.observableArrayList(editorialesUnicas);
            menuBtnEditorial.setItems(editorialesObservable);

            // Establecer cómo se deben mostrar las editoriales en el ComboBox
            menuBtnEditorial.setCellFactory(param -> new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);  // Mostrar el nombre de la editorial
                    }
                }
            });

            // Establecer el texto mostrado en el botón del ComboBox
            menuBtnEditorial.setButtonCell(new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);  // Mostrar el nombre de la editorial
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar las Editoriales.");
        }
    }

    @FXML
    public void seleccionarCat() {
        try {
            // Obtener las categorías desde la base de datos
            List<Categoria> categorias = servicio.obtenerCategorias();

            // Filtrar las categorías para que no se repitan
            Set<String> nombresUnicos = new HashSet<>();
            List<Categoria> categoriasUnicas = categorias.stream()
                    .filter(categoria -> nombresUnicos.add(categoria.getCategoria()))
                    .collect(Collectors.toList()); // Usamos Collectors.toList() en lugar de toList()
            // Convertir la lista de categorías únicas a un ObservableList y cargarla en el ComboBox
            menuBtnCat.setItems(FXCollections.observableArrayList(categoriasUnicas));
            // Establecer cómo se debe mostrar cada categoría en el ComboBox
            menuBtnCat.setCellFactory(param -> new ListCell<Categoria>() {
                @Override
                protected void updateItem(Categoria item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getCategoria()); // Mostrar el nombre de la categoría
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
                        setText(item.getCategoria()); // Mostrar el nombre de la categoría en el botón del ComboBox
                    }
                }
            });
            // Hacer lo mismo para el otro ComboBox
            CbCat.setItems(FXCollections.observableArrayList(categoriasUnicas));
            CbCat.setCellFactory(param -> new ListCell<Categoria>() {
                @Override
                protected void updateItem(Categoria item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getCategoria()); // Mostrar el nombre de la categoría
                    }
                }
            });

            CbCat.setButtonCell(new ListCell<Categoria>() {
                @Override
                protected void updateItem(Categoria item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getCategoria()); // Mostrar el nombre de la categoría en el botón del ComboBox
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar las categorías.");
        }
    }

    @FXML
    public void seleccionarAutor() {
        try {
            // Obtener los autores desde la base de datos
            List<Autor> autores = servicio.obtenerAutores();
            // Filtrar los autores para que no se repitan
            Set<String> nombresUnicos = new HashSet<>();
            List<Autor> autoresUnicos = autores.stream()
                    .filter(autor -> nombresUnicos.add(autor.getNombre()))
                    .collect(Collectors.toList());
            // Convertir la lista de autores únicos a un ObservableList y cargarla en el ComboBox
            menuBtnAutor.setItems(FXCollections.observableArrayList(autoresUnicos));
            // Establecer cómo se debe mostrar cada autor en el ComboBox
            menuBtnAutor.setCellFactory(param -> new ListCell<Autor>() {
                @Override
                protected void updateItem(Autor item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getNombre()); // Mostrar el nombre del autor
                    }
                }
            });

            // Establecer el texto mostrado en el botón del ComboBox
            menuBtnAutor.setButtonCell(new ListCell<Autor>() {
                @Override
                protected void updateItem(Autor item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getNombre()); // Mostrar el nombre del autor en el botón del ComboBox
                    }
                }
            });

            // Hacer lo mismo para el otro ComboBox
            CbAutor.setItems(FXCollections.observableArrayList(autoresUnicos));
            CbAutor.setCellFactory(param -> new ListCell<Autor>() {
                @Override
                protected void updateItem(Autor item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getNombre()); // Mostrar el nombre del autor
                    }
                }
            });

            CbAutor.setButtonCell(new ListCell<Autor>() {
                @Override
                protected void updateItem(Autor item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getNombre()); // Mostrar el nombre del autor en el botón del ComboBox
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los autores.");
        }
    }

    public void registrarCategoria(ActionEvent event) {
        servicio = App.getServicio();
        try {
            // Crear el cuadro de diálogo para ingresar los datos del la categoria
            TextInputDialog nombreDialog = new TextInputDialog();
            nombreDialog.setTitle("Registrar Categoria");
            nombreDialog.setHeaderText("Ingrese la nueva categoria");
            nombreDialog.setContentText("Nombre de la categoria:");

            // Mostrar el diálogo y capturar el nombre
            Optional<String> nombreInput = nombreDialog.showAndWait();
            if (!nombreInput.isPresent() || nombreInput.get().isBlank()) {
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Debe ingresar el nombre de la categoria.");
                alerta.showAndWait();
                return;
            }
            String nombreCateg = nombreInput.get();
            // Crear el objeto Categoria y registra en la base de datos
            Categoria nuevaCateg = new Categoria(nombreCateg);
            // Llamar al servicio para registrar la categoria en la base de datos
            servicio.agregarCategoria(nuevaCateg);
            // Actualizar el ComboBox con las categorias más recientes
            seleccionarCat();
            // Mostrar una confirmación al usuario
            Alert confirmacion = new Alert(Alert.AlertType.INFORMATION, "Categoria registrada correctamente.");
            confirmacion.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alerta = new Alert(Alert.AlertType.ERROR, "Ocurrió un error al registrar la nueva Categoria.");
            alerta.showAndWait();
        }
    }

    @FXML
    public void registrarAutor(ActionEvent event) {
        servicio = App.getServicio();
        try {
            // Crear el cuadro de diálogo para ingresar los datos del autor
            TextInputDialog nombreDialog = new TextInputDialog();
            nombreDialog.setTitle("Registrar Autor");
            nombreDialog.setHeaderText("Ingrese los datos del autor");
            nombreDialog.setContentText("Nombre del Autor:");
            // Mostrar el diálogo y capturar el nombre
            Optional<String> nombreInput = nombreDialog.showAndWait();
            if (!nombreInput.isPresent() || nombreInput.get().isBlank()) {
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Debe ingresar el nombre del autor.");
                alerta.showAndWait();
                return;
            }
            String nombreAutor = nombreInput.get();
            // Crear otro cuadro de diálogo para la nacionalidad
            TextInputDialog nacionalidadDialog = new TextInputDialog();
            nacionalidadDialog.setTitle("Registrar Autor");
            nacionalidadDialog.setHeaderText("Ingrese los datos del autor");
            nacionalidadDialog.setContentText("Nacionalidad del Autor:");
            // Mostrar el diálogo y capturar la nacionalidad
            Optional<String> nacionalidadInput = nacionalidadDialog.showAndWait();
            if (!nacionalidadInput.isPresent() || nacionalidadInput.get().isBlank()) {
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Debe ingresar la nacionalidad del autor.");
                alerta.showAndWait();
                return;
            }
            String nacionalidadAutor = nacionalidadInput.get();
            // Crear el objeto Autor y registrarlo en la base de datos
            Autor nuevoAutor = new Autor(nombreAutor, nacionalidadAutor);
            // Llamar al servicio para registrar el autor en la base de datos
            servicio.agregarAutor(nuevoAutor);
            // Actualizar el ComboBox con los autores más recientes
            seleccionarAutor();
            // Mostrar una confirmación al usuario
            Alert confirmacion = new Alert(Alert.AlertType.INFORMATION, "Autor registrado correctamente.");
            confirmacion.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alerta = new Alert(Alert.AlertType.ERROR, "Ocurrió un error al registrar el autor.");
            alerta.showAndWait();
        }
    }

    public void registrarLibro() throws IOException {
        // Obtener el autor seleccionado del ComboBox
        Autor autorSeleccionado = CbAutor.getValue();
        Categoria categoriaSeleccionada = CbCat.getValue();

        // Validar si se seleccionó un autor y agregarlo a la lista
        if (autorSeleccionado != null && !autores.contains(autorSeleccionado)) {
            autores.add(autorSeleccionado);
        }
        if(categoriaSeleccionada != null && !categorias.contains(categoriaSeleccionada)){
            categorias.add(categoriaSeleccionada);
        }
        if (txtISBN.getText().equals("") || txtISBN.getText() == null) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una editorial");
        } else if (txtIdioma.getText().equals("") || txtIdioma.getText() == null) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un idioma");
        } else if (txtEditorial.getText().equals("") || txtEditorial.getText() == null) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un isbn");
        } else if (txtTitulo.getText().equals("") || txtTitulo.getText() == null) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un titulo");
        } else if (autores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe al menos un autor");
        } else if (categorias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe al menos una categoria");
        } else {
            String editorial = txtEditorial.getText();
            String idioma = txtIdioma.getText();
            long isbn = Long.parseLong(txtISBN.getText());
            String titulo = txtTitulo.getText();
            String descripcion = txtDesc.getText();
            // Obtener las copias registradas en la TableView
            List<Copia> copias = new ArrayList<>(tablaCopias.getItems());

            // Crear el libro con las copias asociadas
            Libro newLibro = new Libro(isbn, titulo, editorial, idioma, descripcion, categorias, autores, "img");
            Object[] opciones = {"Si", "No"};
            int seleccion = JOptionPane.showOptionDialog(null, "¿Esta seguro que quiere agregar este Libro?", "¿Está seguro?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, 1);
            if (seleccion == 0) {
                servicio.agregarLibro(newLibro);
                inicializarTabla();
            }
        }
    }

    public void registrarCopia() throws IOException {
        App.setRoot("registrarCopia");
    }

    public void tablaCopias() throws IOException {
        Libro libro = TablaLibros.getSelectionModel().getSelectedItem();
        
        if(libro == null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar un Libro de la tabla.");
        }
        else{
            servicio.guardarSelectedCopia(tablaCopias.getSelectionModel().getSelectedItem());
            columnId.setCellValueFactory(new PropertyValueFactory<>("idCopiaString"));
            columTipo.setCellValueFactory(new PropertyValueFactory<>("tipoString"));
            columRackCopia.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            cargarCopias();
        }
    }

    @FXML
    private void cargarCopias() throws IOException {
        List<Copia> copiasLista = servicio.obtenerCopias();
        ObservableList<Copia> observableList = FXCollections.observableArrayList(copiasLista);
        tablaCopias.setItems(observableList);
    }

    @FXML
    public void inicializarTabla() throws IOException {
        // Configuración de las columnas directamente
        columISBN.setCellValueFactory(new PropertyValueFactory<>("idString"));
        columTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        columEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        columCat.setCellValueFactory(new PropertyValueFactory<>("categString"));
        columAutor.setCellValueFactory(new PropertyValueFactory<>("autoresString"));
        columCopias.setCellValueFactory(new PropertyValueFactory<>("nrCopias"));
        cargar(); // Llamada para cargar los datos
    }

    @FXML
    private void cargar() throws IOException {
        // Obtén la lista de libros desde el servicio
        List<Libro> librosLista = servicio.obtenerLibros();
        // Si la lista es null o vacía, muestra una tabla vacía
        if (librosLista == null || librosLista.isEmpty()) {
            TablaLibros.setItems(null);
        } else {
            // Asigna la lista directamente a la tabla
            TablaLibros.getItems().setAll(librosLista);
        }
    }

    @FXML
    public void initialize() throws IOException {
        servicio = App.getServicio();
        insertarCategoria();
        insertarLibros();
        seleccionarCat();
        seleccionarAutor();
        seleccionarEditorial();
        seleccionarIdioma();
        inicializarTabla();
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

    public void insertarLibros() {
        try {
            Autor autor1 = new Autor("Gabriel García Márquez", "Colombiana");
            Autor autor2 = new Autor("Isaac Asimov", "Estadounidense");
            Autor autor3 = new Autor("Agatha Christie", "Británica");
            servicio.agregarAutor(autor1);
            servicio.agregarAutor(autor2);
            servicio.agregarAutor(autor3);
            // Crear y guardar categorías en la base de datos
            Categoria categoria1 = new Categoria("Ficción");
            Categoria categoria2 = new Categoria("Aventura");
            Categoria categoria3 = new Categoria("Ciencia");
            Categoria categoria4 = new Categoria("Suspenso");
            servicio.agregarCategoria(categoria1);
            servicio.agregarCategoria(categoria2);
            servicio.agregarCategoria(categoria3);
            servicio.agregarCategoria(categoria4);
            // Crear las listas de categorías y autores
            List<Categoria> categorias1 = Arrays.asList(categoria1, categoria2);
            List<Categoria> categorias2 = Arrays.asList(categoria3);
            List<Categoria> categorias3 = Arrays.asList(categoria4);
            List<Autor> autores1 = Arrays.asList(autor1);
            List<Autor> autores2 = Arrays.asList(autor2);
            List<Autor> autores3 = Arrays.asList(autor3);

            // Crear las instancias de libros
            Libro libro1 = new Libro(
                    97884975,
                    "Cien años de soledad",
                    "Editorial Sudamericana",
                    "Español",
                    "Un clásico de la literatura latinoamericana.",
                    categorias1,
                    autores1, "img"
            );
            Libro libro2 = new Libro(
                    9780,
                    "Fundación",
                    "Gnome Press",
                    "Inglés",
                    "Una obra maestra de la ciencia ficción.",
                    categorias2,
                    autores2, "img"
            );
            Libro libro3 = new Libro(
                    9780007,
                    "Asesinato en el Orient Express",
                    "Collins Crime Club",
                    "Inglés",
                    "Una de las mejores novelas de misterio de Agatha Christie.",
                    categorias3,
                    autores3, "img"
            );
            //crear racks y copias 
            // Creación de racks para las copias
            Rack rack1 = new Rack("A1");
            Rack rack2 = new Rack("B2");
            Rack rack3 = new Rack("El rack esta a la derecha al fondo");
            servicio.agregarRack(rack1);
            servicio.agregarRack(rack3);
            servicio.agregarRack(rack2);
            // Usar el servicio para agregar los libros a la base de datos
            servicio.agregarLibro(libro1);
            servicio.agregarLibro(libro2);
            servicio.agregarLibro(libro3);
            // Creación de copias y asociación con libros
            Copia copia1Libro1 = new Copia(libro1, Tipo.RUSTICA , false, rack1, 500.0);
            Copia copia2Libro1 = new Copia(libro1, Tipo.AUDIOLIBRO , true, rack1, 450.0);

            Copia copia1Libro2 = new Copia(libro2, Tipo.LIBROELECTRONICO, false, rack2, 400.0);
            Copia copia2Libro2 = new Copia(libro2, Tipo.TAPA_DURA, false, rack2, 350.0);

            Copia copia1Libro3 = new Copia(libro3, Tipo.AUDIOLIBRO, true, rack3, 600.0);
            Copia copia2Libro3 = new Copia(libro3, Tipo.RUSTICA, false, rack3, 550.0);
            //agregar copias
            // Imprimir cada copia antes de agregarla
            System.out.println("Insertando copia: " + copia1Libro1);
            servicio.agregarCopia(copia2Libro3);
            servicio.agregarCopia(copia1Libro3);
            servicio.agregarCopia(copia1Libro1);
            servicio.agregarCopia(copia2Libro1);
            servicio.agregarCopia(copia1Libro2);
            servicio.agregarCopia(copia2Libro2);
            // Confirmación
            System.out.println("Libros insertados correctamente.");
        } catch (IllegalArgumentException e) {
            // Manejar errores en caso de que los datos sean inválidos
            System.err.println("Error al insertar libros: " + e.getMessage());
        } catch (Exception e) {
            // Manejar otros errores no esperados
            e.printStackTrace();
            System.err.println("Error inesperado al insertar libros.");
        }
    }
}
