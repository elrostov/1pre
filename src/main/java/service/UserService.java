package service;

import DAO.UserHibernateDAO;
import util.HibernateConnection;
import model.User;
import org.hibernate.SessionFactory;
import util.UserDaoFactory;

import java.util.List;

public class UserService {

    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public List<User> getAllUsers(){
//        return JdbcConnection.getUserJdbcDAO().getAllUsersDAO();
        return UserDaoFactory.getInstance().getConnection().getAllUsersDAO();
    }

    public boolean addUser(User user) {
//        return JdbcConnection.getUserJdbcDAO().addUserDAO(user);
        return UserDaoFactory.getInstance().getConnection().addUserDAO(user);
    }

    public boolean deleteUser(User user) {
//        return JdbcConnection.getUserJdbcDAO().deleteUserDAO(id);
        return UserDaoFactory.getInstance().getConnection().deleteUserDAO(user);
    }

    public boolean updateUser(User user) {
//        return JdbcConnection.getUserJdbcDAO().updateUserDAO(user);
        return UserDaoFactory.getInstance().getConnection().updateUserDAO(user);
    }
}