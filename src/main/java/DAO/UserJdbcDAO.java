package DAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO {

    private Connection connection;
    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }


    public List<User> getAllUsersDAO() {
        try {
            List<User> userList = new ArrayList<>();
            Statement stmt = connection.createStatement();
            stmt.execute("select * from pre1.users");
            ResultSet rs = stmt.getResultSet();
            while(rs.next()) {
                userList.add(new User(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
            stmt.close();
            connection.close();
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.addSuppressed(e);
                ex.printStackTrace();
            }
            return null;
        }
    }


    public boolean addUserDAO (User user) {
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement
                    ("insert into users (name, password) values(?,?)")) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getPassword());
                int r = preparedStatement.executeUpdate();
                connection.commit();
                connection.close();
                return r > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.addSuppressed(e);
                ex.printStackTrace();
            }
            return false;
        }
    }


    public boolean deleteUserDAO (Long id) {
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement
                    ("delete from users where id=?")) {
                preparedStatement.setLong(1, id);
                int r = preparedStatement.executeUpdate();
                connection.commit();
                connection.close();
                return r > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.addSuppressed(e);
                ex.printStackTrace();
            }
            return false;
        }
    }


    public boolean updateUserDAO(User user) {
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement
                    ("update users set name = ?, password = ? where id=?")) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setLong(3, user.getId());
                int r = preparedStatement.executeUpdate();
                connection.commit();
                connection.close();
                return r > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.addSuppressed(e);
                ex.printStackTrace();
            }
            return false;
        }
    }


    public boolean updateUserPasswordDAO(User user) {
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement
                    ("update users set password = ? where id=?")) {  // Почему-то при изменении пароля у юзера
                // обнуляется имя. Вариант со Statement такой же. Драйвера менял на 5.1.47.
                preparedStatement.setString(1, user.getPassword());
                preparedStatement.setLong(2, user.getId());
                int r = preparedStatement.executeUpdate();
                connection.commit();
                connection.close();
                return r > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.addSuppressed(e);
                ex.printStackTrace();
            }
            return false;
        }
    }


    public boolean updateUserNameDAO(User user) {
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement
                    ("update users set name = ? where id=?")) { // Почему-то при изменении имени у юзера обнуляется
                // пароль. Вариант со Statement такой же. Драйвера менял на 5.1.47.
                preparedStatement.setString(1, user.getName());
                preparedStatement.setLong(2, user.getId());
                int r = preparedStatement.executeUpdate();
                connection.commit();
                connection.close();
                return r > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.addSuppressed(e);
                ex.printStackTrace();
            }
            return false;
        }
    }
}
