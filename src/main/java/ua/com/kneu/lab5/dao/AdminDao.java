package ua.com.kneu.lab5.dao;

import org.hibernate.SessionFactory;
import ua.com.kneu.lab5.entity.Admins;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AdminDao extends BaseDao<Admins> {


    public AdminDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void save(Admins obj) {
       /* transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("INSERT INTO `admins` (user_id, admin_type_id) VALUES (?,?)").setParameter(1, obj.getUser().getId()).setParameter(2, obj.getAdminType().getId()).executeUpdate());*/

        transactionExecutor.execute(entityManager ->
                entityManager.createQuery("INSERT INTO Admins (user, adminType) SELECT :user, :admin_type")
                        .setParameter("user", obj.getUser())
                        .setParameter("admin_type", obj.getAdminType())
                        .executeUpdate());

        /*transactionExecutor.execute(entityManager ->
                entityManager.persist(obj));*/
    }

    @Override
    public void update(Admins obj) {
       /* transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("UPDATE `admins` SET user_id = ?, adming_type_id = ? WHERE id = ?")
                        .setParameter(1, obj.getUser().getId())
                        .setParameter(2, obj.getAdminType().getId())
                        .setParameter(3, obj.getId())
                        .executeUpdate());*/
        transactionExecutor.execute(entityManager ->
                entityManager.createQuery("UPDATE Admins as a SET a.user =:user, a.adminType =: admin_type WHERE a.id =: id")
                        .setParameter("user", obj.getUser())
                        .setParameter("admin_type", obj.getAdminType())
                        .setParameter("id", obj.getId())
                        .executeUpdate());

        /*transactionExecutor.execute(entityManager ->
                entityManager.merge(obj));*/
    }

    @Override
    public void delete(Admins obj) {
        /*transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("DELETE FROM `admins` WHERE id =?")
                        .setParameter(1, obj.getId())
                        .executeUpdate());*/

        transactionExecutor.execute(entityManager ->
                entityManager.createQuery("DELETE FROM Admins as a WHERE a.id = :id")
                        .setParameter("id", obj.getId())
                        .executeUpdate());

      /*  transactionExecutor.execute(entityManager ->
                entityManager.remove(obj));*/
    }

    @Override
    public void deleteAll() {
       /* transactionExecutor.execute(entityManager ->
                entityManager.createNativeQuery("DELETE FROM `admins`")
                        .executeUpdate());*/

        transactionExecutor.execute(entityManager ->
                entityManager.createQuery("DELETE FROM Admins as a")
                        .executeUpdate());

    }

    @Override
    public List<Admins> findAll() {

        AtomicReference<List<Admins>> admins = new AtomicReference<>();

       /* transactionExecutor.execute(entityManager -> {
            admins.set(entityManager
                    .createNativeQuery("SELECT * FROM `admins` ", Admins.class)
                    .getResultList());
        });*/
        transactionExecutor.execute(entityManager -> {
            admins.set(entityManager
                    .createQuery("FROM Admins ", Admins.class)
                    .getResultList());
        });

        return admins.get();
    }

    @Override
    public Admins findById(Long id) {
        AtomicReference<Admins> admins = new AtomicReference<>();

       /* transactionExecutor.execute(entityManager -> {
            admins.set((Admins) entityManager
                    .createNativeQuery("SELECT * FROM `admins` WHERE id = ?", Admins.class)
                    .setParameter(1, id)
                    .getResultList().getFirst());
        });
*/
        transactionExecutor.execute(entityManager -> {
            admins.set(entityManager.createQuery("SELECT a FROM Admins as a WHERE a.id =:id ", Admins.class)
                    .setParameter("id", id)
                    .getResultList().getFirst());
        });

        return admins.get();
    }

}
