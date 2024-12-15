package com.example.repositorio;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class Repositorio {
    private final EntityManager em;

    public Repositorio(EntityManagerFactory emf) {
        if (!emf.isOpen()) {
            throw new IllegalArgumentException("EntityManagerFactory está cerrado.");
        }
        this.em = emf.createEntityManager();
    }
    public void iniciarTransaccion() {
        em.getTransaction().begin();
    }
    
    public void confirmarTransaccion() {
        em.getTransaction().commit();
    }

    public void descartarTransaccion() {
        em.getTransaction().rollback();
    }
    
    public void insertar(Object o) {
        this.em.persist(o);
    }
    
    public void modificar(Object o){
        this.em.merge(o);
    }

    public void eliminar(Object o){
        this.em.remove(o);
    }

    public void refrescar(Object o) {
        this.em.refresh(o);
    }

    public <T> T buscar(Class<T> clase, Object id) {
        return em.find(clase, id);
    }

public <T> List<T> buscarTodos(Class<T> clase) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(clase);
            Root<T> rootEntry = cq.from(clase);
            cq.select(rootEntry);
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar todos los registros de " + clase.getSimpleName(), e);
        }
    }
}
