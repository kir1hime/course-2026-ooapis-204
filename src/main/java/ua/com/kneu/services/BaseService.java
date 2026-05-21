package ua.com.kneu.services;

import java.util.List;

public interface BaseService<T> {

    public abstract void save(T obj);

    public abstract void update(T obj);

    public abstract void delete(T obj);

    public abstract void deleteAll();

    public abstract List<T> findAll();

    public abstract T findById(Long id);
}
