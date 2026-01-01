import models.Student;
import services.StudentManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static Student inputStudent(Scanner scanner) {
        System.out.print("Nhập id sinh viên: ");
        String id =  scanner.nextLine();
        System.out.print("Nhập tên sinh viên: ");
        String name =  scanner.nextLine();
        System.out.print("Nhập điểm sinh viên: ");
        double mark = Double.parseDouble(scanner.nextLine());
        return new Student(id,name,mark);
    }
    public static void main(String[] args) {
        StudentManager sm = new StudentManager();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("===MENU QUẢN LÝ SINH VIÊN===");
            System.out.println("1. Thêm sinh viên mới");
            System.out.println("2. Xem danh sách sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Sửa thông tin sinh viên");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1: {
                    System.out.println("Nhập thông tin sinh viên");
                    Student newStudent = inputStudent(sc);
                    boolean result = sm.addStudent(newStudent);
                    if (result) System.out.println("Thêm sinh viên thành công");
                    else System.out.println("Không thể thêm sinh viên");
                    break;
                }
                case 2: {
                    List<Student> students = sm.showAll();
                    System.out.println("Danh sách sinh viên: ");
                    if (students != null && !students.isEmpty()) {
                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }
                    else {
                        System.out.println("Hiện tại chưa có sinh viên nào");
                    }
                    break;
                }
                case 3: {
                    System.out.print("Nhập id sinh viên: ");
                    String student_id = sc.nextLine();
                    boolean result = sm.deleteStudent(student_id);
                    if (result) System.out.println("Xóa sinh viên thành công");
                    else System.out.println("Không thể xóa sinh viên");
                    break;
                }
                case 4: {
                    System.out.print("Nhập id sinh viên: ");
                    String student_id = sc.nextLine();
                    System.out.print("Nhập tên mới (Nhấn Enter để bỏ qua): ");
                    String name = sc.nextLine();
                    System.out.print("Nhập điểm mới (Nhấn -1 để bỏ qua): ");
                    String stringMark = sc.nextLine();
                    double markInp = (stringMark.isBlank()) ? -1 : Double.parseDouble(stringMark);
                    Double mark = (markInp == -1) ? null : markInp;
                    boolean result = sm.updateStudent(student_id, name, mark);
                    if (result) {
                        System.out.println("Cập nhật sinh viên thành công");
                    } else {
                        System.out.println("Không thể cập nhật sinh viên");
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
        sc.close();
    }
}