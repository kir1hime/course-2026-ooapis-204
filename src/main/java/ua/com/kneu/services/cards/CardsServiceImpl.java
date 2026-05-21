package ua.com.kneu.services.cards;

import org.springframework.stereotype.Service;
import ua.com.kneu.entities.Cards;

import java.util.List;

@Service
public class CardsServiceImpl implements CardsService{
    @Override
    public void save(Cards obj) {

    }

    @Override
    public void update(Cards obj) {

    }

    @Override
    public void delete(Cards obj) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Cards> findAll() {
        return List.of();
    }

    @Override
    public Cards findById(Long id) {
        return null;
    }
}
