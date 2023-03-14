package KinopoiskDB.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Film {
    public int filmId;
    public String nameRu;
    public String posterUrl;
    public String rating;
    public String filmLength;
}
