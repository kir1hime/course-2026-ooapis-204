package ua.com.kneu.lab5.dao;

import org.hibernate.SessionFactory;
import ua.com.kneu.lab5.entity.Clients;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ClientDao extends BaseDao<Clients> {

    public ClientDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void save(Clients obj) {
       /* transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("INSERT INTO `clients` (`first_name`, `last_name`, `email`, `patronymic`, `age`, `phone`, `address`, `user_id`) VALUES (?,?,?,?,?,?,?,?)")
                        .setParameter(1, obj.getFirstName())
                        .setParameter(2, obj.getLastName())
                        .setParameter(3, obj.getEmail())
                        .setParameter(4, obj.getPatronymic())
                        .setParameter(5, obj.getAge())
                        .setParameter(6, obj.getPhone())
                        .setParameter(7, obj.getAddress())
                        .setParameter(8, obj.getUser().getId())
                        .executeUpdate()
        );*/


        transactionExecutor.execute(entityManager ->
                entityManager.persist(obj)
        );
    }

    @Override
    public void update(Clients obj) {
        /*transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("UPDATE `clients` SET first_name = ?, last_name = ?, email = ?, patronymic = ?, age = ?, phone = ?, address = ?, user_id = ? WHERE id = ?")
                        .setParameter(1, obj.getFirstName())
                        .setParameter(2, obj.getLastName())
                        .setParameter(3, obj.getEmail())
                        .setParameter(4, obj.getPatronymic())
                        .setParameter(5, obj.getAge())
                        .setParameter(6, obj.getPhone())
                        .setParameter(7, obj.getAddress())
                        .setParameter(8, obj.getUser().getId())
                        .setParameter(9, obj.getId())
                        .executeUpdate());*/

        transactionExecutor.execute(entityManager ->
                entityManager.createQuery("UPDATE Clients as c SET " +
                                "c.firstName =:firstName, c.lastName =:lastName, c.email =:email, " +
                                "c.patronymic =:patronymic, c.age =:age, c.phone =:phone, " +
                                "c.address =:address, c.user =:user WHERE c.id =:id")
                        .setParameter("firstName", obj.getFirstName())
                        .setParameter("lastName", obj.getLastName())
                        .setParameter("email", obj.getEmail())
                        .setParameter("patronymic", obj.getPatronymic())
                        .setParameter("age", obj.getAge())
                        .setParameter("phone", obj.getPhone())
                        .setParameter("address", obj.getAddress())
                        .setParameter("user", obj.getUser())
                        .setParameter("id", obj.getId())
                        .executeUpdate());

        /*transactionExecutor.execute(entityManager ->
                entityManager.merge(obj));*/
    }

    @Override
    public void delete(Clients obj) {
        /*transactionExecutor.execute(entityManager -> {
            entityManager.createNativeQuery("DELETE FROM `cards_accounts` WHERE account_id IN (SELECT id FROM `accounts` WHERE client_id = ?)")
                    .setParameter(1, obj.getId())
                    .executeUpdate();
            entityManager.createNativeQuery("DELETE FROM `accounts` WHERE client_id = ?")
                    .setParameter(1, obj.getId())
                    .executeUpdate();
            entityManager.createNativeQuery("DELETE FROM `clients` WHERE id = ?")
                    .setParameter(1, obj.getId())
                    .executeUpdate();
        });*/

        transactionExecutor.execute(entityManager -> {
            entityManager.createNativeQuery("DELETE FROM `cards_accounts` WHERE account_id IN (SELECT id FROM `accounts` WHERE client_id = ?)")
                    .setParameter(1, obj.getId())
                    .executeUpdate();

            entityManager.createQuery("DELETE FROM Accounts a WHERE a.id = :id ")
                    .setParameter("id", obj.getId())
                    .executeUpdate();

            entityManager.createQuery("DELETE FROM Clients as c WHERE c.id =:id")
                    .setParameter("id", obj.getId())
                    .executeUpdate();
        });

        /*transactionExecutor.execute(entityManager ->
                entityManager.remove(obj));*/
    }

    @Override
    public void deleteAll() {
       /* transactionExecutor.execute(entityManager -> {
            entityManager.createNativeQuery("DELETE  FROM `cards_accounts`").executeUpdate();
            entityManager.createNativeQuery("DELETE FROM `account`").executeUpdate();
            entityManager.createNativeQuery("DELETE FROM `clients`").executeUpdate();
        });*/

        transactionExecutor.execute(entityManager -> {
            entityManager.createNativeQuery("DELETE  FROM `cards_accounts`").executeUpdate();
            entityManager.createQuery("DELETE FROM Accounts  as a").executeUpdate();
            entityManager.createQuery("DELETE FROM Clients as c").executeUpdate();
        });
    }

    @Override
    public List<Clients> findAll() {
        AtomicReference<List<Clients>> clients = new AtomicReference<>();

        /*transactionExecutor.execute(entityManager -> {
            clients.set(entityManager
                    .createNativeQuery("SELECT * FROM `clients`", Clients.class)
                    .getResultList());
        });*/

        transactionExecutor.execute(entityManager -> {
            clients.set(entityManager
                    .createQuery("FROM Clients", Clients.class)
                    .getResultList());
        });

        return clients.get();
    }

    @Override
    public Clients findById(Long id) {
        AtomicReference<Clients> client = new AtomicReference<>(new Clients());

        /*transactionExecutor.execute(entityManager -> {
            client.set((Clients) entityManager
                    .createNativeQuery("SELECT * FROM `clients` WHERE id = ?", Clients.class)
                    .setParameter(1, id)
                    .getResultList().getFirst());
        });*/

        transactionExecutor.execute(entityManager -> {
            client.set(entityManager
                    .createQuery("SELECT c FROM Clients as c WHERE c.id =:id", Clients.class)
                    .setParameter("id", id)
                    .getResultList().getFirst());
        });

        return client.get();
    }
}
