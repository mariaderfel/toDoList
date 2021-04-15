package com.mary.todolist.dao;

import java.util.Collection;

public interface Dao<T>{
    T find(Long id);
    Collection<T> findAll();
    void save(T t);
    void upDate(T t);
    void delete(T t);
}
