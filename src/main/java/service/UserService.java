package service;

import DAO.UserJdbcDAO;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public List<User> getAllUsers() {
        UserJdbcDAO dao = getUserDAO();
        return dao.getAllUsers();
    }

    public User getUserByID(long id) {
        UserJdbcDAO dao = getUserDAO();
        return dao.getUserById(id);
    }

    public void createTable() {
        UserJdbcDAO dao = getUserDAO();
        try {
            dao.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(User user) {
        UserJdbcDAO dao = getUserDAO();
        if (dao.getUserById(user.getId()) == null) {
            dao.addUser(user);
            return true;
        }
        return false;
    }

    public void deleteUserByID(long id) {
        UserJdbcDAO dao = getUserDAO();
        dao.deleteUserByID(id);
    }

    public void editUser(long id, String name, String mail, String pass) {
        UserJdbcDAO dao = getUserDAO();
        dao.editUser(id, name, mail, pass);
    }

    private static UserJdbcDAO getUserDAO() {
        return new UserJdbcDAO(getMysqlConnection());
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=dagmar").       //password
                    append("&serverTimezone=UTC");   //setup server time
            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}

