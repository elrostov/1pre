package DAO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {

    private static final String PROPERTIES_FILE = "config.properties";

    public static UserDAO getDAO() {
        String property = getProperties();
        if ("jdbc".equals(property)) {
            return new UserJdbcDAO(); // Возвращать класс с пустым конструкотором
        } else if ("hibernate".equals(property)) {
            return new UserHibernateDAO();
        } else {
            throw new IllegalArgumentException("Wrong configuration property:" + property);
        }
    }

    private static String getProperties() {
        try (InputStream input = UserDaoFactory.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("daoType", "hibernate");
        } catch (IOException ex) {
            ex.printStackTrace();
            return "hibernate";
        }
    }
}
