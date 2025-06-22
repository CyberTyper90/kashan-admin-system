import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SMS extends Application {
    
    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        showLoginScreen();
    }

    public void showLoginScreen() {
        Label titleLabel = new Label("Student Management System");
        titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        Label typeLabel = new Label("Login Type:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> {
                    String username = usernameField.getText();
                    String password = passwordField.getText();

                    if (UserManager.validateAdmin(username, password)) {
                        AdminDashboard adminDashboard = new AdminDashboard(stage, this);
                        adminDashboard.show();
                    } else {
                        Student student = UserManager.validateStudent(username, password);
                        if (student != null) {
                            StudentDashboard studentDashboard = new StudentDashboard(stage, this, student);
                            studentDashboard.show();
                        } else {
                            showAlert("Invalid credentials. Please try again.");
                        }
                    }
            });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(passwordField, 1, 1);

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleLabel, grid, loginBtn);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    private void showAlert(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}