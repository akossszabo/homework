package com.mycompany.facade;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityFacade {

    @PersistenceContext(name = "vidamparkJpaPU")
    protected EntityManager entityManager;

    public <T> T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public <T> T read(Class<T> clazz, Object id) {
        return entityManager.find(clazz, id);
    }

    public <T> T update(T entity) {
        return entityManager.merge(entity);
    }

    public <T> void delete(T entity) {
        entityManager.remove(entity);
    }
}
