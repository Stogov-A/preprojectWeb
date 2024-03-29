package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import dao.UserJdbcDAO;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDAO userDAO;
    private static UserService userService;

    private UserService() {
    }

    public User getUserByNameAndPass(String name, String pass) {
        UserDAO dao = getUserDAO();
        return dao.getUserByNameAndPass(name, pass);
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getAllUsers() {
        UserDAO dao = getUserDAO();
        return dao.getAllUsers();
    }

    public User getUserByID(long id) {
        UserDAO dao = getUserDAO();
        return dao.getUserById(id);
    }

    public boolean isBaseContainsAdmin(){
        UserDAO dao = getUserDAO();
        return dao.isBaseContainsAdmin();
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

    public void editUser(long id, String name, String mail, String pass, String role) {
        UserDAO dao = getUserDAO();
        dao.editUser(id, name, mail, pass, role);
    }

    private UserDAO getUserDAO() {
        if (userDAO == null) {
            UserDaoFactory userDaoFactory = new UserDaoFactory();
            userDAO = userDaoFactory.getDao();
        }
        return userDAO;
    }

    public void createTable(){
        UserJdbcDAO dao = (UserJdbcDAO) getUserDAO();
        try {
            dao.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

