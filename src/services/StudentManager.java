package services;

import models.Student;
import repositories.IStudentRepository;
import repositories.StudentRepositoryImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private IStudentRepository studentRepo;
//    private ArrayList<Student> students;
    public StudentManager() {
        this.studentRepo = new StudentRepositoryImpl();
    }
    public boolean addStudent(Student student) {
        if (student.getMark() < 0 || student.getMark() > 10) {
            return false;
        }
        return studentRepo.save(student);
    }
    // Xài boolean chứ không nên in ra thông báo
    public boolean deleteStudent(String student_id) {
        return studentRepo.delete(student_id);
    }
    // Truyền vào scanner là sai, services k được truyền
    public boolean updateStudent(String student_id, String name, Double mark) {
        Student s = studentRepo.findById(student_id);
        if (s == null) {
            return false;
        }
        // Dùng isEmpty thì " " sẽ có
        if (name != null && !name.isBlank()) s.setName(name);
        if (mark != null) {
            if (mark < 0 || mark > 10) return false;
            s.setMark(mark);
        }
        return studentRepo.update(s);
    }
    public List<Student> showAll() {
        return studentRepo.findAll();
    }
}
