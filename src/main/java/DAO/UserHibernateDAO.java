package DAO;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM users").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void addUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUserByID(long id) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM users WHERE id = :id");
        query.setLong("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void editUser(long userID, String newName, String newMail, String newPass) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE users SET name = :name, mail = :mail," +
                " pass= :pass WHERE id = :id ");
        query.setString("name", newName);
        query.setString("mail", newMail);
        query.setString("pass", newPass);
        query.setLong("id", userID);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public User getUserById(long id) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM users WHERE id = :id");
        query.setLong("id", id);
        List<User> list = query.list();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
