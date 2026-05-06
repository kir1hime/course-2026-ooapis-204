package ua.com.kneu.lab5.dao.card;

import org.hibernate.SessionFactory;
import ua.com.kneu.lab4.entity.card.Card;

import java.util.List;

public class CardDaoImpl implements CardDao{
    private final SessionFactory sessionFactory;

    public CardDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Card obj) {

    }

    @Override
    public void update(Card obj) {

    }

    @Override
    public void delete(Card obj) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Card> findAll() {
        return List.of();
    }

    @Override
    public Card findById(Long id) {
        return null;
    }

    @Override
    public Card findByName(String name) {
        return null;
    }
}
