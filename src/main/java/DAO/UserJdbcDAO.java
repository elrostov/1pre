package DAO;

import model.User;
import util.DBHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserJdbcDAO implements UserDAO {

    private Connection connection;

    public UserJdbcDAO() {
        this.connection = DBHelper.getInstance().getConnection();
    }

    @Override
    public List<User> getAllUsersDAO () { //Где открывать Connection? В конструкторе класса или в каждом методе?
        try (PreparedStatement preparedStatement = //Закроется ли автоматически в таком случае и connection и
                     //preparedStatement, или только preparedStatement? - Нет
                     connection.prepareStatement("select * from pre1.users")) {
            List<User> userList = new ArrayList<>();
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getResultSet(); //A ResultSet object is automatically closed when the
            //Statement object that generated it is closed, re-executed, or used to retrieve the next result
            //from a sequence of multiple results. - Да
            while(rs.next()) {
                userList.add(new User(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)));
            }
            return userList;
        } catch (SQLException e) { //Где выбрасывать и обрабатывать исключения? SQL - обрабатывать здесь
            e.printStackTrace();
            return new ArrayList<>();//Правильно ли возвращать null? - Лучше пустой лист или Optional.
        }
    }

    @Override
    public boolean addUserDAO (User user) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("insert into users (name, password) values(?,?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            int r = preparedStatement.executeUpdate();
            return r > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUserDAO(User user) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("delete from users where id=?")) {
            preparedStatement.setLong(1, user.getId());
            int r = preparedStatement.executeUpdate();
            return r > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUserDAO(User user) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("update users set name = ?, password = ? where id=?")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());
            int r = preparedStatement.executeUpdate();
            return r > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<User> getUserIfExistsDAO(User user) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM users WHERE name = ? and password = ?")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            User userFromDB = null;
            while(rs.next()) {
                userFromDB = new User(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }
            return Optional.ofNullable(userFromDB);
            } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
