package KinopoiskDB.service.menu;

import KinopoiskDB.model.Film;
import KinopoiskDB.service.api.ApiService;
import KinopoiskDB.service.api.ApiServiceImpl;
import KinopoiskDB.service.database.DatabaseService;
import KinopoiskDB.service.database.DatabaseServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    Stage stage = new Stage();

    ApiService apiService = new ApiServiceImpl();
    DatabaseService databaseService = new DatabaseServiceImpl();
    ObservableList<Film> langs = FXCollections.observableArrayList(databaseService.findAll());

    @Override
    public MenuItem add() {
        MenuItem add = new MenuItem("Добавить");
        add.setOnAction(e -> {
            final Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.initOwner(stage);
            Text text = new Text("Что написать?");
            Text movieNameText = new Text("Название фильма");
            TextField movieText = new TextField();
            ImageView imageView = new ImageView();
            Button btn = new Button();
            btn.setText("Сохранить");
            btn.setOnAction(actionEvent -> {
                Film film = apiService.movieInfo(movieText.getText());
                databaseService.add(film.getPosterUrl(), film.getNameRu(), film.getRating(), film.getFilmLength());
                langs.clear();
                langs.addAll(databaseService.findAll());
            });
            VBox additionalRoot = new VBox(movieNameText
                    , movieText
                    , btn);
            Scene additionalScene = new Scene(additionalRoot, 400, 400);
            popup.setTitle("Добавить фильм");
            popup.setScene(additionalScene);
            popup.show();
        });
        return add;
    }

    @Override
    public MenuItem delete() {
        MenuItem delete = new MenuItem("Удалить");
        delete.setOnAction(e -> {
            final Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.initOwner(stage);
            Text text = new Text("Что написать?");
            Text idText = new Text("ID, которое хотите удалить");
            TextField id = new TextField();
            Button btn = new Button();
            btn.setText("Удалить");
            btn.setOnAction(actionEvent -> {
                databaseService.delete(Integer.parseInt(id.getText()));
                langs.clear();
                langs.addAll(databaseService.findAll());
            });
            VBox additionalRoot = new VBox(idText, id
                    , btn);
            Scene additionalScene = new Scene(additionalRoot, 400, 400);
            popup.setTitle("Удалить фильм");
            popup.setScene(additionalScene);
            popup.show();
        });
        return delete;
    }
}
