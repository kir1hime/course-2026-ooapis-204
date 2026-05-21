package ua.com.kneu.services;

import java.util.List;

public interface BaseService<T> {

    T save(T obj);

    T update(T obj);

    void deleteById(Long id);

    void deleteAll();

    List<T> findAll();

    T findById(Long id);
}
