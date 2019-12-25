package factories;

import DAO.UserDAO;
import DAO.UserJdbcDAO;

public class UserJDBCFactory {

    public static UserDAO getUserJdbcDAO() {
        return new UserJdbcDAO();
    }
}
