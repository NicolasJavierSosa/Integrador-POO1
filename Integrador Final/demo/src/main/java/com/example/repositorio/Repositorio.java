package com.example.repositorio;

import com.example.modelo.Miembro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

public class Repositorio {
    private EntityManagerFactory em;

    public Repositorio(EntityManagerFactory em) {
        this.em = em;
    }

    // Método para obtener el EntityManager
    public EntityManager getEntityManager() {
        return em.createEntityManager(); // Crear un EntityManager a partir del EntityManagerFactory
    }
    
    public void iniciarTransaccion() {
        getEntityManager().getTransaction().begin();
    }
    
    public void confirmarTransaccion() {
        getEntityManager().getTransaction().commit();
    }

    public void descartarTransaccion() {
        getEntityManager().getTransaction().rollback();
    }
    
    public void insertar(Object o) {
        EntityManager entityManager = getEntityManager();
        entityManager.persist(o);
    }
    
    public void modificar(Object o){
        EntityManager entityManager = getEntityManager();
        entityManager.merge(o);
    }

    public void eliminar(Object o){
        EntityManager entityManager = getEntityManager();
        entityManager.remove(o);
    }

    public void refrescar(Object o) {
        EntityManager entityManager = getEntityManager();
        entityManager.refresh(o);
    }

    public String autenticar(String nombreUsuario, String contrasena) {
        EntityManager entityManager = getEntityManager(); // Crear EntityManager aquí
        try {
            // Realizas la consulta para buscar al miembro en la base de datos
            Query query = entityManager.createQuery("SELECT m FROM Miembro m WHERE m.nombreUsuario = :nombreUsuario AND m.contrasena = :contrasena");
            query.setParameter("nombreUsuario", nombreUsuario);
            query.setParameter("contrasena", contrasena);
            
            // Si encuentra al miembro, devuelve su rol
            Object result = query.getSingleResult();
            if (result != null) {
                // Si el miembro existe, se puede retornar el rol
                // Esto asume que 'Miembro' tiene un campo 'rol'
                return ((Miembro) result).getRol();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close(); // Cerrar el EntityManager después de usarlo
        }
        return null; // Si no encuentra el usuario o las credenciales son incorrectas
    }
}
