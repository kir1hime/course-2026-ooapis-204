package ua.com.kneu.dao.utils;


import jakarta.persistence.EntityManager;

public interface TransactionTask {
    void doTask(EntityManager entityManager);
}
