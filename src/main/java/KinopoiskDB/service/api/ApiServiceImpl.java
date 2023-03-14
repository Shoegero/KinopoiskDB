package KinopoiskDB.service.api;

import KinopoiskDB.ApiBuilder;
import KinopoiskDB.ApiEndPoints;
import KinopoiskDB.model.Film;
import KinopoiskDB.model.Root;
import lombok.SneakyThrows;

public class ApiServiceImpl implements ApiService {

    private String apiKey = "aca7e988-42c8-4be0-9db9-18618ada5e4c";

    @SneakyThrows
    @Override
    public Film movieInfo(String keyword) {
        ApiEndPoints apiEndPoints = ApiBuilder.createService(ApiEndPoints.class, apiKey);
        Root root = apiEndPoints.listRepos(keyword, 1).execute().body();
        return root.getFilms().get(0);
    }
}
