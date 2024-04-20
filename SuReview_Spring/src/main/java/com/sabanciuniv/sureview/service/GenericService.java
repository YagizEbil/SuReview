package com.sabanciuniv.sureview.service;

public interface GenericService<T> {
    T save(T entity);
    T findById(String id);
    Iterable<T> findAll();
    void deleteById(String id);
}
