package assignments.assignment4.page;

import java.util.stream.Collectors;

import assignments.assignment3.DepeFood;
import assignments.assignment3.Restaurant;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminMenu extends MemberMenu {
    private Stage stage;
    private Scene scene;
    private Scene addRestaurantScene;
    private Scene addMenuScene;
    private Scene viewRestaurantsScene;
    private MainApp mainApp;
    private ComboBox<String> restaurantComboBox = new ComboBox<>();
    public AdminMenu(Stage stage, MainApp mainApp, User user) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.scene = createBaseMenu();
        this.addRestaurantScene = createAddRestaurantForm();
        this.addMenuScene = createAddMenuForm();
        this.viewRestaurantsScene = createViewRestaurantsForm();
    }

    @Override
    public Scene createBaseMenu() {
        VBox menuLayout = new VBox(10);

        Button addRestaurantButton = new Button("Tambah Restoran");
        addRestaurantButton.setOnAction(e -> stage.setScene(addRestaurantScene));

        Button addMenuButton = new Button("Tambah Menu Restoran");
        addMenuButton.setOnAction(e -> stage.setScene(addMenuScene));

        Button viewRestaurantsButton = new Button("Lihat Daftar Restoran");
        viewRestaurantsButton.setOnAction(e -> stage.setScene(viewRestaurantsScene));

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> mainApp.logout());

        menuLayout.getChildren().addAll(addRestaurantButton, addMenuButton, viewRestaurantsButton, logoutButton);
        menuLayout.setAlignment(Pos.CENTER);

        return new Scene(menuLayout, 400, 600);
    }

    private Scene createAddRestaurantForm() {
        VBox layout = new VBox(10);

        Label nameLabel = new Label("Nama Restoran:");
        TextField nameInput = new TextField();

        Button submitButton = new Button("Tambah Restoran");
        submitButton.setOnAction(e -> {
            String name = nameInput.getText().trim();
            handleTambahRestoran(name);
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.setScene(scene));

        layout.getChildren().addAll(nameLabel, nameInput, submitButton, backButton);
        layout.setAlignment(Pos.CENTER);

        return new Scene(layout, 400, 600);
    }

    private Scene createAddMenuForm() {
        VBox layout = new VBox(10);

        Label restaurantLabel = new Label("Pilih Restoran:");
        restaurantComboBox.setItems(FXCollections.observableArrayList(
                DepeFood.getRestoList().stream().map(Restaurant::getNama).collect(Collectors.toList())
        ));

        Label itemNameLabel = new Label("Nama Menu:");
        TextField itemNameInput = new TextField();

        Label priceLabel = new Label("Harga Menu:");
        TextField priceInput = new TextField();

        Button submitButton = new Button("Tambah Menu");
        submitButton.setOnAction(e -> {
            String restaurantName = restaurantComboBox.getValue();
            String itemName = itemNameInput.getText().trim();
            double price;
            try {
                price = Double.parseDouble(priceInput.getText().trim());
            } catch (NumberFormatException ex) {
                showAlert("Error", "Harga harus berupa angka.", itemName, Alert.AlertType.ERROR);
                return;
            }

            Restaurant restaurant = DepeFood.getRestaurantByName(restaurantName);
            handleTambahMenuRestoran(restaurant, itemName, price);
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.setScene(scene));

        layout.getChildren().addAll(restaurantLabel, restaurantComboBox, itemNameLabel, itemNameInput, priceLabel, priceInput, submitButton, backButton);
        layout.setAlignment(Pos.CENTER);

        return new Scene(layout, 400, 600);
    }

    private Scene createViewRestaurantsForm() {
        VBox layout = new VBox(10);

        ListView<String> restaurantListView = new ListView<>();
        restaurantListView.setItems(FXCollections.observableArrayList(
                DepeFood.getRestoList().stream().map(Restaurant::getNama).collect(Collectors.toList())
        ));

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.setScene(scene));

        layout.getChildren().addAll(restaurantListView, backButton);
        layout.setAlignment(Pos.CENTER);

        return new Scene(layout, 400, 600);
    }

    private void handleTambahRestoran(String nama) {
        String validName = DepeFood.getValidRestaurantName(nama);
        if (!validName.equals(nama)) {
            showAlert("Error", validName, validName, Alert.AlertType.ERROR);
            return;
        }

        DepeFood.handleTambahRestoran(nama);
        showAlert("Success", "Restoran berhasil ditambahkan.", validName, Alert.AlertType.INFORMATION);
    }

    private void handleTambahMenuRestoran(Restaurant restaurant, String itemName, double price) {
        if (restaurant == null || itemName.isEmpty() || price <= 0) {
            showAlert("Error", "Data menu tidak valid.", itemName, Alert.AlertType.ERROR);
            return;
        }

        DepeFood.handleTambahMenuRestoran(restaurant, itemName, price);
        showAlert("Success", "Menu berhasil ditambahkan.", itemName, Alert.AlertType.INFORMATION);
    }
}