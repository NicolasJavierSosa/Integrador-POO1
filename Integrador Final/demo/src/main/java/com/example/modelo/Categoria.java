package com.example.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idCategoria;
    @NotNull
    private String nombreCategoria;
    // Relación ManyToMany con Libro
    @ManyToMany(mappedBy = "categoria")
    private List<Libro> libro = new ArrayList<>();

    protected Categoria(){}

    public long getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }
    public String getCategoria() {
        return nombreCategoria;
    }
    public boolean setNombreCategoria(String nombreCategoria) {
        if (nombreCategoria == null || nombreCategoria.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar una categoría válida");
        }
        this.nombreCategoria = nombreCategoria;
        return true;
    }

    public Categoria(String nombreCategoria) {
        if (nombreCategoria == null || nombreCategoria.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar una categoría válida");
        }
        this.nombreCategoria = nombreCategoria;
    }
    public List<Libro> getLibros() {
        return new ArrayList<>(libro);
    }
    public void setLibros(List<Libro> libro) {
        this.libro = libro;
    }
}
