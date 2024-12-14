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
        miembroActivo = this.repositorio.buscar(Miembro.class, idMiembro);
        if(miembroActivo == null){
            JOptionPane.showMessageDialog(null, "Usuario ingresado inválido");
            throw new IllegalArgumentException();
        }
        if(!(miembroActivo.isEstado())){
            JOptionPane.showMessageDialog(null, "El miembro esta dado de baja");
            throw new IllegalArgumentException();
        }
        if(!miembroActivo.getClave().equals(clave)){
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
            throw new IllegalArgumentException();
        }
        if(miembroActivo.getClave()==null || miembroActivo.getClave().isEmpty()){
            JOptionPane.showMessageDialog(null, "No se ingresó contraseña");
            throw new IllegalArgumentException();
        }
        if(miembroActivo.getIdMiembro() == null || miembroActivo.getIdMiembro().isEmpty()){
            JOptionPane.showMessageDialog(null, "no se ha ingresado un usuario");
            throw new IllegalArgumentException();
        }

        // Redirección basada en el rol
        if (!isSocio()) {
            App.setRoot("inicioBibliotecario");  // Reemplazar con la vista del Bibliotecario
        } else if (isSocio()) {
            App.setRoot("inicioUsuario");  // Reemplazar con la vista del Usuario
        } else {
            JOptionPane.showMessageDialog(null, "Rol de miembro no reconocido");
        }
    } 

    public Miembro getMiembroActivo(){
        return miembroActivo;
    }
    
    public Boolean isSocio(){
        if("Usuario" == miembroActivo.getRol() /* "Usuario".equals(miembroActivo.getRol())*/){
            return true;
        }
        else{
            return false;
        }
    }
  /*  public void modificarPrestamo(Prestamo modifPrestamo){
        try{
            repositorio.iniciarTransaccion();
            repositorio.modificar(modifPrestamo);
            repositorio.confirmarTransaccion();
        }
        catch(Exception e){
            repositorio.descartarTransaccion();
            throw e;
        }
    }*/

    public void modificarMiembro(Miembro modifMiembro){
        try {
            repositorio.iniciarTransaccion();
            repositorio.modificar(modifMiembro);
            repositorio.confirmarTransaccion();
        } catch (Exception e) {
            repositorio.descartarTransaccion();
            throw e;
        }
    }/* 
    public void modificarCategoria(Categoria modifCategoria){
        try{
            repositorio.iniciarTransaccion();
            repositorio.modificar(modifCategoria);
            repositorio.confirmarTransaccion();
        }
        catch(Exception e){
            repositorio.descartarTransaccion();
            throw e;
        }
    } 
    public void modificarAutor(Autor modifAutor){
        try{
            repositorio.iniciarTransaccion();
            repositorio.modificar(modifAutor);
            repositorio.confirmarTransaccion();
        }
        catch(Exception e){
            repositorio.descartarTransaccion();
            throw e;
        }
    }  
    public void modificarRack(Rack modifRack){
        try{
            repositorio.iniciarTransaccion();
            repositorio.modificar(modifRack);
            repositorio.confirmarTransaccion();
        }
        catch(Exception e){
            repositorio.descartarTransaccion();
            throw e;
        }
    }
    public void modificarLibro(Libro modifLibro){
        try{
            repositorio.iniciarTransaccion();
            repositorio.modificar(modifLibro);
            repositorio.confirmarTransaccion();
        }
        catch(Exception e){
            repositorio.descartarTransaccion();
            throw e;
        }
    }
*/
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
