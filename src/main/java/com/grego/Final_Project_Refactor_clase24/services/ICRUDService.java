package com.grego.Final_Project_Refactor_clase24.services;

import java.util.List;

public interface ICRUDService<T> {

    T findById(Integer id);

    T save(T entity);

    void deleteById(Integer id);

    T update(Integer id, T entity);

    List<T> findAll();
}
