package com.example.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity 
public class Libro { 
    @Id
    @NotNull
    private long isbn; 
    @NotNull
    private String titulo;
    @NotNull
    protected String idioma; 
    @NotNull
    protected String editorial;
    @Column
    private String descripcion;
    @Column
    private String img; 
    @NotNull
    private int nrCopias;
    @NotNull
    private int cantDisponible;
    @ManyToMany
    @JoinTable(
        name = "libro_categorias",
        joinColumns = @JoinColumn(name = "isbn"),
        inverseJoinColumns = @JoinColumn(name = "idCategoria"))
    private List<Categoria> categoria = new ArrayList<>();
    @ManyToMany
    @JoinTable(
        name = "libro_autor", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "isbn"), // Columna para el libro
        inverseJoinColumns = @JoinColumn(name = "codAutor")) // Columna para el autor
    private List<Autor> autores = new ArrayList<>();
    // Relación uno a muchos con Copia
    @OneToMany(mappedBy = "libro")
    private List<Copia> copiasAsociadas = new ArrayList<>();
    
    protected Libro() { }
    public Libro(long isbn, String titulo, String editorial, String idioma, String descripcion, List<Categoria> categoria,List<Autor> autores, String img) {
        if (isbn == 0 ) {
            throw new IllegalArgumentException("Debe ingresar un ISBN válido");
        }
        this.isbn = isbn;

        if (titulo == null || titulo.equals(" ")) {
            throw new IllegalArgumentException("Debe ingresar un titulo válido");
        }
        this.titulo = titulo;
        if (idioma == null || idioma.equals(" ")) {
            throw new IllegalArgumentException("Debe ingresar un idioma válido");
        }
        this.idioma = idioma;

        if (categoria == null) {
            throw new IllegalArgumentException("Debe ingresar al menos una categoría");
        }
        this.categoria = categoria;

        if (editorial == null || editorial.equals("")) {
            throw new IllegalArgumentException("Debe ingresar una editorial válida");
        }
        this.editorial = editorial;

        if (autores == null) {
            throw new IllegalArgumentException("Debe ingresar al menos un autor");
        }
        this.autores = autores;
        this.descripcion = descripcion;
        this.img = img;
    } 

    public void setCantDisponible(int cantDisponible) {
        this.cantDisponible = cantDisponible;
    }
    public long getIsbn() {
        return isbn;
    }
    public void setIsbn(long isbn) {
        if (isbn == 0 ) {
            throw new IllegalArgumentException("Debe ingresar un ISBN válido");
        }
        else{
            this.isbn = isbn;
        }
    }
    public String getIdString(){
        return String.valueOf(isbn);
    }

    public int getCopiasDisponibles() {
        List<Copia> lista = getCopiasAsociadas();
        int tamaño = lista.size();
        int cantCopias = 0;
        for(int i = 0; i < tamaño; i++){
            if(lista.get(i).isDisponible()){
                cantCopias++;
            }
        }
        return cantCopias;
    }

    public String getTitulo() {
        return titulo;
    }
    public boolean setTitulo(String titulo){
        if (titulo == null || titulo.equals("")) {
            throw new IllegalArgumentException("Debe ingresar un titulo válido");
        }
        else{
            this.titulo = titulo;
            return true;
        }
    }
    public boolean setIdioma(String idioma){
        if (idioma == null || idioma.equals("")) {
            throw new IllegalArgumentException("Debe ingresar un idioma válido");
        }
        else{
            this.idioma = idioma;
            return true;
        }
    }
    public boolean setEditorial(String editorial){
        if (editorial == null || editorial.equals("")) {
            throw new IllegalArgumentException("Debe ingresar una editorial válida");
        }
        else{
            this.editorial = editorial;
            return true;
        }
    }
    public int  getNrCopias() {
        return nrCopias = getCopiasAsociadas().size();
    }
    public void setNrCopias(int  nrCopias) {
        this.nrCopias = nrCopias;
    }
    public List<Autor> getAutores() {
        return new ArrayList<>(autores);
    }
    public void setAutores(List<Autor> autores){
        this.autores = autores;
    }
    public List<Copia> getCopiasAsociadas() {
        if (copiasAsociadas == null) {
            copiasAsociadas = new ArrayList<>();
        }
        List<Copia> x = new ArrayList<>();
        x.addAll(copiasAsociadas);
        return x;
    }

    public void actualizarDisponibilidad() {
        this.cantDisponible = (int) copiasAsociadas.stream()
            .filter(Copia::isDisponible)
            .count();
    }

    /**
     * Agregar una copia al libro y actualizar la cantidad de copias disponibles.
     */
    public Boolean añadirCopiaAsociada(Copia copia){
        if(copia != null){
            copiasAsociadas.add(copia);
            copia.setLibro(this); // Establecer la relación bidireccional
            actualizarDisponibilidad();
            return true;
        }
        else{
            throw new IllegalArgumentException("Debe ingresar una copia valida para asociar");
        }
        
    }

    /**
     * Remover una copia del libro y actualizar la cantidad de copias disponibles.
     */

    public Boolean removerCopiaAsociada(Copia copia){
        if(copia != null){
            copiasAsociadas.remove(copia);
            actualizarDisponibilidad(); // Actualiza la cantidad disponible
            return true;
        }
        else{
            throw new IllegalArgumentException("Debe ingresar una copia valida para asociar");
        }
    }

    public void setCopiasAsociadas(List<Copia> copiasAsociadas) {
        this.copiasAsociadas = copiasAsociadas;
        actualizarDisponibilidad(); // Asegura la consistencia al modificar toda la lista
    }

    public int getCantDisponible() {
        actualizarDisponibilidad(); // Asegura que siempre esté sincronizado
        return cantDisponible;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getIdioma() {
        return idioma;

    }
    public String getEditorial() {
        return editorial;
    }

    public String getCantCopias() {
        return String.valueOf(getCopiasAsociadas().size());

    }
    public String getCopiasDisp() {
        List<Copia> lista = getCopiasAsociadas();  // Obtiene la lista de copias asociadas al libro
        int tamaño = lista.size();  // Obtiene el tamaño de la lista de copias
        int cantCopiasDisponibles = 0;  // Contador de copias disponibles
        // Itera a través de todas las copias asociadas
        for (int i = 0; i < tamaño; i++) {
            Copia copia = lista.get(i);
            // Verifica si la copia está disponible (no está prestada)
            if (copia.isDisponible()) {
                cantCopiasDisponibles++;
            }
        }
        // Retorna la cantidad de copias disponibles como un String
        return Integer.toString(cantCopiasDisponibles);
    }

    public List<Categoria> getCategorias() {
        return new ArrayList<>(categoria);

    }

    public List<String> getCategString(){
        List<String> x = new ArrayList<>();
        int tamaño = categoria.size();
        for(int i = 0; i < tamaño; i++){
            x.add(categoria.get(i).getCategoria());
        }
        return x;
    }
    public void setCategorias(List<Categoria> categorias){
        this.categoria = categorias;
    }
    public List<String> getAutoresString(){
        List<String> x = new ArrayList<>();
        int tamaño = autores.size();
        for(int i = 0; i < tamaño; i++){
            x.add(autores.get(i).getNombre());
        }
        return x;
    }
    public String getImg(){
        return img;
    }
    public boolean agregarAutor(Autor autor){
        if (autor == null) {
            throw new IllegalArgumentException("Debe ingresar al menos un autor");
        }
        else{
            autores.add(autor);
            return true;
        }
    }
    public Boolean removerAutor(Autor autor){
        if (autor == null) {
            throw new IllegalArgumentException("Debe ingresar al menos un autor");
        }
        else{
            autores.remove(autor);
            return true;
        }
    }
    public boolean agregarCategoria(Categoria cat){
        if(cat == null){
            throw new IllegalArgumentException("Debe ingresar al menos una categoría");
        }
        else{
            categoria.add(cat);
            return true;
        }
    }
    public Boolean removerCategoria(Categoria cat){
        if(cat == null){
            throw new IllegalArgumentException("Debe ingresar al menos una categoría");
        }
        else{
            categoria.remove(cat);
            return true;
        }
    }



}