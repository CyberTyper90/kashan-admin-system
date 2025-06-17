import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Optional;

public class ViewStudentsForm {
    private Stage stage;
    private AdminDashboard adminDashboard;

    public ViewStudentsForm(Stage stage, AdminDashboard adminDashboard) {
        this.stage = stage;
        this.adminDashboard = adminDashboard;
    }

    public void show() {
        Label titleLabel = new Label("All Students");
        titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        ListView<Student> studentList = new ListView<>();
        studentList.getItems().addAll(UserManager.students);
        studentList.setCellFactory(lv -> new ListCell<Student>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                setText(empty ? null : student.getFullName() + " (" + student.getStudentID() + ")");
            }
        });

        Button editBtn = new Button("Edit Selected");
        editBtn.setOnAction(e -> {
            Student selectedStudent = studentList.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                EditStudentForm editForm = new EditStudentForm(stage, selectedStudent, true);
                editForm.show();
            } else {
                showAlert("Please select a student to edit");
            }
        });

        Button deleteBtn = new Button("Delete Selected");
        deleteBtn.setOnAction(e -> {
            Student selectedStudent = studentList.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Confirm Delete");
                confirm.setHeaderText("Delete Student");
                confirm.setContentText("Are you sure you want to delete " + selectedStudent.getFullName() + "?");

                Optional<ButtonType> result = confirm.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    UserManager.removeStudent(selectedStudent.getStudentID());
                    studentList.getItems().setAll(UserManager.students); // Refresh the list
                }
            } else {
                showAlert("Please select a student to delete");
            }
        });

        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> adminDashboard.show());

        VBox layout = new VBox(20);
        layout.setPadding(new javafx.geometry.Insets(20));
        layout.getChildren().addAll(titleLabel, studentList, editBtn, deleteBtn, backButton);

        Scene scene = new Scene(layout, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Manage Students");
        stage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.showAndWait();
    }
}