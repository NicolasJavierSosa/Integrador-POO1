package com.example.modelo;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;


@Entity
public class Usuario extends Miembro{
    private String rol = "usuario"; 
    
    @NotNull
    private String direccion;

    public Usuario(){

    }
    public Usuario(String idMiembro, String nombre, String clave, String rol, String correo, String direccion) {
        super(idMiembro, nombre, clave, rol, correo);
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    // Getters y setters
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
