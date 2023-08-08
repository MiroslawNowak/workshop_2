package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.sql.Connection;
import java.sql.SQLException;

public class TaskManager {
    public static void main(String[] args) {
        try {
        User user = new User("mnowak123", "test4@wp.pl", "blablabla");
        User user2 = new User("mnowak123gsrgsg", "test5@wp.pl", "blablabla");
        UserDao userCreation = new UserDao();
        UserDao userRead = new UserDao();
        UserDao userRead2 = new UserDao();

            User user5 = userRead.read(5);
            System.out.println(user5.getId());
            System.out.println(user5.getEmail());

            User userNotExists = userRead2.read(1);
            System.out.println(userNotExists.getUserName());

        userRead.delete(3);

        Connection conn = DbUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
