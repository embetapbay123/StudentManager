import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/student_management";
        String user = "root";
        String password = "abc123";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Kết nối DATABASE thành công!!!");
            System.out.println(conn.getMetaData());
        } catch (SQLException e) {
//            nên dùng err vì sao? vì in ra chữ màu đỏ, dễ nhìn
            System.err.println("Lỗi kết nối DATABASE: " + e.getMessage());
            e.printStackTrace(); // nên lưu vào file log
        }
    }
}
