package assignments.assignment4.page;

import assignments.assignment3.DepeFood;
import assignments.assignment3.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.util.List;
import java.util.stream.Collectors;

public abstract class MemberMenu {
    private Scene scene;
    protected ObservableList<String> restaurantNames;
    protected ComboBox<String> restaurantComboBox;
    protected ListView<String> restaurantListView;

    public MemberMenu() {
        this.restaurantNames = FXCollections.observableArrayList();
        this.restaurantComboBox = new ComboBox<>(restaurantNames);
        this.restaurantListView = new ListView<>(restaurantNames);
        refresh();
    }

    abstract protected Scene createBaseMenu();

    protected void showAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public Scene getScene() {
        return this.scene;
    }

    protected void refresh() {
        List<Restaurant> restaurants = DepeFood.getRestoList();
        restaurantNames.setAll(restaurants.stream().map(Restaurant::getNama).collect(Collectors.toList()));
    }
}