package ua.com.kneu.lab5.dao;

import org.hibernate.SessionFactory;
import ua.com.kneu.lab5.entity.Currencies;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CurrencyDao extends BaseDao<Currencies> {

    public CurrencyDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void save(Currencies obj) {
       /* transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("INSERT INTO `currencies` (currency)" +
                                "VALUES (?)")
                        .setParameter(1, obj.getCurrency())
                        .executeUpdate());*/

        transactionExecutor.execute(entityManager ->
                entityManager.persist(obj));
    }

    @Override
    public void update(Currencies obj) {
        transactionExecutor.execute(entityManager -> {
            entityManager.createNativeQuery("UPDATE `currencies` SET currency =? WHERE id = ?")
                    .setParameter(1, obj.getCurrency())
                    .setParameter(2, obj.getId())
                    .executeUpdate();
            entityManager.clear();
        });

       /* transactionExecutor.execute(entityManager ->
                entityManager.createQuery("UPDATE Currencies as c SET c.currency =: currency WHERE c.id =: id")
                        .setParameter("currency", obj.getCurrency())
                        .setParameter("id", obj.getId())
                        .executeUpdate());*/

       /* transactionExecutor.execute(entityManager ->
                entityManager.merge(obj));*/
    }

    @Override
    public void delete(Currencies obj) {
        transactionExecutor.execute(entityManager -> {
            entityManager.createNativeQuery("DELETE FROM `cards_accounts` WHERE account_id IN (SELECT id FROM `accounts` WHERE currency_id = ?)")
                    .setParameter(1, obj.getId())
                    .executeUpdate();

            entityManager.createNativeQuery("DELETE FROM `accounts` WHERE currency_id = ?")
                    .setParameter(1, obj.getId())
                    .executeUpdate();

            entityManager.createQuery("DELETE FROM Currencies c WHERE c.id = :id")
                    .setParameter("id", obj.getId())
                    .executeUpdate();
        });
        /*transactionExecutor.execute(entityManager -> {
            entityManager.createNativeQuery("DELETE FROM `cards_accounts` WHERE account_id IN (SELECT id FROM `accounts` WHERE currency_id = ?)")
                    .setParameter(1, obj.getId())
                    .executeUpdate();
            entityManager.createQuery("DELETE FROM Accounts  as a WHERE  a.id = :id")
                    .setParameter("id", obj.getId()).executeUpdate();
            entityManager.createQuery("DELETE FROM Currencies as c WHERE c.id = :id")
                    .setParameter("id", obj.getId())
                    .executeUpdate();
        });*/

    /* transactionExecutor.execute(entityManager ->
            entityManager.remove(obj)); */
    }

    @Override
    public void deleteAll() {
        transactionExecutor.execute(entityManager -> {
            entityManager.createNativeQuery("DELETE FROM `cards_accounts`").executeUpdate();
            entityManager.createNativeQuery("DELETE  FROM `accounts`").executeUpdate();
            entityManager.createNativeQuery("DELETE FROM `currencies`")
                    .executeUpdate();
        });

      /*  transactionExecutor.execute(entityManager -> {
            entityManager.createNativeQuery("DELETE FROM `cards_accounts`").executeUpdate();
            entityManager.createQuery("DELETE FROM Accounts  as a").executeUpdate();
            entityManager.createQuery("DELETE FROM Currencies as c").executeUpdate();
        });*/
    }

    @Override
    public List<Currencies> findAll() {
        AtomicReference<List<Currencies>> currencies = new AtomicReference<>();

        transactionExecutor.execute(entityManager -> {
            currencies.set(entityManager
                    .createNativeQuery("SELECT * FROM `currencies`", Currencies.class)
                    .getResultList());
        });

       /* transactionExecutor.execute(entityManager -> {
            currencies.set(entityManager
                    .createQuery("FROM Currencies", Currencies.class)
                    .getResultList());
        });*/

        return currencies.get();
    }

    @Override
    public Currencies findById(Long id) {
        AtomicReference<Currencies> currency = new AtomicReference<>(new Currencies());

        transactionExecutor.execute(entityManager -> {
            currency.set((Currencies) entityManager
                    .createNativeQuery("SELECT * FROM `currencies` WHERE id = ?", Currencies.class)
                    .setParameter(1, id)
                    .getResultList().getFirst());
        });

        /*transactionExecutor.execute(entityManager -> {
            currency.set(entityManager
                    .createQuery("SELECT c FROM Currencies as c WHERE c.id = :id", Currencies.class)
                    .setParameter("id", id)
                    .getResultList().getFirst());
        });

*/

        return currency.get();
    }

}
