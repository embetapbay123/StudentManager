import models.Student;
import services.StudentManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager sm = new StudentManager();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("===MENU QUẢN LÝ SINH VIÊN===");
            System.out.println("1. Thêm sinh viên mới");
            System.out.println("2. Xem danh sách sinh viên");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Nhập thông tin sinh viên");
                    Student newStudent = sm.inputStudent(sc);
                    sm.addStudent(newStudent);
                    break;
                case 2:
                    sm.showAll();
                    break;
                case 0:
                    System.out.println("Xin chào và hẹn gặp lại!!!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        } while (choice != 0);
        sc.close();
    }
}