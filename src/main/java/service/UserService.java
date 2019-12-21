package service;

import DAO.UserHibernateDAO;
import DAO.UserJdbcDAO;
import connections.HibernateConnection;
import connections.JdbcConnection;
import model.User;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserService {

    private static UserService instance;

    private SessionFactory sessionFactory;

    private UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService(HibernateConnection.getSessionFactory());
        }
        return instance;
    }

    public List<User> getAllUsers(){
//        return JdbcConnection.getUserJdbcDAO().getAllUsersDAO();
        return new UserHibernateDAO(sessionFactory.openSession()).getAllUsersDAO();
    }

    public boolean addUser(User user) {
//        return JdbcConnection.getUserJdbcDAO().addUserDAO(user);
        return new UserHibernateDAO(sessionFactory.openSession()).addUserDAO(user);
    }

    public boolean deleteUser(User user) {
//        return JdbcConnection.getUserJdbcDAO().deleteUserDAO(id);
        return new UserHibernateDAO(sessionFactory.openSession()).deleteUserDAO(user);
    }

    public boolean updateUser(User user) {
//        return JdbcConnection.getUserJdbcDAO().updateUserDAO(user);
        return new UserHibernateDAO(sessionFactory.openSession()).updateUserDAO(user);
    }
}