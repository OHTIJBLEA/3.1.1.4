package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {

    final SessionFactory sessionFactory = getSessionFactory();
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
//        Session session = sessionFactory.openSession();
//        String query = "CREATE TABLE users (id INT NOT NULL AUTO_INCREMENT, " +
//                "name VARCHAR(45) NOT NULL , " +
//                "lastname VARCHAR(45) NOT NULL , " +
//                "age TINYINT NOT NULL, " +
//                "PRIMARY KEY (id))";
//        try {
//            session.createSQLQuery(query).executeUpdate();
//        } catch (Exception e) {
//            e.getMessage();
//        }
//        session.close();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("CREATE TABLE IF NOT EXISTS user (" +
                "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR(50), " +
                "age INT)");
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
//        Session session = Util.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//            session.createSQLQuery("DROP TABLE user").executeUpdate();
//        transaction.commit();
//        session.close();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("drop table if exists user").addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
//        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        session.save(new User(name, lastName, age));
//        session.getTransaction().commit();
//        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete User where id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> users = session.createCriteria(User.class).list();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
