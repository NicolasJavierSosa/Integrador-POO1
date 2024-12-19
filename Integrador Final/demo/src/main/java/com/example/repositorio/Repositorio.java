package com.example.repositorio;

import java.io.IOException;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
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
        System.out.println("Confirmando transacción...");
        em.getTransaction().commit();
        System.out.println("Transacción confirmada.");
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
    public <T> List<T> buscarPorAtributo(Class<T> clase, String atributo, Object valor) throws IOException {
        try {
            String jpql = "SELECT e FROM " + clase.getSimpleName() + " e WHERE e." + atributo + " = :valor";
            TypedQuery<T> query = em.createQuery(jpql, clase);
            query.setParameter("valor", valor);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar por atributo: " + atributo);
        } finally {
            em.close();
        }
    }
}
