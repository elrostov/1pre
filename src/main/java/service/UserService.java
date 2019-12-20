package service;

import DAO.UserHibernateDAO;
import DAO.UserJdbcDAO;
import connections.ConnectionHibernate;
import connections.ConnectionJDBC;
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
            instance = new UserService(ConnectionHibernate.getSessionFactory());
        }
        return instance;
    }

    public List<User> getAllUsers(){
        return new UserHibernateDAO(sessionFactory.openSession()).getAllUsersDAO();
    }

    public boolean addUser(User user) {
        return new UserHibernateDAO(sessionFactory.openSession()).addUserDAO(user);
    }

    public UserJdbcDAO getUserDAO() {
        return new UserJdbcDAO(ConnectionJDBC.getInstance().getConnection());
    }

    public boolean deleteUser(Long id) {
        return new UserHibernateDAO(sessionFactory.openSession()).deleteUserDAO(id);
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