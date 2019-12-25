package service;

import factories.AbstractFactory;
import model.User;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance;

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public List<User> getAllUsers() {
        return AbstractFactory.getDAO().getAllUsersDAO();
    }

    @Override
    public boolean addUser(User user) {
        return AbstractFactory.getDAO().addUserDAO(user);
    }

    @Override
    public boolean deleteUser(User user) {
        return AbstractFactory.getDAO().deleteUserDAO(user);
    }

    @Override
    public boolean updateUser(User user) {
        return AbstractFactory.getDAO().updateUserDAO(user);
    }

    @Override
    public Optional<User> getUserIfExists(User user) {
        return AbstractFactory.getDAO().getUserIfExistsDAO(user);
    }
}