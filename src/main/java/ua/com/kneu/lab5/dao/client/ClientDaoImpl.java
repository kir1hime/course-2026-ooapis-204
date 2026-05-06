package ua.com.kneu.lab5.dao.client;

import org.hibernate.SessionFactory;
import ua.com.kneu.lab5.entity.Client;

import java.util.List;

public class ClientDaoImpl implements ClientDao {
    private final SessionFactory sessionFactory;

    public ClientDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Client obj) {

    }

    @Override
    public void update(Client obj) {

    }

    @Override
    public void delete(Client obj) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Client> findAll() {
        return List.of();
    }

    @Override
    public Client findById(Long id) {
        return null;
    }

    @Override
    public Client findByName(String name) {
        return null;
    }
}
