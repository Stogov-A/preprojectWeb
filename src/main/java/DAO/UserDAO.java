package DAO;

import model.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();

    public void addUser(User user);

    public void deleteUserByID(long id);

    public void editUser(long userID, String newName, String newMail, String newPass);

    public User getUserById(long id);
}
