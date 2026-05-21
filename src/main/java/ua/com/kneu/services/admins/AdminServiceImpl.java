package ua.com.kneu.services.admins;

import org.springframework.stereotype.Service;
import ua.com.kneu.entities.Admins;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Override
    public void save(Admins obj) {

    }

    @Override
    public void update(Admins obj) {

    }

    @Override
    public void delete(Admins obj) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Admins> findAll() {
        return List.of();
    }

    @Override
    public Admins findById(Long id) {
        return null;
    }
}
