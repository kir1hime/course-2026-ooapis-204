package ua.com.kneu.lab5.dao.account;

import org.hibernate.SessionFactory;
import ua.com.kneu.lab4.entity.account.Account;

import java.util.List;

public class AccountDaoImpl implements AccountDao{

    private final SessionFactory sessionFactory;

    public AccountDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void save(Account obj) {

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
