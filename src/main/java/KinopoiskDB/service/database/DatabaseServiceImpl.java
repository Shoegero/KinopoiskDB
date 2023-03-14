package KinopoiskDB.service.database;

import KinopoiskDB.model.Film;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseServiceImpl implements DatabaseService {
    final String DB_URL = "jdbc:postgresql://192.168.0.105:8492/postgres";
    final String USER = "root";
    final String PASS = "3221";
    final String FIND_ALL = "SELECT * from movies ORDER BY id";
    final String CREATE = "INSERT into movies values(nextval('\"movies_id_seq\"'::regclass),?,?,?,?)";
    final String DELETE = "DELETE from movies where id = ?";

    @SneakyThrows
    @Override
    public void add(String nameRu, String posterUrl, String rating, String filmLength) {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement(CREATE);
        stmt.setString(1, posterUrl);
        stmt.setString(2, nameRu);
        stmt.setString(3, rating);
        stmt.setString(4, filmLength);
        stmt.executeUpdate();
    }

    @SneakyThrows
    @Override
    public void delete(int id) {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement(DELETE);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    @SneakyThrows
    @Override
    public List<Film> findAll() {
        List<Film> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(FIND_ALL);) {
            while (rs.next()) {
                list.add(new Film(rs.getInt("id")
                        , rs.getString("poster")
                        , rs.getString("name")
                        , rs.getString("rating")
                        , rs.getString("length")));
            }
        }
        return list;
    }
}
