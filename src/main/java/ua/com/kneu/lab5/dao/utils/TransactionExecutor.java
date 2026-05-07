package ua.com.kneu.lab5.dao.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.SessionFactory;


public class TransactionExecutor {
    private final SessionFactory sessionFactory;

    public TransactionExecutor(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void execute(TransactionTask transactionTask) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try (entityManager) {
            entityTransaction.begin();
            transactionTask.doTask(entityManager);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            throw new RuntimeException("Error during transaction executing");
        }
    }

}
