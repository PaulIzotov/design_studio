package com.company.designstudio.service;

import java.util.List;

public interface AbstractService<K, T> {
    List<T> findAll(int limit, long offset);

    T findById(K id);

    T save(T entity);

    T update(T entity);

    void delete(K id);

    long count();
}
