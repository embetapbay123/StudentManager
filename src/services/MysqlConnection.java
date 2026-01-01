package services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/student_management";
        String user = "root";
        String password = "abc123";
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối DATABASE: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
