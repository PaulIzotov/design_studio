package com.company.design_studio.service;

import java.util.List;

public interface AbstractService<K, T> {
    List<T> findAll();

    T findById(K id);

    T save(T entity);

    T update(T entity);

    void delete(K id);
}
