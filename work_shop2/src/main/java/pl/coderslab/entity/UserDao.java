package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DbUtil;

import java.sql.*;
import java.util.Arrays;

public class UserDao {

    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_QUERY = "SELECT * FROM users WHERE users.id = ?";

    private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String PRINT_ALL_QUERY = "SELECT * FROM tableName";

    public User[] findAll(String tableName) {
        User[] userArr;
        userArr = new User[0];
        try {

            Connection conn = DbUtil.getConnection();
            PreparedStatement statment = conn.prepareStatement(PRINT_ALL_QUERY.replace("tableName", tableName));
            ResultSet resutleSet = statment.executeQuery();
            ResultSet resultSet = statment.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setUserName(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                 userArr = addToArray(user, userArr);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return userArr;
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }

    public void delete (int userId) {
        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public User read(int userId) {
        User user = new User();
        try {
            Connection conn = DbUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(READ_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setUserName(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
        return user;
    }

    public User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());

    }
}

