import models.Student;
import services.StudentManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager sm = new StudentManager();
        Scanner sc = new Scanner(System.in);
        sm.loadFromFile();
        int choice;
        do {
            System.out.println("===MENU QUẢN LÝ SINH VIÊN===");
            System.out.println("1. Thêm sinh viên mới");
            System.out.println("2. Xem danh sách sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Sửa thông tin sinh viên");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");
//            choice = sc.nextInt(); // có 1 lỗi ở đây, buffer đọc luôn dấu \n nhưng thg nextInt k lấy nên bị nhét vào mấy thg sau
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1: {
                    System.out.println("Nhập thông tin sinh viên");
                    Student newStudent = sm.inputStudent(sc);
                    sm.addStudent(newStudent);
                    break;
                }
                case 2: {
                    sm.showAll();
                    break;
                }
                case 3: {
                    System.out.print("Nhập id sinh viên: ");
                    String student_id = sc.nextLine();
                    if (sm.deleteStudent(student_id)) {
                        System.out.println("Xóa sinh viên thành công!!!");
                    } else {
                        System.out.println("Không tìm thấy sinh viên có id " + student_id);
                    }
                    break;
                }
                case 4: {
                    System.out.print("Nhập id sinh viên: ");
                    String student_id = sc.nextLine();
                    if (sm.updateStudent(student_id, sc)) {
                        System.out.println("Cập nhật thông tin sinh viên thành công!!!");
                    }
                    else {
                        System.out.println("Không tìm thấy sinh viên có id " + student_id);
                    }
                    break;
                }
                case 0:
                    System.out.println("Xin chào và hẹn gặp lại!!!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        } while (choice != 0);
        sm.saveToFile();
        sc.close();
    }
}