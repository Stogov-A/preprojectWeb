package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;
import java.util.List;

public class UserService {
    private UserDAO userDAO;
    private static UserService userService;

    private UserService() {
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
        if (userDAO == null) {
            UserDaoFactory userDaoFactory = new UserDaoFactory();
            userDAO = userDaoFactory.getDao();
        }
        return userDAO;
    }
}

