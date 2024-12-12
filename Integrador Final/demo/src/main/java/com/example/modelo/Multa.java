package com.example.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Multas")
public class Multa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idMulta;
    @NotNull
    private float costo;
    @NotNull
    private boolean estado;
    public long getIdMulta() {
        return idMulta;
    }
    public float getCosto() {
        return costo;
    }
    public void setCosto(float costo) {
        this.costo = costo;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public Multa(long idMulta, float costo, boolean estado) {
        this.idMulta = idMulta;
        this.costo = costo;
        this.estado = estado;
    }
    public Multa() {
    }
    
}
