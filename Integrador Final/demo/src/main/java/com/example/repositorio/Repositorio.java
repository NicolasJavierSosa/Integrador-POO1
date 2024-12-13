package com.example.repositorio;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class Repositorio {
    private final EntityManager em;

    public Repositorio(EntityManagerFactory em) {
        this.em = em.createEntityManager();
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

    public <Todos extends Object> Todos buscar(Class<Todos> clase, Object id) {
        return (Todos) this.em.find(clase, id);
    }
    public <Todos> List<Todos> buscarTodos(Class<Todos> clase) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Todos> cq = cb.createQuery(clase);
    Root<Todos> rootEntry = cq.from(clase);
    CriteriaQuery<Todos> all = cq.select(rootEntry);
    return em.createQuery(all).getResultList();
    }
}
