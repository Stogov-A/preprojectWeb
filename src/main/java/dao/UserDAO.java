package dao;

import model.User;

import java.util.List;

public interface UserDAO {

    public boolean isBaseContainsAdmin();

    public User getUserByNameAndPass(String name, String pass);

    public List<User> getAllUsers();

    public void addUser(User user);

    public void deleteUserByID(long id);

    public void editUser(long userID, String newName, String newMail, String newPass, String role);

    public User getUserById(long id);
}
