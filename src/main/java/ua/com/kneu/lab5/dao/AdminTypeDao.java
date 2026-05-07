package ua.com.kneu.lab5.dao;

import org.hibernate.SessionFactory;
import ua.com.kneu.lab5.entity.AdminTypes;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AdminTypeDao extends BaseDao<AdminTypes> {

    public AdminTypeDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void save(AdminTypes obj) {
        /*transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("INSERT INTO `admin_types` (`type`) VALUES (?)")
                        .setParameter(1, obj.getType())
                        .executeUpdate()
        );*/

        transactionExecutor.execute(entityManager ->
                entityManager.createQuery(
                                "INSERT INTO AdminTypes (type) " +
                                        "SELECT :type")
                        .setParameter("type", obj.getType())
                        .executeUpdate()
        );

        /*transactionExecutor.execute(entityManager ->
                entityManager.persist(obj)
        );*/
    }

    @Override
    public void update(AdminTypes obj) {
        /*transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("UPDATE `admin_types` SET type = ? WHERE id = ?")
                        .setParameter(1, obj.getType())
                        .setParameter(2, obj.getId())
                        .executeUpdate());*/

        transactionExecutor.execute(entityManager ->
                entityManager.createQuery("UPDATE AdminTypes as a SET " +
                                "a.type =:type WHERE a.id =:id")
                        .setParameter("type", obj.getType())
                        .setParameter("id", obj.getId())
                        .executeUpdate());

        /*transactionExecutor.execute(entityManager ->
                entityManager.merge(obj));*/
    }

    @Override
    public void delete(AdminTypes obj) {
        /*transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("DELETE FROM `admin_types` WHERE id = ?")
                        .setParameter(1, obj.getId())
                        .executeUpdate());*/

        transactionExecutor.execute(entityManager ->
                entityManager.createQuery("DELETE FROM AdminTypes as a WHERE a.id =:id")
                        .setParameter("id", obj.getId())
                        .executeUpdate());

        /*transactionExecutor.execute(entityManager ->
                entityManager.remove(obj));*/
    }

    @Override
    public void deleteAll() {
        /*transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("DELETE FROM `admin_types`").executeUpdate());*/

        transactionExecutor.execute(entityManager ->
                entityManager.createQuery("DELETE FROM AdminTypes as a").executeUpdate());
    }

    @Override
    public List<AdminTypes> findAll() {
        AtomicReference<List<AdminTypes>> adminTypes = new AtomicReference<>();

        /*transactionExecutor.execute(entityManager -> {
            adminTypes.set(entityManager
                    .createNativeQuery("SELECT * FROM `admin_types`", AdminTypes.class)
                    .getResultList());
        });*/

        transactionExecutor.execute(entityManager -> {
            adminTypes.set(entityManager
                    .createQuery("FROM AdminTypes", AdminTypes.class)
                    .getResultList());
        });

        return adminTypes.get();
    }

    @Override
    public AdminTypes findById(Long id) {
        AtomicReference<AdminTypes> adminType = new AtomicReference<>(new AdminTypes());

        /*transactionExecutor.execute(entityManager -> {
            adminType.set((AdminTypes) entityManager
                    .createNativeQuery("SELECT * FROM `admin_types` WHERE id = ?", AdminTypes.class)
                    .setParameter(1, id)
                    .getResultList().getFirst());
        });*/

        transactionExecutor.execute(entityManager -> {
            adminType.set(entityManager
                    .createQuery("SELECT a FROM AdminTypes as a WHERE a.id =:id", AdminTypes.class)
                    .setParameter("id", id)
                    .getResultList().getFirst());
        });

        return adminType.get();
    }
}