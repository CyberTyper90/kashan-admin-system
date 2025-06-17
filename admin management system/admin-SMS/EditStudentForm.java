import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditStudentForm {
    public Stage getStage() {
    return stage;
}

    private Stage stage;
    private Student student;
    private boolean isAdminEdit;

    public EditStudentForm(Stage stage, Student student, boolean isAdminEdit) {
        this.stage = stage;
        this.student = student;
        this.isAdminEdit = isAdminEdit;
    }

    public void show() {
        Label titleLabel = new Label("Edit Student");
        titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        TextField fullNameField = new TextField(student.getFullName());
        TextField dobField = new TextField(student.getDob());
        TextField genderField = new TextField(student.getGender());
        TextField studentIDField = new TextField(student.getStudentID());
        studentIDField.setEditable(false); // ID shouldn't be changed
        TextField phoneField = new TextField(student.getPhone());
        TextField emailField = new TextField(student.getEmail());
        PasswordField passwordField = new PasswordField();
        passwordField.setText(student.getPassword());
        TextField parentNameField = new TextField(student.getParentName());
        TextField parentContactField = new TextField(student.getParentContact());

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        grid.add(new Label("Full Name:"), 0, 0);
        grid.add(fullNameField, 1, 0);
        grid.add(new Label("Date of Birth:"), 0, 1);
        grid.add(dobField, 1, 1);
        grid.add(new Label("Gender:"), 0, 2);
        grid.add(genderField, 1, 2);
        grid.add(new Label("Student ID:"), 0, 3);
        grid.add(studentIDField, 1, 3);
        grid.add(new Label("Phone:"), 0, 4);
        grid.add(phoneField, 1, 4);
        grid.add(new Label("Email:"), 0, 5);
        grid.add(emailField, 1, 5);
        grid.add(new Label("Password:"), 0, 6);
        grid.add(passwordField, 1, 6);
        grid.add(new Label("Parent Name:"), 0, 7);
        grid.add(parentNameField, 1, 7);
        grid.add(new Label("Parent Contact:"), 0, 8);
        grid.add(parentContactField, 1, 8);

        Button updateButton = new Button("Save Changes");
        updateButton.setOnAction(e -> {
            Student updatedStudent = new Student(
                fullNameField.getText(),
                dobField.getText(),
                genderField.getText(),
                student.getStudentID(), // Keep original ID
                phoneField.getText(),
                emailField.getText(),
                passwordField.getText(),
                parentNameField.getText(),
                parentContactField.getText()
            );
            UserManager.updateStudent(student.getStudentID(), updatedStudent);
            
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setContentText("Student information updated successfully!");
            success.showAndWait();
            
            stage.close(); // Close the edit form
        });

        Button backButton = new Button("Cancel");
        backButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleLabel, grid, updateButton, backButton);

        Scene scene = new Scene(layout, 500, 600);
        stage.setScene(scene);
        stage.setTitle("Edit Student");
        stage.show();
        
    }
}