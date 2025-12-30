import models.Student;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("SV001", "Nguyễn Minh Công", 8.3);
        // Tạo thử 1 sv
        System.out.println("Thông tin sinh viên vừa tạo");
        System.out.println(student); // Không cần thiết phải .toString? chắc là tự convert qua
        // Thay đổi điểm
        student.setMark(10);
        System.out.println("Thông tin sinh viên sau khi đổi thành 10 điểm");
        System.out.println(student);
    }
}