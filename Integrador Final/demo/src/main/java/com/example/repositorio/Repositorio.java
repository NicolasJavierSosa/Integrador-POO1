package com.example.repositorio;

import com.example.modelo.Miembro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
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

    public String autenticar(String Usuario, String contraseña) {
        EntityManager entityManager = getEntityManager(); // Crear EntityManager aquí
        try {
            if (Usuario == null || Usuario.isEmpty() || contraseña == null || contraseña.isEmpty()) {
                return null; // Si los parámetros son nulos o vacíos, retornar null
            }
            
            // Realizas la consulta para buscar al miembro en la base de datos
            Query query = entityManager.createQuery("SELECT m FROM Miembro m WHERE m.idmiembro = :idmiembro AND m.clave = :clave");
            query.setParameter("idmiembro", Usuario);
            query.setParameter("clave", contraseña);
            
            // Si encuentra al miembro, devuelve su rol
            try {
                Object result = query.getSingleResult();
                if (result != null) {
                    // Si el miembro existe, se puede retornar el rol
                    return ((Miembro) result).getRol();
                }
            } catch (NoResultException e) {
                // Si no se encuentra el usuario, se retorna null
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close(); // Cerrar el EntityManager después de usarlo
        }
        return null; // Si no encuentra el usuario o las credenciales son incorrectas
    }
}
