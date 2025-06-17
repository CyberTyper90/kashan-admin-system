import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentDashboard {
    private Stage stage;
    private SMS main;
    private Student student;

    public StudentDashboard(Stage stage, SMS main, Student student) {
        this.stage = stage;
        this.main = main;
        this.student = student;
    }

    public void show() {
        Label titleLabel = new Label("Student Dashboard");
        titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        TextArea studentInfoArea = new TextArea(student.toString());
        studentInfoArea.setEditable(false);
        studentInfoArea.setPrefHeight(300);

        Button editBtn = new Button("Edit My Information");
        editBtn.setOnAction(e -> {
            EditStudentForm editForm = new EditStudentForm(new Stage(), student, false);
            editForm.show();

            // Use getter to access stage and listen for close event to refresh info
            editForm.getStage().setOnHidden(ev -> {
                studentInfoArea.setText(student.toString());
            });
        });

        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> main.showLoginScreen());

        VBox layout = new VBox(20);
        layout.setPadding(new javafx.geometry.Insets(20));
        layout.getChildren().addAll(titleLabel, studentInfoArea, editBtn, logoutBtn);

        Scene scene = new Scene(layout, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Student Dashboard");
        stage.show();
    }
}
