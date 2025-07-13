import java.util.ArrayList;

public class UserManager {
    public static ArrayList<Student> students = new ArrayList<>();

    public static boolean validateAdmin(String username, String password) {
        return "#".equals(username) && "#".equals(password);
    }

    public static Student validateStudent(String email, String password) {
        for (Student student : students) {
            if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }

    public static boolean removeStudent(String studentID) {
        return students.removeIf(student -> student.getStudentID().equals(studentID));
    }

    public static void updateStudent(String studentID, Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentID().equals(studentID)) {
                students.set(i, updatedStudent);
                break;
            }
        }
    }
}