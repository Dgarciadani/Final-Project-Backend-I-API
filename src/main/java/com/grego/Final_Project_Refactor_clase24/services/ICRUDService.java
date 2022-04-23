package com.grego.Final_Project_Refactor_clase24.services;

import com.grego.Final_Project_Refactor_clase24.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ICRUDService<T> {

    T findById(Integer id) throws ResourceNotFoundException;

    T save(T entity);

    void deleteById(Integer id) throws ResourceNotFoundException;

    T update(Integer id, T entity) throws ResourceNotFoundException;

    List<T> findAll();
}
