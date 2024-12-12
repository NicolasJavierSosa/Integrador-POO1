package com.example.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Detalle_Prestamo")
public class DetallePrestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long nrDetalle;
    @NotNull
    private double precio;
    public long getNrDetalle() {
        return nrDetalle;
    }
    public void setNrDetalle(long nrDetalle) {
        this.nrDetalle = nrDetalle;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public DetallePrestamo(long nrDetalle, double precio) {
        this.nrDetalle = nrDetalle;
        this.precio = precio;
    }
    
}
