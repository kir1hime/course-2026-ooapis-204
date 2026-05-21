package ua.com.kneu.services.clients;

import org.springframework.stereotype.Service;
import ua.com.kneu.entities.Clients;

import java.util.List;

@Service
public class ClientsServiceImpl implements ClientsService{
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
}
