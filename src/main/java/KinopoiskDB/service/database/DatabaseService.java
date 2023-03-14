package KinopoiskDB.service.database;

import KinopoiskDB.model.Film;
import lombok.SneakyThrows;

import java.util.List;

public interface DatabaseService {
    void add(String nameRu, String posterUrl, String rating, String filmLength);

    void delete(int id);

    @SneakyThrows
    List<Film> findAll();
}
