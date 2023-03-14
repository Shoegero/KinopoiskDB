package KinopoiskDB.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Root {
    public String keyword;
    public Integer pagesCount;
    public List<Film> films;
}
