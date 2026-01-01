package repositories;

import models.Student;
import java.util.List;

public interface IStudentRepository {
    Student findById(String student_id);
    List<Student> findAll();
    boolean save(Student student);
    boolean delete(String student_id);
    boolean update(Student student);
}
