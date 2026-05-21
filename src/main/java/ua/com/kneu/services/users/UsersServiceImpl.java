package ua.com.kneu.services.users;

import org.springframework.stereotype.Service;
import ua.com.kneu.entities.Users;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService{
    @Override
    public void save(Users obj) {

    }

    @Override
    public void update(Users obj) {

    }

    @Override
    public void delete(Users obj) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Users> findAll() {
        return List.of();
    }

    @Override
    public Users findById(Long id) {
        return null;
    }
}
