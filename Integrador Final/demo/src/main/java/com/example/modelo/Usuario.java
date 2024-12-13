package com.example.modelo;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;


@Entity
public class Usuario extends Miembro{
    private String rol = "usuario";  // Este podría ser un atributo o campo derivado del tipo
    @NotNull
    private String direccion;

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
