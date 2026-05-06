package ua.com.kneu.lab5.dao;

import org.hibernate.SessionFactory;
import ua.com.kneu.lab5.entity.Clients;

import java.util.List;

public class ClientDao extends BaseDao<Clients> {

    public ClientDao(SessionFactory sessionFactory) {
       super(sessionFactory);
    }

    @Override
    public void save(Clients obj) {

    }

    @Override
    public void update(Clients obj) {

    }

    @Override
    public void delete(Clients obj) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Clients> findAll() {
        return List.of();
    }

    @Override
    public Clients findById(Long id) {
        return null;
    }

    @Override
    public Clients findByName(String name) {
        return null;
    }
}
