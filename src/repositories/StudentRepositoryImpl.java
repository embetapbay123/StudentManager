package repositories;

import models.Student;
import services.MysqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements IStudentRepository{

    @Override
    public Student findById(String student_id) {
        String query = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = MysqlConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query))
        {
            statement.setString(1, student_id);
            try (ResultSet rs = statement.executeQuery()) { // try-with-resources
                if (rs.next()) {
                    return new Student(rs.getString("id"), rs.getString("name"), rs.getDouble("mark"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Connection conn = MysqlConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             )
        {
            try (ResultSet rs = statement.executeQuery()) {
                while(rs.next()) {
                    students.add(new Student(rs.getString("id"), rs.getString("name"), rs.getDouble("mark")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public boolean save(Student student) {
        String query = "INSERT INTO students (id, name, mark) VALUES (?, ?, ?)";
        try (Connection conn = MysqlConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query))
        {
            statement.setString(1, student.getId());
            statement.setString(2, student.getName());
            statement.setDouble(3, student.getMark());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String student_id) {
        String query = "DELETE FROM students WHERE id = ?";
        try (Connection conn = MysqlConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query))
        {
            statement.setString(1, student_id);
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Student student) {
        String query = "UPDATE students SET name = ?, mark = ? WHERE id = ?";
        try (Connection conn = MysqlConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);)
        {
            statement.setString(1, student.getName());
            statement.setDouble(2, student.getMark());
            statement.setString(3, student.getId());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
