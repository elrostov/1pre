package service;

import DAO.UserJdbcDAO;
import connections.ConnectionJDBC;
import model.User;

import java.util.List;

public class UserService {

    private static UserService instance;

    private UserService(){}

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public List<User> getAllUsers(){
        return getUserDAO().getAllUsersDAO();
    }

    public boolean addUser(User user) {
        return getUserDAO().addUserDAO(user);
    }

    public UserJdbcDAO getUserDAO() {
        return new UserJdbcDAO(ConnectionJDBC.getInstance().getConnection());
    }

    public boolean deleteUser(Long id) {
        return getUserDAO().deleteUserDAO(id);
    }

    public boolean updateUser(User user) {
        if (user.getName().isEmpty()) {
            return getUserDAO().updateUserPasswordDAO(user);
        } else if (user.getPassword().isEmpty()) {
            return getUserDAO().updateUserNameDAO(user);
        } else {
            return getUserDAO().updateUserDAO(user);
        }
    }
}