package com.example.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @Column(nullable = false)
    private String idMiembro;
    @NotNull
    private String clave;
    @NotNull
    private boolean estado = true;
    @NotNull
    private String nombre;
    @NotNull
    private String correo;
    @NotNull
    private int librosPedidos;
    @NotNull
    private String rol; // Este es el campo que utilizamos para diferenciar el tipo de miembro (usuario, bibliotecario, etc.)
    
    protected Miembro(){}
    
    public Miembro(String idMiembro, String nombre, String clave, String rol, String correo){
        if(idMiembro == null || idMiembro.length() < 3){
            throw new IllegalArgumentException("Ingrese un usuario válido");
        }
        this.idMiembro = idMiembro;

        if(nombre == null || nombre == ""){
            throw new IllegalArgumentException("Ingrese un nombre válido");
        }
        this.nombre = nombre;
        
        if(clave == null || clave == ""){
            throw new IllegalArgumentException("Ingrese una clave válida");
        }
        this.clave = clave;

        if(rol == null){
            throw new IllegalArgumentException("Debe ingresar una clave válida");
        }
        this.rol = rol;
        if(correo == null){
            throw new IllegalArgumentException("Debe ingresar un correo");
        }

    }

    public String getIdMiembro() {
        return idMiembro;
    }
    public Boolean setIdMiembro(String id){
        if(id == null || id.length() < 3){
            throw new IllegalArgumentException("Ingrese un usuario válido");
        }else{
            idMiembro = id;
            return true;
        }
    }

    public String getClave() {
        return clave;
    }
    public Boolean setClave(String clave) {
        if(clave == null || clave == "" || clave.length()<8){
            throw new IllegalArgumentException("Ingrese una contraseña válida");
        }else{
            this.clave = clave;
            return true;
        }
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
    public Boolean setNombre(String nombre) {
        if(nombre == null || nombre == "" || nombre.length()<3){
            throw new IllegalArgumentException("Ingrese un nombre válido");
        }else{
            this.nombre = nombre;
            return true;
        }
    }

    public String getCorreo() {
        return correo;
    }
    public Boolean setCorreo(String correo) {
        // Expresión regular para validar un correo electrónico
        String correoRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (correo == null || correo.isEmpty() || !correo.matches(correoRegex)) {
            throw new IllegalArgumentException("Ingrese un correo válido"); 
        } else {
            this.correo = correo;
            return true;
        }
    }
    public int getLibrosPedidos() {
        return librosPedidos;
    }
    public void setLibrosPedidos(int librosPedidos) {
        this.librosPedidos = librosPedidos;
    }

    public String getRol() {
        return rol; // Método para obtener el rol
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    public static boolean contieneSoloNumeros(String str) {
        
        for (char c : str.toCharArray()) {
            
            if (!Character.isDigit(c)) {
                
                return false;
            }
        }
        return true;
    }
    
}
