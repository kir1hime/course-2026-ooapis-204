package ua.com.kneu.lab5.dao;

import org.hibernate.SessionFactory;
import ua.com.kneu.lab5.entity.Accounts;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AccountDao extends BaseDao<Accounts> {


    public AccountDao(SessionFactory sessionFactory) {
        super(sessionFactory);

    }

    @Override

    public void save(Accounts obj) {
        /*transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("INSERT INTO `accounts` (`iban`, `balance`, `payment_limit`, `currency_id`, `client_id`) VALUES (?,?,?,?,?)")
                        .setParameter(1, obj.getIban())
                        .setParameter(2, obj.getBalance())
                        .setParameter(3, obj.getPaymentLimit())
                        .setParameter(4, obj.getCurrency().getId())
                        .setParameter(5, obj.getClient().getId())
                        .executeUpdate()
        );*/

        transactionExecutor.execute(entityManager ->
                entityManager.createQuery(
                                "INSERT INTO Accounts (iban, balance, paymentLimit, currency, client) " +
                                        "SELECT :iban, :balance, :payment_limit, :currency, :client")
                        .setParameter("iban", obj.getIban())
                        .setParameter("balance", obj.getBalance())
                        .setParameter("payment_limit", obj.getPaymentLimit())
                        .setParameter("currency", obj.getCurrency())
                        .setParameter("client", obj.getClient())
                        .executeUpdate()
        );
       /* transactionExecutor.execute(entityManager ->
                entityManager.persist(obj)
        );*/
    }

    @Override
    public void update(Accounts obj) {
       /* transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("UPDATE `account` SET iban =?, balance = ?, payment_limit = ?, currency_id = ?, client_id = ?, WHERE id =?")
                        .setParameter(1, obj.getIban())
                        .setParameter(2, obj.getBalance())
                        .setParameter(3, obj.getPaymentLimit())
                        .setParameter(4, obj.getCurrency())
                        .setParameter(5, obj.getClient())
                        .setParameter(6, obj.getId())
                        .executeUpdate());*/

        transactionExecutor.execute(entityManager ->
                entityManager.createQuery("UPDATE Accounts as a SET " +
                                "a.iban =: iban, a.balance =: balance, " +
                                "a.paymentLimit =: payment_limit, a.currency =:currency, a.client =: client WHERE a.id =: id")
                        .setParameter("iban", obj.getIban())
                        .setParameter("balance", obj.getBalance())
                        .setParameter("payment_limit", obj.getPaymentLimit())
                        .setParameter("currency", obj.getCurrency())
                        .setParameter("client", obj.getClient())
                        .setParameter("id", obj.getId())
                        .executeUpdate());

       /* transactionExecutor.execute(entityManager ->
                entityManager.merge(obj));*/
    }

    @Override
    public void delete(Accounts obj) {
       /* transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("DELETE FROM `accounts` WHERE id =?")
                        .setParameter(1, obj.getId())
                        .executeUpdate());*/

        transactionExecutor.execute(entityManager ->
                entityManager.createQuery("DELETE FROM Accounts as a WHERE a.id = :id")
                        .setParameter("id", obj.getId())
                        .executeUpdate());

      /*  transactionExecutor.execute(entityManager ->
                entityManager.remove(obj));*/
    }

    @Override
    public void deleteAll() {
       /* transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("DELETE FROM `accounts`").executeUpdate());*/

        transactionExecutor.execute(entityManager ->
                entityManager.createQuery("DELETE FROM Accounts as a").executeUpdate());

    }

    @Override
    public List<Accounts> findAll() {
        AtomicReference<List<Accounts>> accounts = new AtomicReference<>();

        /*transactionExecutor.execute(entityManager -> {
            accounts.set(entityManager
                    .createNativeQuery("SELECT * FROM `accounts`", Accounts.class)
                    .getResultList());
        });
*/
        transactionExecutor.execute(entityManager -> {
            accounts.set(entityManager
                    .createQuery("FROM Accounts", Accounts.class)
                    .getResultList());
        });

        return accounts.get();
    }

    @Override
    public Accounts findById(Long id) {
        AtomicReference<Accounts> account = new AtomicReference<>(new Accounts());

       /* transactionExecutor.execute(entityManager -> {
            account.set((Accounts) entityManager
                    .createNativeQuery("SELECT * FROM `accounts` WHERE id = ?", Accounts.class)
                    .setParameter(1, id)
                    .getResultList().getFirst());
        });*/

        transactionExecutor.execute(entityManager -> {
            account.set(entityManager.createQuery("SELECT a FROM Accounts as a WHERE a.id =:id ", Accounts.class).setParameter("id", id).getResultList().getFirst());
        });

        return account.get();
    }
}