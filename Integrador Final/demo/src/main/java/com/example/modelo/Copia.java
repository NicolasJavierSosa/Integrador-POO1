package com.example.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity 
public class Copia extends Libro { 
    @NotNull
    protected boolean referencia; 
    @NotNull
    protected String idioma; 
    @NotNull
    protected String editorial; 
    @NotNull
    protected float precioEstimado;
    public boolean isReferencia() {
        return referencia;
    }
    public void setReferencia(boolean referencia) {
        this.referencia = referencia;
    }
    public String getIdioma() {
        return idioma;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    public String getEditorial() {
        return editorial;
    }
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    public float getPrecioEstimado() {
        return precioEstimado;
    }
    public void setPrecioEstimado(float precioEstimado) {
        this.precioEstimado = precioEstimado;
    }
    public Copia(boolean referencia, String idioma, String editorial, float precioEstimado) {
        this.referencia = referencia;
        this.idioma = idioma;
        this.editorial = editorial;
        this.precioEstimado = precioEstimado;
    }
    public Copia() {
    } 
    
}
