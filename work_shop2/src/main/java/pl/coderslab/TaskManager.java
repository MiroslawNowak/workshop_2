package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

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
            UserDao userDaoArr = new UserDao();
            User[]  userArr = userDaoArr.findAll("users");

            System.out.println(userArr[4].getPassword());

            User userNotExists = userRead2.read(3);

            System.out.println(userNotExists.getUserName());


        Connection conn = DbUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
