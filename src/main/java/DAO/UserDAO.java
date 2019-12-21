package DAO;

import model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsersDAO();
    boolean addUserDAO (User user);
    boolean deleteUserDAO (User user);
    boolean updateUserDAO(User user);
}
