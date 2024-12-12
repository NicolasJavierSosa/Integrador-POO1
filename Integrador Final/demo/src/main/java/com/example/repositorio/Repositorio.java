package com.example.repositorio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Repositorio {
    private final EntityManager em;
        
    public Repositorio(EntityManagerFactory emf) {
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
}
