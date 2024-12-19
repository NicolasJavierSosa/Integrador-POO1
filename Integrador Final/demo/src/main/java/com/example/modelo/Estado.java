package com.example.modelo;

public enum Estado {
    DISPONIBLE,
    PRESTADO,
    PERDIDO;

    @Override
    public String toString() {
        switch(this) {
            case DISPONIBLE: return "Disponible";
            case PRESTADO: return "Prestado";
            case PERDIDO: return "Perdido";
            default: return "Desconocido";
        }
    }
}
