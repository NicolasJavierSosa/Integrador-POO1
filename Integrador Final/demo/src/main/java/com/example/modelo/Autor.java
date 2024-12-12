package com.example.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long codAutor;
    @NotNull
    private String nombre;
    @NotNull
    private String nacionalidad;


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

    public Autor() {
    }
    public Autor(long codAutor, String nombre, String nacionalidad) {
        this.codAutor = codAutor;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }
    
}
