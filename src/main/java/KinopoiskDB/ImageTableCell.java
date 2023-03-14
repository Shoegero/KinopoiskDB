package KinopoiskDB;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageTableCell<Film, String> extends TableCell<Film, String> {
    final ImageView imageView = new ImageView();

    ImageTableCell() {
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(String item, boolean empty) {

        super.updateItem(item, empty);

        if (item == null) {
            imageView.setImage(null);
            setText(null);
            setGraphic(null);
        } else {
            Image image = new Image((java.lang.String) item);
            imageView.setImage(image);
            imageView.setFitHeight(200);
            imageView.setFitWidth(150);
            setGraphic(imageView);
        }
    }
}
