package services;

import models.Student;

import java.util.ArrayList;

public class StudentManager {
    private ArrayList<Student> students;
    public StudentManager() {
        this.students = new ArrayList<>();
    }
    public void addStudent(Student student) {
        this.students.add(student);
        System.out.println("Đã thêm sinh viên...");
    }
    public void showAll() {
        System.out.println("Danh sách sinh viên:");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
