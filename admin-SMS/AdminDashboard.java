import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminDashboard {
    private Stage stage;
    private SMS main;

    public AdminDashboard(Stage stage, SMS main) {
        this.stage = stage;
        this.main = main;
    }

    public void show() {
        Label titleLabel = new Label("Admin Dashboard");
        titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        Button addStudentBtn = new Button("Add Student");
        addStudentBtn.setOnAction(e -> {
            AddStudentForm addStudentForm = new AddStudentForm(stage, this);
            addStudentForm.show();
        });

        Button viewStudentsBtn = new Button("View/Edit Students");
        viewStudentsBtn.setOnAction(e -> {
            ViewStudentsForm viewStudentsForm = new ViewStudentsForm(stage, this);
            viewStudentsForm.show();
        });

        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> main.showLoginScreen());

        VBox layout = new VBox(20);
        layout.setPadding(new javafx.geometry.Insets(20));
        layout.getChildren().addAll(titleLabel, addStudentBtn, viewStudentsBtn, logoutBtn);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Admin Dashboard");
        stage.show();
    }
}