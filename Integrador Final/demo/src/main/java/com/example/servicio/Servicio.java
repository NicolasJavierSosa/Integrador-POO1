package com.example.servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.example.App;
import com.example.modelo.Miembro;
import com.example.repositorio.Repositorio;

public class Servicio {
    private Repositorio repositorio;
    private Miembro miembroActivo;
    private Miembro miembroSelec;

    public Servicio(Repositorio repo) {
        this.repositorio = repo;
    }

    /**
     * Autentica al miembro utilizando su id y clave.
     * @param idMiembro Identificador del miembro.
     * @param clave Clave del miembro.
     * @throws IOException Si ocurre un error de I/O.
     */
    public void autenticarMiembro(String idMiembro, String clave) throws IOException {
        
        miembroActivo = repositorio.buscar(Miembro.class, idMiembro);
    
        if (miembroActivo == null) {
            JOptionPane.showMessageDialog(null, "Usuario ingresado inválido");
            throw new IllegalArgumentException("Usuario no encontrado.");
        }
    
        if (miembroActivo.getClave() == null || miembroActivo.getClave().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se ingresó contraseña");
            throw new IllegalArgumentException("Contraseña vacía.");
        }
    
        if (!miembroActivo.getClave().equals(clave)) {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
            throw new IllegalArgumentException("Contraseña incorrecta.");
        }
    
        if (!miembroActivo.isEstado()) {
            JOptionPane.showMessageDialog(null, "El miembro está dado de baja");
            throw new IllegalArgumentException("Miembro desactivado.");
        }
    
        System.out.println("Rol del miembro: " + miembroActivo.getRol());
    
        if (miembroActivo.getRol() == null) {
            JOptionPane.showMessageDialog(null, "El rol del miembro no está definido");
            throw new IllegalStateException("Rol indefinido.");
        }
        
        if ("Bibliotecario".equals(miembroActivo.getRol())) {
            App.setRoot("inicioBibliotecario");
        } else {
            App.setRoot("inicioUsuario");
        }
    }
    

    public Miembro getMiembroActivo(){
        return miembroActivo;
    }


    public void modificarMiembro(Miembro modifMiembro){
        try {
            repositorio.iniciarTransaccion();
            repositorio.modificar(modifMiembro);
            repositorio.confirmarTransaccion();
        } catch (Exception e) {
            repositorio.descartarTransaccion();
            throw e;
        }
    }

    public void agregarMiembro(Miembro newMiembro){
        try{
            repositorio.iniciarTransaccion();
            repositorio.insertar(newMiembro);
            repositorio.confirmarTransaccion();
        }
        catch(Exception e){
            repositorio.descartarTransaccion();
            throw e;
        }
    }

    public List<Miembro> obtenerMiembros() throws IOException{
        List<Miembro> miembrosLista = new ArrayList<>();
        miembrosLista = repositorio.buscarTodos(Miembro.class);
        return miembrosLista;
    }
    public void guardarMiembroSelec(Miembro selec){
        try{
            miembroSelec = selec;
        }
        catch(Exception e){
            throw e;
        }
    }
    public Miembro getMiembroSelec(){
        Miembro selec = miembroSelec;
        return selec;
    }
}
