package ua.com.kneu.lab5.dao.utils;


import jakarta.persistence.EntityManager;

public interface TransactionTask {
    void doTask(EntityManager entityManager);
}
