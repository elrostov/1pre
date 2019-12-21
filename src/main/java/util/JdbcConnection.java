package util;

import DAO.UserJdbcDAO;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

    private static JdbcConnection instance;

    private JdbcConnection(){}

    public static JdbcConnection getInstance() {
        if (instance == null) {
            instance = new JdbcConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.append("jdbc:mysql://")
                    .append("localhost:")
                    .append("3306/")
                    .append("pre1?")
                    .append("user=root&")
                    .append("password=7727&")
                    .append("serverTimezone=UTC");
            System.out.println("Connected to DB/ URL: " + url + "\n");
            return DriverManager.getConnection(url.toString());
        } catch (SQLException | InstantiationException |
                IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }


}
