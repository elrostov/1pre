package factories;

import DAO.UserDAO;
import DAO.UserHibernateDAO;
import DAO.UserJdbcDAO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserHibernateFactory {

    public static UserDAO getUserHibernateDAO() {
        return new UserHibernateDAO();
    }
}
