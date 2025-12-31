package services;

import models.Student;

import java.io.*;
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
        double mark = Double.parseDouble(scanner.nextLine());
//        scanner.close(); đóng lại là đi luôn bởi vì nó đóng luôn system.in thì những lần đọc sau sẽ k đọc được nữa
        return new Student(id,name,mark);
    }
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("students.txt"))) {
            for (Student s : students) {
                bw.write(s.getId() + "," + s.getName() + "," + s.getMark());
                bw.newLine();
            }
            System.out.println("-->Đã lưu dữ liệu sinh viên vào file students.txt");
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu file: " + e);
        }
    }
    public void loadFromFile() {
        File file = new File("students.txt");
        if (!file.exists()) {
            System.out.println("-->Không tìm được file students.txt...");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0];
                String name = data[1];
                double mark = Double.parseDouble(data[2]);
                this.students.add(new Student(id,name,mark));
            }
            System.out.println("-->Đọc dữ liệu và thêm dữ liệu thành công");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e);
        }
    }
}
