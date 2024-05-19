package assignments.assignment4.components.form;

import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import assignments.assignment4.page.AdminMenu;
import assignments.assignment4.page.CustomerMenu;

public class LoginForm {
    private Stage stage;
    private MainApp mainApp;
    private TextField nameInput;
    private TextField phoneInput;

    public LoginForm(Stage stage, MainApp mainApp) {
        this.stage = stage;
        this.mainApp = mainApp; // Store MainApp instance
    }

    private Scene createLoginForm() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        Label nameLabel = new Label("Name:");
        grid.add(nameLabel, 0, 0);
        nameInput = new TextField();
        grid.add(nameInput, 1, 0);

        Label phoneLabel = new Label("Phone Number:");
        grid.add(phoneLabel, 0, 1);
        phoneInput = new TextField();
        grid.add(phoneInput, 1, 1);

        Button loginButton = new Button("Login");
        grid.add(loginButton, 1, 2);

        loginButton.setOnAction(e -> handleLogin());

        return new Scene(grid, 400, 600);
    }

    private void handleLogin() {
        String name = nameInput.getText();
        String phone = phoneInput.getText();
        User user = DepeFood.handleLogin(name, phone);

        if (user == null) {
            showAlert("Login Failed", "Invalid credentials", "Please check your name and phone number and try again.");
        } else {
            mainApp.setUser(user);
            mainApp.setScene(user.getRole().equals("Admin") ? new AdminMenu(stage, mainApp, user).getScene() : new CustomerMenu(stage, mainApp, user).getScene());
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public Scene getScene(){
        return this.createLoginForm();
    }
}