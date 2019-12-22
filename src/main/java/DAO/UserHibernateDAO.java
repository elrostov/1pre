package DAO;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.DBHelper;

import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private Session session; //Где открывать сессии? В конструкторе или в методах?

    public UserHibernateDAO() {
        this.session = DBHelper.getSessionFactory().openSession();
    }

    @Override
    public List<User> getAllUsersDAO() {
        try {
            List<User> userList = (List<User>) session.createQuery("FROM User").list();
            session.close();
            return userList;
        } catch (HibernateException e) { //Где отлавливать и обрабатывать исключения?
            e.printStackTrace();
            session.close();
            return null;
        }
    }

    @Override
    public boolean addUserDAO(User user) {
        Transaction t = session.beginTransaction(); // Нужны ли здесь транзакции?
        try {
            long num = (long) session.save(user);//Есть ли метод, который предварительно проверяет наличие в базе и
            // принимает решение, записывать или нет?
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
    public boolean deleteUserDAO(User user) {
        Transaction t = session.beginTransaction();
        try {
            session.delete(user);
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
        Transaction t = session.beginTransaction();
        try {
            session.update(user);
            t.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            t.rollback();
            session.close();
            return false;
        }
    }
}
