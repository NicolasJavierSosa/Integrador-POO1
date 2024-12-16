package com.example.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long codAutor;
    @NotNull
    private String nombre;
    @NotNull
    private String nacionalidad;
    // Relación de muchos a muchos con Libro
    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros = new ArrayList<>();

    public Autor() { }
    public Autor(String nombre, String nacionalidad) {
        if(nombre == null || nombre.trim().isEmpty() || nacionalidad == null || nacionalidad.trim().isEmpty()){
            throw new IllegalArgumentException("Debe ingresar un nombre y/o nacionalidad válidos");
        }
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }
    //getters and setters
    public long getCodAutor() {
        return codAutor;
    }
    public void setCodAutor(long codAutor) {
        this.codAutor = codAutor;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    public List<Libro> getLibros() {
        return new ArrayList<>(libros); // Para evitar cambios directos
    }
    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
