package DAO;

import model.User;
import util.DBHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    public UserJdbcDAO() {}

    @Override
    public List<User> getAllUsersDAO() { //Где открывать Connection? В конструкторе класса или в каждом методе?
        try (PreparedStatement preparedStatement = //Закроется ли автоматически в таком случае и connection и
                     // preparedStatement, или только preparedStatement?
                     DBHelper.getInstance().getConnection().prepareStatement("select * from pre1.users")) {
            List<User> userList = new ArrayList<>();
            int r = preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getResultSet(); //A ResultSet object is automatically closed when the
            // Statement object that generated it is closed, re-executed, or used to retrieve the next result
            // from a sequence of multiple results.
            while(rs.next()) {
                userList.add(new User(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
            return userList;
        } catch (SQLException e) { // Где выбрасывать и обрабатывать исключения?
            e.printStackTrace();
            return null; //Правильно ли возвращать null?
        }
    }

    @Override
    public boolean addUserDAO (User user) {
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement preparedStatement = //Нужно ли тут указывать PreparedStatement для автоматического
                     // закрытия, или оставить только Connection?
                        con.prepareStatement("insert into users (name, password) values(?,?)")) {
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
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     con.prepareStatement("delete from users where id=?")) {
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
        try (Connection con = DBHelper.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     con.prepareStatement("update users set name = ?, password = ? where id=?")) {
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
}
