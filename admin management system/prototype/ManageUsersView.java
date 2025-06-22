import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageUsersView {
    private TableView<User> table;
    private ObservableList<User> userList;

    public void show() {
        Stage stage = new Stage();
        userList = FXCollections.observableArrayList(UserManager.getAllUsers());

        table = new TableView<>(userList);
        TableColumn<User, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        TableColumn<User, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getUsername()));

        TableColumn<User, String> roleCol = new TableColumn<>("Role");
        roleCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRole()));

        TableColumn<User, String> bannedCol = new TableColumn<>("Banned");
        bannedCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
            data.getValue().isBanned() ? "Yes" : "No"));

        table.getColumns().addAll(nameCol, usernameCol, roleCol, bannedCol);

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        ChoiceBox<String> roleBox = new ChoiceBox<>();
        roleBox.getItems().addAll("Student", "Teacher", "Staff");

        Button addUser = new Button("Add");
        Button removeUser = new Button("Remove");
        Button banUser = new Button("Ban");
        Button unbanUser = new Button("Unban");

        addUser.setOnAction(e -> {
            String name = nameField.getText();
            String uname = usernameField.getText();
            String pass = passwordField.getText();
            String role = roleBox.getValue();
            if (!name.isEmpty() && !uname.isEmpty() && !pass.isEmpty() && role != null) {
                User user = new User(name, uname, pass, role);
                UserManager.addUser(user);
                userList.setAll(UserManager.getAllUsers());
                nameField.clear(); usernameField.clear(); passwordField.clear(); roleBox.setValue(null);
            }
        });

        removeUser.setOnAction(e -> {
            User selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                UserManager.removeUser(selected);
                userList.setAll(UserManager.getAllUsers());
            }
        });

        banUser.setOnAction(e -> {
            User selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setBanned(true);
                userList.setAll(UserManager.getAllUsers());
            }
        });

        unbanUser.setOnAction(e -> {
            User selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setBanned(false);
                userList.setAll(UserManager.getAllUsers());
            }
        });

        VBox layout = new VBox(10, table, nameField, usernameField, passwordField, roleBox, addUser, removeUser, banUser, unbanUser);
        layout.setPrefSize(600, 500);

        stage.setScene(new Scene(layout));
        stage.setTitle("Manage Users");
        stage.show();
    }
}
