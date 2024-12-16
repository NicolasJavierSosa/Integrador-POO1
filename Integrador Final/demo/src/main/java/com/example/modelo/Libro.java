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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity 
@Table(name = "libro")
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
    @NotNull
    private int nrCopias;
    @NotNull
    private int cantDisponible;
    @ManyToMany
    @JoinTable(
        name = "libro_categoria",
        joinColumns = @JoinColumn(name = "isbn"),
        inverseJoinColumns = @JoinColumn(name = "idCategoria"))
    private List<Categoria> categorias = new ArrayList<Categoria>();
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
    public Libro(long isbn, String titulo, String editorial, String idioma, String descripcion, int nrCopias, int cantDisponible, List<Categoria> categorias,List<Autor> autores ) {
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

        if(nrCopias<=0){
            throw new IllegalArgumentException("Ingrese una cantidad adecuada");
        }
        this.nrCopias = nrCopias;

        if(cantDisponible<=0){
            throw new IllegalArgumentException("Ingrese una cantidad adecuada");
        }
        this.cantDisponible = cantDisponible;

        if (categorias == null) {
            throw new IllegalArgumentException("Debe ingresar al menos una categoría");
        }
        this.categorias = categorias;

        if (editorial == null || editorial.equals("")) {
            throw new IllegalArgumentException("Debe ingresar una editorial válida");
        }
        this.editorial = editorial;

        if (autores == null) {
            throw new IllegalArgumentException("Debe ingresar al menos un autor");
        }
        this.autores = autores;
        this.descripcion = descripcion;
    } 

    public int getCantDisponible() {
        return cantDisponible;
    }
    public void setCantDisponible(int cantDisponible) {
        this.cantDisponible = cantDisponible;
    }
    public long getIsbn() {
        return isbn;
    }
    public boolean setIsbn(long isbn) {
        if (isbn == 0 ) {
            throw new IllegalArgumentException("Debe ingresar un ISBN válido");
        }
        else{
            this.isbn = isbn;
            return true;
        }
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
        return nrCopias;
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
    public List<Copia> getCopiasAsociadas(){
        return new ArrayList<>(copiasAsociadas); // Para evitar cambios directos
    }
    public Boolean añadirCopiaAsociada(Copia copia){
        if(copia != null){
            copiasAsociadas.add(copia);
            return true;
        }
        else{
            throw new IllegalArgumentException("Debe ingresar una copia valida para asociar");
        }
    }
    public Boolean removerCopiaAsociada(Copia copia){
        if(copia != null){
            copiasAsociadas.remove(copia);
            return true;
        }
        else{
            throw new IllegalArgumentException("Debe ingresar una copia valida para asociar");
        }
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
    public void agregarCopia(Copia copia) {
        if (copia == null) {
            throw new IllegalArgumentException("Debe ingresar una copia válida");
        }
        copiasAsociadas.add(copia);
        copia.setLibro(this); // Asocia la copia con este libro
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
        return new ArrayList<>(categorias);

    }

    public List<String> getCategString(){
        List<String> x = new ArrayList<>();
        int tamaño = categorias.size();
        for(int i = 0; i < tamaño; i++){
            x.add(categorias.get(i).getCategoria());
        }
        return x;
    }
    public void setCategorias(List<Categoria> categorias){
        this.categorias = categorias;
    }
    public List<String> getAutoresString(){
        List<String> x = new ArrayList<>();
        int tamaño = autores.size();
        for(int i = 0; i < tamaño; i++){
            x.add(autores.get(i).getNombre());
        }
        return x;
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
            categorias.add(cat);
            return true;
        }
    }
    public Boolean removerCategoria(Categoria cat){
        if(cat == null){
            throw new IllegalArgumentException("Debe ingresar al menos una categoría");
        }
        else{
            categorias.remove(cat);
            return true;
        }
    }

    public void setCopiasAsociadas(List<Copia> copiasAsociadas) {
        this.copiasAsociadas = copiasAsociadas;
    }


}