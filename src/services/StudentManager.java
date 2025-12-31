package services;

import models.Student;

import java.util.ArrayList;
import java.util.Scanner;

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
    public Student inputStudent(Scanner scanner) {
//        Scanner scanner = new Scanner(System.in); Nên biến nó thành tham số truyền vào, khởi tạo ở main
        System.out.print("Nhập id sinh viên: ");
        String id =  scanner.nextLine();
        System.out.print("Nhập tên sinh viên: ");
        String name =  scanner.nextLine();
        System.out.print("Nhập điểm sinh viên: ");
        double mark = scanner.nextDouble();
//        scanner.close(); đóng lại là đi luôn bởi vì nó đóng luôn system.in thì những lần đọc sau sẽ k đọc được nữa
        return new Student(id,name,mark);
    }
}
