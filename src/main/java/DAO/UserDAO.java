package DAO;

import model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsersDAO();
    boolean addUserDAO (User user);
    boolean deleteUserDAO (Long id);
    boolean updateUserDAO(User user);
    boolean updateUserPasswordDAO(User user);
    boolean updateUserNameDAO(User user);
}
