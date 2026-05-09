package ua.com.kneu.dao;

import org.hibernate.SessionFactory;
import ua.com.kneu.entity.Users;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class UserDao extends BaseDao<Users> {

    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void save(Users obj) {
      /*   transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("INSERT INTO `users` (`username`, `password`) VALUES (?, ?)")
                        .setParameter(1, obj.getUsername())
                        .setParameter(2, obj.getPassword())
                        .executeUpdate()
        );*/


        transactionExecutor.execute(entityManager ->
                entityManager.persist(obj)
        );
    }

    @Override
    public void update(Users obj) {
        transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("UPDATE `users` SET username = ?, password = ? WHERE id = ?")
                        .setParameter(1, obj.getUsername())
                        .setParameter(2, obj.getPassword())
                        .setParameter(3, obj.getId())
                        .executeUpdate());

        /*
        transactionExecutor.execute(entityManager ->
                entityManager.createQuery("UPDATE Users u SET u.username = :un, u.password = :pw WHERE u.id = :id")
                        .setParameter("un", obj.getUsername())
                        .setParameter("pw", obj.getPassword())
                        .setParameter("id", obj.getId())
                        .executeUpdate());
        */
        /*
        transactionExecutor.execute(entityManager ->
                entityManager.merge(obj));*/
    }

    @Override
    public void delete(Users obj) {

        transactionExecutor.execute(entityManager -> {
            entityManager.createNativeQuery("DELETE FROM `cards_accounts` WHERE account_id IN (SELECT id FROM `accounts` WHERE client_id IN (SELECT id FROM `clients` WHERE user_id = ?))")
                    .setParameter(1, obj.getId())
                    .executeUpdate();

            entityManager.createNativeQuery("DELETE FROM `accounts` WHERE client_id IN (SELECT id FROM `clients` WHERE user_id = ?)")
                    .setParameter(1, obj.getId())
                    .executeUpdate();

            entityManager.createNativeQuery("DELETE FROM `admins` WHERE user_id = ?")
                    .setParameter(1, obj.getId())
                    .executeUpdate();

            entityManager.createNativeQuery("DELETE FROM `clients` WHERE user_id = ?")
                    .setParameter(1, obj.getId())
                    .executeUpdate();

            entityManager.createQuery("DELETE FROM Users u WHERE u.id = :id")
                    .setParameter("id", obj.getId())
                    .executeUpdate();
        });


       /* transactionExecutor.execute(entityManager -> {
            entityManager.createNativeQuery("DELETE FROM `cards_accounts` WHERE account_id IN (SELECT id FROM `accounts` WHERE client_id IN (SELECT id FROM `clients` WHERE user_id = ?))")
                    .setParameter(1, obj.getId())
                    .executeUpdate();
            entityManager.createQuery("DELETE FROM Accounts a WHERE a.client IN (SELECT c FROM Clients c WHERE c.user.id = :user_id)")
                    .setParameter("user_id", obj.getId())
                    .executeUpdate();
            entityManager.createQuery("DELETE FROM Admins a WHERE a.user.id =  :user_id")
                    .setParameter("user_id", obj.getId())
                    .executeUpdate();
            entityManager.createQuery("DELETE FROM Clients c WHERE c.user.id = :user_id")
                    .setParameter("user_id", obj.getId())
                    .executeUpdate();
            entityManager.createQuery("DELETE FROM Users u WHERE u.id = :id")
                    .setParameter("id", obj.getId())
                    .executeUpdate();
        });
*/
      /*  transactionExecutor.execute(entityManager ->
                entityManager.remove(obj));*/
    }

    @Override
    public void deleteAll() {
        transactionExecutor.execute(entityManager -> {
            entityManager.createNativeQuery("DELETE FROM `cards_accounts`")
                    .executeUpdate();

            entityManager.createNativeQuery("DELETE FROM `cards`")
                    .executeUpdate();

            entityManager.createNativeQuery("DELETE FROM `accounts`")
                    .executeUpdate();

            entityManager.createNativeQuery("DELETE FROM `admins`")
                    .executeUpdate();

            entityManager.createNativeQuery("DELETE FROM `clients`")
                    .executeUpdate();

            entityManager.createQuery("DELETE FROM Users")
                    .executeUpdate();
        });


        /*transactionExecutor.execute(entityManager -> {
            entityManager.createNativeQuery("DELETE FROM `cards_accounts`").executeUpdate();
            entityManager.createQuery("DELETE FROM Cards ").executeUpdate();
            entityManager.createQuery("DELETE FROM Accounts ").executeUpdate();
            entityManager.createQuery("DELETE FROM Admins ").executeUpdate();
            entityManager.createQuery("DELETE FROM Clients ").executeUpdate();
            entityManager.createQuery("DELETE FROM Users").executeUpdate();
        });*/
    }

    @Override
    public List<Users> findAll() {
        AtomicReference<List<Users>> usersList = new AtomicReference<>();


        transactionExecutor.execute(entityManager -> {
            usersList.set(entityManager
                    .createNativeQuery("SELECT * FROM `users`", Users.class)
                    .getResultList());
        });


        /*transactionExecutor.execute(entityManager -> {
            usersList.set(entityManager
                    .createQuery("SELECT u FROM Users u", Users.class)
                    .getResultList());
        });*/

        return usersList.get();
    }

    @Override
    public Users findById(Long id) {
        AtomicReference<Users> user = new AtomicReference<>();

        transactionExecutor.execute(entityManager -> {
            user.set((Users) entityManager
                    .createNativeQuery("SELECT * FROM `users` WHERE id = ?", Users.class)
                    .setParameter(1, id)
                    .getResultList()
                    .getFirst());
        });


        /*
        transactionExecutor.execute(entityManager -> {
            user.set(entityManager
                    .createQuery("SELECT u FROM Users u WHERE u.id = :id", Users.class)
                    .setParameter("id", id)
                    .getSingleResult());
        });
        */

        return user.get();
    }
}