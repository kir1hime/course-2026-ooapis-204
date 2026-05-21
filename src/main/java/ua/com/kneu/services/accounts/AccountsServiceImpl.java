package ua.com.kneu.services.accounts;

import org.springframework.stereotype.Service;
import ua.com.kneu.entities.Accounts;

import java.util.List;

@Service
public class AccountsServiceImpl implements AccountsService{
    @Override
    public void save(Accounts obj) {

    }

    @Override
    public void update(Accounts obj) {

    }

    @Override
    public void delete(Accounts obj) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Accounts> findAll() {
        return List.of();
    }

    @Override
    public Accounts findById(Long id) {
        return null;
    }
}
