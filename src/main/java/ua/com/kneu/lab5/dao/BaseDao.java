package ua.com.kneu.lab5.dao;

import org.hibernate.SessionFactory;
import ua.com.kneu.lab5.dao.utils.TransactionExecutor;
import java.util.List;

public abstract class BaseDao<T> {
    protected final TransactionExecutor transactionExecutor;

    public BaseDao(SessionFactory sessionFactory) {
        transactionExecutor = new TransactionExecutor(sessionFactory);
    }

    public abstract void save(T obj);

    public abstract void update(T obj);

    public abstract void delete(T obj);

    public abstract void deleteAll();

    public abstract List<T> findAll();

    public abstract T findById(Long id);


}
