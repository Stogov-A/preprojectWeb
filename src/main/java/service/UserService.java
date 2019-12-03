package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import dao.UserJdbcDAO;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public List<User> getAllUsers() {
        UserDAO dao = getUserDAO();
        return dao.getAllUsers();
    }

    public User getUserByID(long id) {
        UserDAO dao = getUserDAO();
        return dao.getUserById(id);
    }

    public boolean addUser(User user) {
        UserDAO dao = getUserDAO();
        if (dao.getUserById(user.getId()) == null) {
            dao.addUser(user);
            return true;
        }
        return false;
    }

    public void deleteUserByID(long id) {
        UserDAO dao = getUserDAO();
        dao.deleteUserByID(id);
    }

    public void editUser(long id, String name, String mail, String pass) {
        UserDAO dao = getUserDAO();
        dao.editUser(id, name, mail, pass);
    }

    private UserDAO getUserDAO() {
        UserDaoFactory userDaoFactory = new UserDaoFactory();
        return userDaoFactory.getDao();
    }
}

