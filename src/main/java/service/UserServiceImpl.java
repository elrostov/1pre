package service;

import model.User;
import DAO.UserDaoFactory;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance;

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    public List<User> getAllUsers() {
        return UserDaoFactory.getDAO().getAllUsersDAO();
    }

    public boolean addUser(User user) {
        return UserDaoFactory.getDAO().addUserDAO(user);
    }

    public boolean deleteUser(User user) {
        return UserDaoFactory.getDAO().deleteUserDAO(user);
    }

    public boolean updateUser(User user) {
        return UserDaoFactory.getDAO().updateUserDAO(user);
    }
}