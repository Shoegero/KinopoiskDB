package KinopoiskDB;

import KinopoiskDB.model.Film;
import KinopoiskDB.service.api.ApiService;
import KinopoiskDB.service.api.ApiServiceImpl;
import KinopoiskDB.service.database.DatabaseService;
import KinopoiskDB.service.database.DatabaseServiceImpl;
import KinopoiskDB.service.menu.MenuItemService;
import KinopoiskDB.service.menu.MenuItemServiceImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;

public class Solution extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @SneakyThrows
    @Override
    public void start(Stage stage) {
        ApiService apiService = new ApiServiceImpl();
        DatabaseService databaseService = new DatabaseServiceImpl();
        ObservableList<Film> langs = FXCollections.observableArrayList(databaseService.findAll());
        TableView<Film> tableView = new TableView<>(langs);
        MenuItemService menuItemService = new MenuItemServiceImpl(stage, apiService, databaseService, langs);
        tableView.setPrefWidth(1366);
        tableView.setPrefHeight(768);

        setTableColumns(tableView);

        MenuBar menuBar = new MenuBar();
        Menu studentsMenu = new Menu("Фильмы");


        MenuItem add = menuItemService.add();
        MenuItem delete = menuItemService.delete();

        studentsMenu.getItems().add(add);
        studentsMenu.getItems().add(delete);

        menuBar.getMenus().addAll(studentsMenu);
        HBox hRoot = new HBox(menuBar);
        VBox root = new VBox(hRoot, tableView);
        Scene scene = new Scene(root, 1600, 900);

        stage.setTitle("Фильмы");
        stage.setScene(scene);
        stage.show();
    }

    private void setTableColumns(TableView<Film> tableView) {
        TableColumn<Film, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("filmId"));
        tableView.getColumns().add(idColumn);

        TableColumn<Film, Image> posterColumn = new TableColumn<>("Movie poster");
        posterColumn.setCellValueFactory(new PropertyValueFactory<>("posterUrl"));
        posterColumn.setCellFactory(param -> new ImageTableCell<>());
        tableView.getColumns().add(posterColumn);

        TableColumn<Film, String> nameColumn = new TableColumn<>("Movie name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameRu"));
        tableView.getColumns().add(nameColumn);

        TableColumn<Film, String> ratingColumn = new TableColumn<>("Movie rating");
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tableView.getColumns().add(ratingColumn);

        TableColumn<Film, String> lengthColumn = new TableColumn<>("Movie length");
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("filmLength"));
        tableView.getColumns().add(lengthColumn);
    }
}
