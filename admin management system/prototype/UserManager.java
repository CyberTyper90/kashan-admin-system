import java.util.ArrayList;

public class UserManager {
    public static ArrayList<User> users = new ArrayList<>();

    static {
        users.add(new User("Admin User", "admin", "admin123", "Admin"));
        users.add(new User("Senior Admin", "senior", "senior123", "SeniorAdmin"));
        users.add(new User("Teacher User", "teacher", "teacher123", "Teacher"));
        users.add(new User("Student User", "student", "student123", "Student"));
    }

    public static boolean validate(String user, String pass, String role) {
        for (User u : users) {
            if (u.getUsername().equals(user) && u.getPassword().equals(pass) && u.getRole().equals(role) && !u.isBanned()) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<User> getAllUsers() {
        return users;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void removeUser(User user) {
        users.remove(user);
    }
}
