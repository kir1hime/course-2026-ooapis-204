package ua.com.kneu.lab5.dao;

import org.hibernate.SessionFactory;
import ua.com.kneu.lab4.entity.account.Account;

import javax.persistence.EntityTransaction;
import java.util.List;

public class AccountDao extends BaseDao<Account> {


    public AccountDao(SessionFactory sessionFactory) {
        super(sessionFactory);

    }

    @Override
    public void save(Account obj) {
        EntityTransaction l = entityManager.getTransaction();
        entityManager.persist(obj);
    }

    @Override
    public void update(Account obj) {

    }

    @Override
    public void delete(Account obj) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Account> findAll() {
        return List.of();
    }

    @Override
    public Account findById(Long id) {
        return null;
    }

    @Override
    public Account findByName(String name) {
        return null;
    }
}
