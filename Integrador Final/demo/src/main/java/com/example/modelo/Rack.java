package com.example.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Racks")
public class Rack {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idRack;
    @NotNull
    private String descripcion;
    public long getIdRack() {
        return idRack;
    }
    public void setIdRack(long idRack) {
        this.idRack = idRack;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
