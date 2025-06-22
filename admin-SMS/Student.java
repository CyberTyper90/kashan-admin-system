public class Student {
    private String fullName;
    private String dob;
    private String gender;
    private String studentID;
    private String phone;
    private String email;
    private String password;
    private String parentName;
    private String parentContact;

    public Student(String fullName, String dob, String gender, String studentID, 
                  String phone, String email, String password, 
                  String parentName, String parentContact) {
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.studentID = studentID;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.parentName = parentName;
        this.parentContact = parentContact;
    }

    // Getters for all fields
    public String getFullName() { return fullName; }
    public String getDob() { return dob; }
    public String getGender() { return gender; }
    public String getStudentID() { return studentID; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getParentName() { return parentName; }
    public String getParentContact() { return parentContact; }

    @Override
    public String toString() {
        return "Name: " + fullName + "\n" +
               "Student ID: " + studentID + "\n" +
               "Date of Birth: " + dob + "\n" +
               "Gender: " + gender + "\n" +
               "Phone: " + phone + "\n" +
               "Email: " + email + "\n" +
               "Parent Name: " + parentName + "\n" +
               "Parent Contact: " + parentContact + "\n";
    }
}