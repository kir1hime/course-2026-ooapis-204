package ua.com.kneu.lab5.dao;

import java.util.List;

public interface BaseDao<T> {
    void save(T obj);

    void update(T obj);

    void delete(T obj);

    void deleteAll();

    List<T> findAll();

    T findById(Long id);

    T findByName(String name);
}
