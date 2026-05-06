package ua.com.kneu.lab5.dao;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public abstract class BaseDao<T> {
    protected final EntityManager entityManager;
    protected final EntityTransaction transaction;

    public BaseDao(SessionFactory sessionFactory) {
        entityManager = sessionFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    public abstract void save(T obj);

    public abstract void update(T obj);

    public abstract void delete(T obj);

    public abstract void deleteAll();

    public abstract List<T> findAll();

    public abstract T findById(Long id);

    public abstract T findByName(String name);
}
