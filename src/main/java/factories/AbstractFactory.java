package factories;

import DAO.UserDAO;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AbstractFactory {

    private static final String PROPERTIES_FILE = "config.properties";

    public static UserDAO getDAO() {
        String property = getProperties();
        if ("jdbc".equals(property.toLowerCase())) {
            return UserJDBCFactory.getUserJdbcDAO();
        } else if ("hibernate".equals(property.toLowerCase())) {
            return UserHibernateFactory.getUserHibernateDAO();
        } else {
            throw new IllegalArgumentException("Wrong configuration property:" + property);
        }
    }

    private static String getProperties() {
        try (InputStream input = UserJDBCFactory.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("daoType", "hibernate");
        } catch (IOException ex) {
            ex.printStackTrace();
            return "hibernate";
        }
    }
}
