package com.example.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Miembros")
@Inheritance(strategy = InheritanceType.JOINED)
public class Miembro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idMiembro;
    @NotNull
    private String clave;
    @NotNull
    private boolean estado;
    @NotNull
    private String nombre;
    @NotNull
    private String correo;
    @NotNull
    private int librosPedidos;
    @NotNull
    private String rol; // Este es el campo que utilizamos para diferenciar el tipo de miembro (usuario, bibliotecario, etc.)
    public long getIdMiembro() {
        return idMiembro;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public int getLibrosPedidos() {
        return librosPedidos;
    }
    public void setLibrosPedidos(int librosPedidos) {
        this.librosPedidos = librosPedidos;
    }
    public Miembro(long idMiembro, String clave, boolean estado, String nombre, String correo, int librosPedidos) {
        this.idMiembro = idMiembro;
        this.clave = clave;
        this.estado = estado;
        this.nombre = nombre;
        this.correo = correo;
        this.librosPedidos = librosPedidos;
    }
    public Miembro() {
    }
    public String getRol() {
        return rol; // Método para obtener el rol
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
