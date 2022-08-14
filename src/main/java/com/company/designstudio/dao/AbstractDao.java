package com.company.designstudio.dao;

import java.util.List;

public interface AbstractDao<K, T> {
    T findById(K id);

    List<T> findAll();

    T save(T entity);

    T update(T entity);

    boolean delete(K id);
}
