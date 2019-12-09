package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private Connection connection;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean isBaseContainsAdmin() {
        boolean haveAdmin = false;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE role = ?");
            statement.setString(1, "admin");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                haveAdmin = true;
            }
            statement.close();
            resultSet.close();
            return haveAdmin;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return haveAdmin;
    }

    @Override
    public User getUserByNameAndPass(String name, String pass) {
        User user = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM users" +
                    " WHERE name = ? AND pass = ?");
            statement.setString(1, name);
            statement.setString(2, pass);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4));
                list.add(user);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (" +
                "id bigint auto_increment," +
                " name varchar(256)," +
                " mail varchar(256)," +
                " pass varchar(256)," +
                " role varchar (256)," +
                " primary key (id))");
        stmt.close();
    }

    @Override
    public void addUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into users values (?,?,?,?,?)");
            statement.setLong(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getMail());
            statement.setString(4, user.getPass());
            if (user.getRole() == null) {
                statement.setString(5, "user");
            }
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserByID(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setLong(1, id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editUser(long userID, String newName, String newMail, String newPass) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET name = ?, mail = ?," +
                    " pass= ? WHERE id = ? ");
            statement.setString(1, newName);
            statement.setString(2, newMail);
            statement.setString(3, newPass);
            statement.setLong(4, userID);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(long id) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select * from users where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4));
            }
            resultSet.close();
            statement.close();
            return user;
        } catch (SQLException e) {
            return user;
        }
    }
}
