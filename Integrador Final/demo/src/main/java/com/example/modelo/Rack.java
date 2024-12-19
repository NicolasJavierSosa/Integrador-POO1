package com.example.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Rack {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idRack;
    @NotBlank // Valida que no sea nulo o vacío
    private String descripcion;
    @OneToMany(mappedBy = "rack")
    private List<Copia> copias = new ArrayList<>();
 
    protected Rack(){}

    public Rack(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        this.descripcion = descripcion;
    }
    public Long getIdRack() {
        return idRack;
    }
    public void setIdRack(long idRack) {
        this.idRack = idRack;
    }
    public String getDescrip() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public List<Copia> getCopias() {
        return copias;
    }
    public void agregarCopia(Copia copia) {
        if (copia == null) {
            throw new IllegalArgumentException("La copia no puede ser nula");
        }
        copias.add(copia);
        copia.setRack(this); // Establece la relación bidireccional
    }

    public void eliminarCopia(Copia copia) {
        if (copia == null) {
            throw new IllegalArgumentException("La copia no puede ser nula");
        }
        copias.remove(copia);
        copia.setRack(null); // Rompe la relación bidireccional
    }
    
}
