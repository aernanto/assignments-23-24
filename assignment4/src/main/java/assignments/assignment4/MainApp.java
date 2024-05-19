package assignments.assignment4;

import java.util.HashMap;
import java.util.Map;

import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import assignments.assignment4.components.form.LoginForm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage window;
    private Map<String, Scene> allScenes = new HashMap<>();
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("DepeFood Ordering System");
        DepeFood.initUser(); // Inisialisasi user

        // Inisialisasi semua scene
        Scene loginScene = new LoginForm(window, this).getScene();

        // Map scene
        allScenes.put("Login", loginScene);

        // Set supaya scene awal ke scene login
        setScene(loginScene);
        window.show();
    }

    public void setUser(User newUser) {
    }

    // Method untuk set scene
    public void setScene(Scene scene) {
        window.setScene(scene);
    }

    // Method buat dapetin scene dari nama
    public Scene getScene(String sceneName) {
        return allScenes.get(sceneName);
    }

    public void addScene(String sceneName, Scene scene) {
        allScenes.put(sceneName, scene);
    }

    public void logout() {
        setUser(null); // Clear user
        setScene(getScene("Login")); // Switch ke login scene
    }

    public static void main(String[] args) {
        launch(args);
    }
}