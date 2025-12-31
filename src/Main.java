import models.Student;
import services.StudentManager;
public class Main {
    public static void main(String[] args) {
        Student sv1 = new Student("SV001", "Nguyen Van A", 8.5);
        Student sv2 = new Student("SV002", "Nguyen Van B", 6.5);
        Student sv3 = new Student("SV003", "Nguyen Van C", 9.5);

        StudentManager sm = new StudentManager();
        sm.addStudent(sv1);
        sm.addStudent(sv2);
        sm.addStudent(sv3);
        sm.showAll();
    }
}