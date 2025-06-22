import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage stage) {
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        ChoiceBox<String> roleBox = new ChoiceBox<>();
        roleBox.getItems().addAll("Admin", "SeniorAdmin", "Student", "Teacher");

        Button loginBtn = new Button("Login");

        loginBtn.setOnAction(e -> {
            String user = usernameField.getText();
            String pass = passwordField.getText();
            String role = roleBox.getValue();

            if (role == null) {
                showAlert("Please select a role.");
                return;
            }

            if (UserManager.validate(user, pass, role)) {
                if (role.equals("Admin") || role.equals("SeniorAdmin")) {
                    new ManageUsersView().show();
                } else {
                    showAlert("Welcome, " + role + "!");
                }
            } else {
                showAlert("Invalid login or banned user.");
            }
        });

        VBox layout = new VBox(10, new Label("Username:"), usernameField,
                new Label("Password:"), passwordField,
                new Label("Role:"), roleBox,
                loginBtn);
        layout.setPrefSize(300, 250);

        stage.setScene(new Scene(layout));
        stage.setTitle("School Management Login");
        stage.show();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
