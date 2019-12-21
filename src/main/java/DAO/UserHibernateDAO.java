package DAO;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private Session session;

    public UserHibernateDAO(Session openSession) {
        this.session = openSession;
    }

    @Override
    public List<User> getAllUsersDAO() {
        try {
            List<User> userList = (List<User>) session.createQuery("FROM User").list();
            session.close();
            return userList;
        } catch (HibernateException e) {
            e.printStackTrace();
            session.close();
            return null;
        }
    }

    @Override
    public boolean addUserDAO(User user) {
        Transaction t = session.beginTransaction();
        try {
            long num = (long) session.save(user);
            t.commit();
            session.close();
            System.out.println(num);
            return num > 0;
        } catch (HibernateException e) {
            t.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean deleteUserDAO(Long id) {
        Transaction t = session.beginTransaction();
        try {
            session.delete(new User(id));
            t.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            t.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean updateUserDAO(User user) {
        return false;
    }

    @Override
    public boolean updateUserPasswordDAO(User user) {
        return false;
    }

    @Override
    public boolean updateUserNameDAO(User user) {
        return false;
    }
}
