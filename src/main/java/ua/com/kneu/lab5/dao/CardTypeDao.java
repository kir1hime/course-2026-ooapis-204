package ua.com.kneu.lab5.dao;

import org.hibernate.SessionFactory;

import ua.com.kneu.lab5.entity.CardTypes;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CardTypeDao extends BaseDao<CardTypes> {

    public CardTypeDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void save(CardTypes obj) {
        /*transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("INSERT INTO `card_types` (`type`) VALUES (?)")
                        .setParameter(1, obj.getType())
                        .executeUpdate()
        );*/


        transactionExecutor.execute(entityManager ->
                entityManager.persist(obj)
        );
    }

    @Override
    public void update(CardTypes obj) {
        transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("UPDATE `card_types` SET type = ? WHERE id = ?")
                        .setParameter(1, obj.getType())
                        .setParameter(2, obj.getId())
                        .executeUpdate());

      /*  transactionExecutor.execute(entityManager ->
                entityManager.createQuery("UPDATE CardTypes as c SET " +
                                "c.type =:type WHERE c.id =:id")
                        .setParameter("type", obj.getType())
                        .setParameter("id", obj.getId())
                        .executeUpdate());*/

        /*transactionExecutor.execute(entityManager ->
                entityManager.merge(obj));*/
    }

    @Override
    public void delete(CardTypes obj) {
        transactionExecutor.execute(entityManager -> {
            entityManager.createNativeQuery("DELETE FROM `cards_accounts` WHERE card_id IN (SELECT id FROM `cards` WHERE card_type_id = ?)")
                    .setParameter(1, obj.getId())
                    .executeUpdate();

            entityManager.createNativeQuery("DELETE FROM `cards` WHERE card_type_id = ?")
                    .setParameter(1, obj.getId())
                    .executeUpdate();

            entityManager.createNativeQuery("DELETE FROM `card_types`  WHERE id = ?")
                    .setParameter(1, obj.getId())
                    .executeUpdate();
        });
        /*transactionExecutor.execute(entityManager -> {
            entityManager.createNativeQuery("DELETE FROM `cards_accounts` WHERE card_id IN (SELECT id FROM `cards` WHERE card_type_id = ?)")
                    .setParameter(1, obj.getId())
                    .executeUpdate();

            entityManager.createQuery("DELETE FROM Cards c WHERE c.id = :id")
                    .setParameter("id", obj.getId())
                    .executeUpdate();

            entityManager.createQuery("DELETE FROM CardTypes as c WHERE c.id =:id")
                    .setParameter("id", obj.getId())
                    .executeUpdate();
        });*/

        /*transactionExecutor.execute(entityManager ->
                entityManager.remove(obj));*/
    }

    @Override
    public void deleteAll() {
        transactionExecutor.execute(entityManager -> {
            entityManager.createNativeQuery("DELETE FROM `cards_accounts`").executeUpdate();
            entityManager.createNativeQuery("DELETE FROM `cards`").executeUpdate();
            entityManager.createNativeQuery("DELETE FROM `card_types`").executeUpdate();
        });

      /*  transactionExecutor.execute(entityManager -> {
            entityManager.createNativeQuery("DELETE FROM `cards_accounts`").executeUpdate();
            entityManager.createQuery("DELETE FROM Cards as c").executeUpdate();
            entityManager.createQuery("DELETE FROM CardTypes as c").executeUpdate();
        });*/
    }

    @Override
    public List<CardTypes> findAll() {
        AtomicReference<List<CardTypes>> cardTypes = new AtomicReference<>();

        transactionExecutor.execute(entityManager -> {
            cardTypes.set(entityManager
                    .createNativeQuery("SELECT * FROM `card_types`", CardTypes.class)
                    .getResultList());
        });

        /*transactionExecutor.execute(entityManager -> {
            cardTypes.set(entityManager
                    .createQuery("FROM CardTypes", CardTypes.class)
                    .getResultList());
        });
*/
        return cardTypes.get();
    }

    @Override
    public CardTypes findById(Long id) {
        AtomicReference<CardTypes> cardType = new AtomicReference<>(new CardTypes());

        transactionExecutor.execute(entityManager -> {
            cardType.set((CardTypes) entityManager
                    .createNativeQuery("SELECT * FROM `card_types` WHERE id = ?", CardTypes.class)
                    .setParameter(1, id)
                    .getResultList().getFirst());
        });

        /*transactionExecutor.execute(entityManager -> {
            cardType.set(entityManager
                    .createQuery("SELECT c FROM CardTypes as c WHERE c.id =:id", CardTypes.class)
                    .setParameter("id", id)
                    .getResultList().getFirst());
        });*/

        return cardType.get();
    }
}