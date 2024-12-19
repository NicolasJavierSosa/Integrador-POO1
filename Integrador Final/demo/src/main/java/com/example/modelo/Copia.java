package com.example.modelo;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Copia{ 
    @Id
    @Column(columnDefinition = "UUID")
    private UUID idCopia = UUID.randomUUID();
    @ManyToOne 
    @JoinColumn(name = "isbn", nullable = false) 
    private Libro libro; 
    @NotNull
    protected boolean referencia; 
    @NotNull
    protected double precioEstimado;
    @Column(nullable = false)
    private Tipo tipo;
    @ManyToOne
    @JoinColumn(name = "idRack", nullable = false)
    private Rack rack;
    @Column(nullable = false)
    private Estado estado = Estado.DISPONIBLE;
    private boolean disponible = true; // Estado inicial


    // Constructor sin argumentos
    protected Copia() { }

    public Copia( Libro libro, Tipo tipo, boolean referencia, Rack rack, double precioEstimado) {
        //* Validacion del libro*/
        if (libro == null) {
            throw new IllegalArgumentException("Debe ingresar un libro válido");
        }
        this.libro = libro;
        // Validación del tipo de copia
        if (tipo == null) {
            throw new IllegalArgumentException("Debe ingresar un tipo de copia válido");
        }
        this.tipo = tipo;  // Asignar tipo a la copia
    
        // Validación de la referencia
        if (referencia != true && referencia != false) {
            throw new IllegalArgumentException("Debe aclarar si la copia a ingresar es una copia de referencia");
        }
        this.referencia = referencia;
    
        // Validación de rack
        if (rack == null) {
            throw new IllegalArgumentException("Debe ingresar un rack válido");
        }
        this.rack = rack;
    
        // Validación del precio estimado
        if (precioEstimado <= 0) {
            throw new IllegalArgumentException("Debe ingresar un precio estimado válido");
        }
        this.precioEstimado = precioEstimado;
    }
    public String getRackDescripcion() { 
        return rack != null ? rack.getDescrip() : ""; 
    }
    // Getters y setters
    public void setLibro(Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser nulo");
        }
        this.libro = libro;
    }
    public Libro getLibro(){
        return libro;
    }

    public UUID getIdCopia() {
        return idCopia;
    }
    public boolean isReferencia() {
        return referencia;
    }

    public void setReferencia(boolean referencia) {
        this.referencia = referencia;
    }
    public double getPrecioEstimado() {
        return precioEstimado;
    }

    public boolean setPrecioEstimado(Double precio){
        if (precio == null) {
            throw new IllegalArgumentException("Debe ingresar un precio estimado válido");
        }
        else{
            precioEstimado = precio;
            return true;
        }
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Rack getRack() {
        return rack;
    }
    public String getDescripcion(){
        String x = rack.getDescrip();
        return x;
    }


    public String getIdRackString() {
        return rack != null ? String.valueOf(rack.getIdRack()) : "";
    }


    public void setRack(Rack rack) {
        if (rack == null) {
            throw new IllegalArgumentException("Debe ingresar un rack válido");
        }
        this.rack = rack;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        if (estado == null) {
            throw new IllegalArgumentException("Debe ingresar un estado válido");
        }
        this.estado = estado;
        this.disponible = estado == Estado.DISPONIBLE;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Métodos adicionales
    public String getReferenciaString() {
        return referencia ? "Es referencia" : "No es referencia";
    }

    public String getEstadoString() {
        return estado.toString();
    }

    public String getTipoString() {
        return tipo.toString();
    }
    public String getTitulo(){
        return libro.getTitulo();
    }
    public Boolean setReferencia(Boolean referencia){
        if (referencia != true && referencia != false) {
            throw new IllegalArgumentException("Debe aclarar si la copia a ingresar es una copia de referencia");
        }
        else{
            this.referencia = referencia;
            return true;
        }
    }
    public String getTituloLibro() { 
        return libro != null ? libro.getTitulo() : ""; 
    }

    public String getIdCopiaString(){
        String x = getIdCopia().toString();
        return x;
    }
    
}
