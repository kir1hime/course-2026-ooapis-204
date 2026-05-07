package ua.com.kneu.lab5.dao;

import org.hibernate.SessionFactory;
import ua.com.kneu.lab5.entity.Cards;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CardDao extends BaseDao<Cards> {

    public CardDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void save(Cards obj) {
        transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("INSERT INTO `cards` (`hashed_card_number`, `expiry_date`, `card_type_id`) VALUES (?,?,?)")
                        .setParameter(1, obj.getHashedCardNumber())
                        .setParameter(2, obj.getExpiryDate())
                        .setParameter(3, obj.getCardType().getId())
                        .executeUpdate()
        );

        /*transactionExecutor.execute(entityManager ->
                entityManager.createQuery(
                                "INSERT INTO Cards (hashedCardNumber, expiryDate, cardType) " +
                                        "SELECT :hashed_card_number, :expiry_date, :card_type")
                        .setParameter("hashed_card_number", obj.getHashedCardNumber())
                        .setParameter("expiry_date", obj.getExpiryDate())
                        .setParameter("card_type", obj.getCardType())
                        .executeUpdate()
        );*/

        /*transactionExecutor.execute(entityManager ->
                entityManager.persist(obj)
        );*/
    }

    @Override
    public void update(Cards obj) {
        transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("UPDATE `cards` SET hashed_card_number = ?, expiry_date = ?, card_type_id = ? WHERE id = ?")
                        .setParameter(1, obj.getHashedCardNumber())
                        .setParameter(2, obj.getExpiryDate())
                        .setParameter(3, obj.getCardType().getId())
                        .setParameter(4, obj.getId())
                        .executeUpdate());

       /* transactionExecutor.execute(entityManager ->
                entityManager.createQuery("UPDATE Cards as c SET " +
                                "c.hashedCardNumber =:hashed_card_number, c.expiryDate =:expiry_date, " +
                                "c.cardType =:card_type WHERE c.id =:id")
                        .setParameter("hashed_card_number", obj.getHashedCardNumber())
                        .setParameter("expiry_date", obj.getExpiryDate())
                        .setParameter("card_type", obj.getCardType())
                        .setParameter("id", obj.getId())
                        .executeUpdate());*/

        /*transactionExecutor.execute(entityManager ->
                entityManager.merge(obj));*/
    }

    @Override
    public void delete(Cards obj) {
        transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("DELETE FROM `cards` WHERE id = ?")
                        .setParameter(1, obj.getId())
                        .executeUpdate());

       /* transactionExecutor.execute(entityManager ->
                entityManager.createQuery("DELETE FROM Cards as c WHERE c.id =:id")
                        .setParameter("id", obj.getId())
                        .executeUpdate());*/

        /*transactionExecutor.execute(entityManager ->
                entityManager.remove(obj));*/
    }

    @Override
    public void deleteAll() {
        transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("DELETE FROM `cards`").executeUpdate());

        /*transactionExecutor.execute(entityManager ->
                entityManager.createQuery("DELETE FROM Cards as c").executeUpdate());*/
    }

    @Override
    public List<Cards> findAll() {
        AtomicReference<List<Cards>> cards = new AtomicReference<>();

        transactionExecutor.execute(entityManager -> {
            cards.set(entityManager
                    .createNativeQuery("SELECT * FROM `cards`", Cards.class)
                    .getResultList());
        });

        /*transactionExecutor.execute(entityManager -> {
            cards.set(entityManager
                    .createQuery("FROM Cards", Cards.class)
                    .getResultList());
        });
*/
        return cards.get();
    }

    @Override
    public Cards findById(Long id) {
        AtomicReference<Cards> card = new AtomicReference<>(new Cards());

        transactionExecutor.execute(entityManager -> {
            card.set((Cards) entityManager
                    .createNativeQuery("SELECT * FROM `cards` WHERE id = ?", Cards.class)
                    .setParameter(1, id)
                    .getResultList().getFirst());
        });

       /* transactionExecutor.execute(entityManager -> {
            card.set(entityManager
                    .createQuery("SELECT c FROM Cards as c WHERE c.id =:id", Cards.class)
                    .setParameter("id", id)
                    .getResultList().getFirst());
        });*/

        return card.get();
    }
}