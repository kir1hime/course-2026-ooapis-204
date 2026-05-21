package ua.com.kneu.services.currencies;

import org.springframework.stereotype.Service;
import ua.com.kneu.entities.Currencies;

import java.util.List;

@Service
public class CurrenciesServiceImpl implements CurrenciesService{
    @Override
    public void save(Currencies obj) {

    }

    @Override
    public void update(Currencies obj) {

    }

    @Override
    public void delete(Currencies obj) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Currencies> findAll() {
        return List.of();
    }

    @Override
    public Currencies findById(Long id) {
        return null;
    }
}
