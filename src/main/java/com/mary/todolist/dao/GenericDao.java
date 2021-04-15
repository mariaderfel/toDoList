package com.mary.todolist.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

@Transactional
public class GenericDao<T> implements Dao<T> {

    @Autowired
    private EntityManager entityManager;
    private Class<T> type;

    public GenericDao() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public T find(Long id) {
        return entityManager.find(type, id);
    }

    @Override
    public Collection<T> findAll() {
        Query query=entityManager.createQuery("SELECT t FROM " + type.getCanonicalName() +" t", type);
        return query.getResultList();
    }

    @Override
    public void save(T t) {
        entityManager.persist(t);
    }

    @Override
    public void upDate(T t) {
        entityManager.merge(t);
    }

    @Override
    public void delete(T t) {
        entityManager.remove(t);
    }
}
