package pl.coderslab;

import java.sql.Connection;
import java.sql.SQLException;

public class TaskManager {
    public static void main(String[] args) {
        try {
            Connection conn = DbUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
