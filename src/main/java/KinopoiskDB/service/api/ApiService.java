package KinopoiskDB.service.api;

import KinopoiskDB.model.Film;
import lombok.SneakyThrows;

public interface ApiService {

    @SneakyThrows
    Film movieInfo(String keyword);
}
