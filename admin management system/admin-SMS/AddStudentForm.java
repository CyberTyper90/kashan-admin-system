import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddStudentForm {
    private Stage stage;
    private AdminDashboard adminDashboard;

    public AddStudentForm(Stage stage, AdminDashboard adminDashboard) {
        this.stage = stage;
        this.adminDashboard = adminDashboard;
    }

    public void show() {
        Label titleLabel = new Label("Add New User");
        titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        TextField fullNameField = new TextField();
        TextField dobField = new TextField();
        TextField genderField = new TextField();
        TextField studentIDField = new TextField();
        TextField phoneField = new TextField();
        TextField emailField = new TextField();
        PasswordField passwordField = new PasswordField();
        TextField parentNameField = new TextField();
        TextField parentContactField = new TextField();

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

        Button addButton = new Button("Add Student");
        addButton.setOnAction(e -> {
            if (fullNameField.getText().isEmpty() || dobField.getText().isEmpty() || 
                genderField.getText().isEmpty() || studentIDField.getText().isEmpty() || 
                phoneField.getText().isEmpty() || emailField.getText().isEmpty() || 
                passwordField.getText().isEmpty() || parentNameField.getText().isEmpty() || 
                parentContactField.getText().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "All fields are required!");
            } else {
                Student newStudent = new Student(
                    fullNameField.getText(),
                    dobField.getText(),
                    genderField.getText(),
                    studentIDField.getText(),
                    phoneField.getText(),
                    emailField.getText(),
                    passwordField.getText(),
                    parentNameField.getText(),
                    parentContactField.getText()
                );
                UserManager.students.add(newStudent);
                showAlert(Alert.AlertType.INFORMATION, "User added successfully!");
                clearFields(fullNameField, dobField, genderField, studentIDField, 
                           phoneField, emailField, passwordField, parentNameField, parentContactField);
            }
        });

        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> adminDashboard.show());

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleLabel, grid, addButton, backButton);

        Scene scene = new Scene(layout, 500, 600);
        stage.setScene(scene);
        stage.setTitle("Add Student");
        stage.show();
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }
}