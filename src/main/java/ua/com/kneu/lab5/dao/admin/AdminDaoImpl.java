package ua.com.kneu.lab5.dao.admin;

import org.hibernate.SessionFactory;
import ua.com.kneu.lab4.entity.user.admin.Admin;

import java.util.List;

public class AdminDaoImpl implements AdminDao{
    private final SessionFactory sessionFactory;

    public AdminDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Admin obj) {

    }

    @Override
    public void update(Admin obj) {

    }

    @Override
    public void delete(Admin obj) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Admin> findAll() {
        return List.of();
    }

    @Override
    public Admin findById(Long id) {
        return null;
    }

    @Override
    public Admin findByName(String name) {
        return null;
    }
}
