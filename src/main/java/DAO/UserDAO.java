package DAO;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> getAllUsersDAO();
    boolean addUserDAO (User user);
    boolean deleteUserDAO (User user);
    boolean updateUserDAO(User user);
    Optional<User> getUserIfExistsDAO(User user);
}
