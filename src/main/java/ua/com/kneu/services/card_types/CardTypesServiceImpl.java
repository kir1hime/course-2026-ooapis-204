package ua.com.kneu.services.card_types;

import org.springframework.stereotype.Service;
import ua.com.kneu.entities.CardTypes;

import java.util.List;

@Service
public class CardTypesServiceImpl implements CardTypesService{
    @Override
    public void save(CardTypes obj) {

    }

    @Override
    public void update(CardTypes obj) {

    }

    @Override
    public void delete(CardTypes obj) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<CardTypes> findAll() {
        return List.of();
    }

    @Override
    public CardTypes findById(Long id) {
        return null;
    }
}
