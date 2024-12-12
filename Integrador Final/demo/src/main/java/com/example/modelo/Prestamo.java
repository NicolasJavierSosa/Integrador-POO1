package com.example.modelo;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Table(name = "Prestamos")
@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long nrPrestamo;

    @NotNull
    private LocalDate fechaPrestamo;

    @NotNull
    private LocalDate fechaVencimiento;

    private LocalDate fechaDevolucion;

    @NotNull
    private boolean estado;

    @NotNull
    private int diasRetraso;

    // Constructor sin argumentos requerido por JPA

    // Constructor completo para instancias manuales
    public Prestamo(LocalDate fechaPrestamo, LocalDate fechaVencimiento, LocalDate fechaDevolucion,
                    boolean estado, int diasRetraso) {
        this.fechaPrestamo = fechaPrestamo;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
        this.diasRetraso = diasRetraso;
    }
    public Prestamo() {
        this.fechaPrestamo = LocalDate.now();
        this.fechaVencimiento = LocalDate.now().plusDays(14); // Ejemplo: préstamo por 14 días
        this.estado = true; // Estado inicial
        this.diasRetraso = 0; // Sin retraso al inicio
    }

    // Getters y setters
    public Long getNrPrestamo() {
        return nrPrestamo;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getDiasRetraso() {
        return diasRetraso;
    }

    public void setDiasRetraso(int diasRetraso) {
        this.diasRetraso = diasRetraso;
    }
}