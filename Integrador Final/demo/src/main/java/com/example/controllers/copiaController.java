package com.example.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.example.App;
import com.example.modelo.Categoria;
import com.example.modelo.Copia;
import com.example.modelo.Estado;
import com.example.modelo.Libro;
import com.example.modelo.Rack;
import com.example.modelo.Tipo;
import com.example.servicio.Servicio;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

public class copiaController {
    @FXML
    private ComboBox<Rack> CbRack;

    @FXML
    private ComboBox<Tipo> CbTipo;

    @FXML
    private RadioButton RbCopiaPerdida;

    @FXML
    private RadioButton RbCopiaReferencia;

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnBuscarRack;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEliminarRack;

    @FXML
    private Button btnFiltrar;

    @FXML
    private Button btnModificarCopia;

    @FXML
    private Button btnModificarRack;

    @FXML
    private Button btnNuevoRack;

    @FXML
    private TableView<Copia> tablaCopias;
    @FXML
    private TableColumn<Copia, String> columEstado;

    @FXML
    private TableColumn<Copia, String> columLibro;

    @FXML
    private TableColumn<Copia, Double> columPrecio;

    @FXML
    private TableColumn<Copia, String> columRack;

    @FXML
    private TableColumn<Copia, String> columRef;

    @FXML
    private TableColumn<Copia, String> columTipo;

    @FXML
    private TextField txtPrecio;
    @FXML
    private ComboBox<Libro> CbLibro;

    private Copia copiaCreada;
    private boolean cancelado = false;
    private Servicio servicio;
    private Libro libro;

    @FXML
    public void initialize() throws IOException {
        servicio = App.getServicio();
        // Cargar valores del Enum Tipo en el ComboBox
        CbTipo.setItems(FXCollections.observableArrayList(Tipo.values()));
        // Cargar los valores de Libro en el Combo box

        // Simula cargar racks existentes 
        List<Rack> racks = servicio.obtenerRacks(); 
        CbRack.setItems(FXCollections.observableArrayList(racks));
        // Configurar el StringConverter para mostrar solo el ID del rack
        CbRack.setConverter(new StringConverter<Rack>() { 
            @Override public String toString(Rack rack) { 
                return rack != null ? String.valueOf(rack.getIdRack()) : ""; 
            } 
            @Override public Rack fromString(String string) { 
                for (Rack rack : racks) { 
                    if (String.valueOf(rack.getIdRack()).equals(string)) { 
                        return rack; 
                    } 
                } return null; 
            } 
        });
        tablaCopias();
        seleccionarLibro();

    }
    @FXML
    public void seleccionarLibro() {
        try {
            // Obtener los libross desde la base de datos
            List<Libro> libros = servicio.obtenerLibros();

            CbLibro.setItems(FXCollections.observableArrayList(libros));
            // Establecer cómo se debe mostrar cada categoría en el ComboBox
            CbLibro.setCellFactory(param -> new ListCell<Libro>() {
                @Override
                protected void updateItem(Libro item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getTitulo()); // Mostrar el nombre del libro
                    }
                }
            });
            // Establecer el texto mostrado en el botón del ComboBox
            CbLibro.setButtonCell(new ListCell<Libro>() {
                @Override
                protected void updateItem(Libro item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getTitulo()); // Mostrar el nombre del libro en el botón del ComboBox
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar las categorías.");
        }
    }


    @FXML
    private void aceptar() throws IOException {

        try {
            // Validar campos obligatorios
            if (CbTipo.getValue() == null || CbRack.getValue() == null || txtPrecio.getText().isEmpty() || CbLibro.getValue() == null) {
                mostrarError("Debe completar todos los campos obligatoriamente.");
                return;
            }
            // Crear la nueva copia
                copiaCreada = new Copia(
                libro = CbLibro.getValue(), 
                CbTipo.getValue(),
                false, // La copia por defecto no es de referencia
                CbRack.getValue(),
                Double.parseDouble(txtPrecio.getText())
            );
            Object[] opciones = {"Si", "No"};
            int seleccion = JOptionPane.showOptionDialog(null, "¿Esta seguro que quiere agregar esta nueva copia?", "¿Está seguro?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, 1);
            if (seleccion == 0) {
                servicio.agregarCopia(copiaCreada);
                    libro.añadirCopiaAsociada(copiaCreada);
                    App.setRoot("AccionesCopia");

            }
            // Si se marca como perdida, se ajusta el estado
            if (RbCopiaPerdida.isSelected()) {
                copiaCreada.setEstado(Estado.PERDIDO);
                copiaCreada.setDisponible(false);
            }
            // Si se marca como de referencia, se ajusta el estado
            if (RbCopiaReferencia.isSelected()) {
                copiaCreada.setReferencia(true);
            }
            cerrarVentana();
        } catch (NumberFormatException e) {
            mostrarError("El precio debe ser un valor numérico válido.");
        }
    }

    @FXML
    private void cancelar() throws IOException {
        Alert alerta = new Alert(AlertType.CONFIRMATION);
            alerta.setTitle("Cerrar sesion");
            alerta.setHeaderText(null);
            alerta.setContentText("Está segur@ que desea cancelar el registro?");
            Optional<ButtonType> option = alerta.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                cancelado = true;
                cerrarVentana();
            }
        
    }

    public Copia getCopiaCreada() {
        return cancelado ? null : copiaCreada;
    }

    private void cerrarVentana() throws IOException {
        App.setRoot("librosBibliotecario");
    }

    private void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    @FXML
    void agregarRack(ActionEvent event) {

    }

    @FXML
    void buscarRacks(ActionEvent event) {

    }
    @FXML
    void eliminarRack(ActionEvent event) {

    }

    @FXML
    void filtrarCopias(ActionEvent event) {

    }

    @FXML
    void modificarCopia(ActionEvent event) {

    }

    @FXML
    void modificarRack(ActionEvent event) {

    }

    public void tablaCopias() throws IOException {
        //libro, tipo, precio, estado, rack, referenci
        columLibro.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        columTipo.setCellValueFactory(new PropertyValueFactory<>("tipoString"));
        columPrecio.setCellValueFactory(new PropertyValueFactory<>("precioEstimado"));
        columEstado.setCellValueFactory(new PropertyValueFactory<>("estadoString"));
        columRack.setCellValueFactory(new PropertyValueFactory<>("idRackString"));
        columRef.setCellValueFactory(new PropertyValueFactory<>("referenciaString"));
        cargarCopias();
    }

    @FXML
    private void cargarCopias() throws IOException {
        List<Copia> copiasLista = servicio.obtenerCopias();
        ObservableList<Copia> observableList = FXCollections.observableArrayList(copiasLista);
        tablaCopias.setItems(observableList);
    }

}
