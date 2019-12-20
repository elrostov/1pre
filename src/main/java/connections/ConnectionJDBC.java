package connections;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

    private static ConnectionJDBC instance;

    private ConnectionJDBC(){}

    public static ConnectionJDBC getInstance() {
        if (instance == null) {
            instance = new ConnectionJDBC();
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
