package ua.com.kneu.lab5.dao;

import org.hibernate.SessionFactory;
import ua.com.kneu.lab4.entity.card.Card;

import java.util.List;

public class CardDao extends BaseDao<Card> {


    public CardDao(SessionFactory sessionFactory) {
        super(sessionFactory);
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
