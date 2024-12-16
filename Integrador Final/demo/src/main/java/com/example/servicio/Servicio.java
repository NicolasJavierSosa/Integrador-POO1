package com.example.servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.example.App;
import com.example.modelo.Autor;
import com.example.modelo.Categoria;
import com.example.modelo.Copia;
import com.example.modelo.Libro;
import com.example.modelo.Miembro;
import com.example.modelo.Rack;
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
        } else if ("Bibliotecario".equals(miembroActivo.getRol())) {
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
            System.err.println("Error modificando miembro: " + e.getMessage());
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

    public void registrarUsuario(Object nuevoMiembro) {
       try {
           repositorio.iniciarTransaccion();
           repositorio.insertar(nuevoMiembro);
           repositorio.confirmarTransaccion();
       } catch (Exception e) {
            repositorio.descartarTransaccion();
            throw e;
       }
    }

    public List<Libro> obtenerLibros() throws IOException{
        List<Libro> librosLista = new ArrayList<>();
        librosLista = repositorio.buscarTodos(Libro.class);
        return librosLista;
    }

    public void agregarCategoria(Categoria nuevaCategoria){
        try{
            repositorio.iniciarTransaccion();
            repositorio.insertar(nuevaCategoria);
            repositorio.confirmarTransaccion();
        }
        catch(Exception e){
            repositorio.descartarTransaccion();
            throw e;
        }
    }
        public void agregarCopia(Copia nuevaCopia){
        try{
            repositorio.iniciarTransaccion();
            repositorio.insertar(nuevaCopia);
            repositorio.confirmarTransaccion();
        }
        catch(Exception e){
            repositorio.descartarTransaccion();
            throw e;
        }
    }
    public void agregarLibro(Libro nuevoLibro){
        try{
            repositorio.iniciarTransaccion();
            repositorio.insertar(nuevoLibro);
            repositorio.confirmarTransaccion();
        }
        catch(Exception e){
            repositorio.descartarTransaccion();
            throw e;
        }
    }
    public List<Categoria> obtenerCategorias() throws IOException{
        List<Categoria> categoriasLista = new ArrayList<>();
        categoriasLista = repositorio.buscarTodos(Categoria.class);
        return categoriasLista;
    }
    public List<Autor> obtenerAutores() throws IOException{
        List<Autor> autoresLista = new ArrayList<>();
        autoresLista = repositorio.buscarTodos(Autor.class);
        return autoresLista;
    }
    public List<Rack> obtenerRacks() throws IOException{
        List<Rack> racksLista = new ArrayList<>();
        racksLista = repositorio.buscarTodos(Rack.class);
        return racksLista;
    }
    public ArrayList<Categoria> crearListaCateg(String categorias){
        ArrayList<Categoria> x = new ArrayList<>();
        String[] split = categorias.split(",\\s*");
        ArrayList<Categoria> categExistentes = new ArrayList<>(this.repositorio.buscarTodos(Categoria.class));
        int tamaño = categExistentes.size();
        for(String categoria:split){
            for(int i = 0; i < tamaño; i++){
                if(categExistentes.get(i).getCategoria().equals(categoria)){
                    x.add(categExistentes.get(i));
                }
            }
        }
        return x;
    }
    public ArrayList<Autor> crearListaAutores(String autores){
        ArrayList<Autor> x = new ArrayList<>();
        String[] split = autores.split(",\\s*");
        ArrayList<Autor> autoresExistentes = new ArrayList<>(this.repositorio.buscarTodos(Autor.class));
        int tamaño = autoresExistentes.size();
        for(String autor:split){
            for(int i = 0; i < tamaño; i++){
                if(autoresExistentes.get(i).getNombre().equals(autor)){
                    x.add(autoresExistentes.get(i));
                }
            }
        }
        return x;
    }
}
