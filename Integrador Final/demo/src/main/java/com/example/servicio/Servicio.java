package com.example.servicio;

import com.example.modelo.Bibliotecario;
import com.example.modelo.Miembro;
import com.example.modelo.Usuario;
import com.example.repositorio.Repositorio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class Servicio {
    private Repositorio repositorio;

    public Servicio(Repositorio p) {
        this.repositorio = p;
    }

    public String autenticarMiembro(String nombreUsuario, String contraseña) {
        EntityManager entityManager = repositorio.getEntityManager();
        
        // Consulta para encontrar el Miembro (Usuario o Bibliotecario) por nombre de usuario y contraseña
        TypedQuery<Miembro> query = entityManager.createQuery(
            "SELECT m FROM Miembro m WHERE m.nombreUsuario = :nombreUsuario AND m.contrasena = :contrasena", Miembro.class);
        query.setParameter("nombreUsuario", nombreUsuario);
        query.setParameter("contrasena", contraseña);

        try {
            Miembro miembro = query.getSingleResult();
            
            if (miembro instanceof Bibliotecario) {
                return "bibliotecario"; // Si es Bibliotecario, devolver "bibliotecario"
            } else if (miembro instanceof Usuario) {
                return "usuario"; // Si es Usuario, devolver "usuario"
            }
        } catch (Exception e) {
            return null;  // Si no se encuentra el miembro o hay error, devolver null
        }

        return null;
    }
}
