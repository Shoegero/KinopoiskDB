package KinopoiskDB;

import KinopoiskDB.model.Root;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoints {
    @GET("api/v2.1/films/search-by-keyword")
    Call<Root> listRepos(
            @Query("keyword") String keyword,
            @Query("page") int page
    );
}
