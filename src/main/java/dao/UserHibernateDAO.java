package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private SessionFactory sessionFactory;
    private Session session;

    public UserHibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean isBaseContainsAdmin() {
        session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User WHERE role = :role");
        query.setString("role", "admin");
        List<User>list = query.list();
        session.close();
        return list.size() > 0;
    }

    @Override
    public User getUserByNameAndPass(String name, String pass) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User WHERE name = :name AND pass = :pass");
        query.setString("name", name);
        query.setString("pass", pass);
        List<User> list = query.list();
        session.close();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void addUser(User user) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if(user.getRole() == null) {
            user.setRole("user");
        }
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUserByID(long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User WHERE id = :id");
        query.setLong("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void editUser(long userID, String newName, String newMail, String newPass, String role) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET name = :name, mail = :mail," +
                " pass= :pass, role = :role WHERE id = :id ");
        query.setString("name", newName);
        query.setString("mail", newMail);
        query.setString("pass", newPass);
        query.setString("role", role);
        query.setLong("id", userID);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public User getUserById(long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE id = :id");
        query.setLong("id", id);
        List<User> list = query.list();
        session.close();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
