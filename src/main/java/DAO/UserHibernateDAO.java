package DAO;

import model.User;
import org.hibernate.*;
import org.hibernate.exception.ConstraintViolationException;
import util.DBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserHibernateDAO implements UserDAO {

    private Session session;

    public UserHibernateDAO() {
        this.session = DBHelper.getInstance().getSessionFactory().openSession();
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
            return new ArrayList<>();
        }
    }

    @Override
    public boolean addUserDAO(User user) {
        Transaction trx = session.beginTransaction();
        try {
            long num = (long) session.save(user);
            trx.commit();
            session.close();
            return num > 0;
        } catch (HibernateException e) {
            trx.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean deleteUserDAO(User user) {
        Transaction trx = session.beginTransaction();
        try {
            session.delete(user);
            trx.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            trx.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean updateUserDAO(User user) {
        Transaction trx = session.beginTransaction();
        try {
            session.update(user);
            trx.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            trx.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public Optional<User> getUserIfExistsDAO(User user) {
        try {
            Query query = session.createQuery("FROM User where name=:name and password=:password");
            query.setParameter("name", user.getName());
            query.setParameter("password", user.getPassword());
            User userFromDB = (User) query.uniqueResult();
            session.close();
            return Optional.ofNullable(userFromDB);
        } catch (HibernateException e) {
            e.printStackTrace();
            session.close();
            return Optional.empty();
        }
    }
}
