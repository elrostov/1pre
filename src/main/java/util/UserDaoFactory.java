package util;

import DAO.UserDAO;
import DAO.UserHibernateDAO;
import DAO.UserJdbcDAO;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {

    private static UserDaoFactory userDaoFactory;

    private static SessionFactory sessionFactory;

    private static final String PROPERTIES_PATH = "config.properties";

    public static UserDaoFactory getInstance() {
        if (userDaoFactory == null) {
            userDaoFactory = new UserDaoFactory();
        }
        return userDaoFactory;
    }

    public UserDAO getConnection() {
        String property = getProperties();
        if ("jdbc".equals(property)) {
            return new UserJdbcDAO(DBHelper.getInstance().getConnection());
        } else if ("hibernate".equals(property)) {
            return new UserHibernateDAO(getSessionFactory().openSession());
        } else {
            throw new IllegalArgumentException("Wrong configuration property:" + property);
        }
    }

    private String getProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(PROPERTIES_PATH)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("daoType"));
            return prop.getProperty("daoType", "hibernate");
        } catch (IOException ex) {
            ex.printStackTrace();
            return "hibernate";
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = DBHelper.getInstance().getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


}
