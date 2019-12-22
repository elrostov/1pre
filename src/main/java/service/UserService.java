package service;

import model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    boolean addUser(User user);
    boolean deleteUser(User user);
    boolean updateUser(User user);
}
