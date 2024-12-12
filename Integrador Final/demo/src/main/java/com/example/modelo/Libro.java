package com.example.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity 
@Table(name = "Libros")
@Inheritance(strategy = InheritanceType.JOINED)
public class Libro { 
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE) 
    private long isbn; 
    @NotNull
    private String titulo; 
    @NotNull
    private String nrCopias;
    public long getIsbn() {
        return isbn;
    }
    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getNrCopias() {
        return nrCopias;
    }
    public void setNrCopias(String nrCopias) {
        this.nrCopias = nrCopias;
    }
    
    public Libro() {
    }
    public Libro(long isbn, String titulo, String nrCopias) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.nrCopias = nrCopias;
    } 

}