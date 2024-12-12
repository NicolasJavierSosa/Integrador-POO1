package com.example.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;


@Entity
public class Bibliotecario extends Miembro{
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
    
}
