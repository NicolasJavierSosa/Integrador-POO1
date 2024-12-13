package com.example.modelo;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;


@Entity
public class Bibliotecario extends Miembro{
    private String rol = "bibliotecario";  // Este podría ser un atributo o campo derivado del tipo
    
    @NotNull
    protected long codigo;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Bibliotecario(long codigo) {
        this.codigo = codigo;
    }
    // Getters y setters
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
