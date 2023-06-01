package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private Connection dbConnection;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/test";
    private static final String USER = "sardorbekkhalimboev";
    private static final String PASSWORD = "postgres";

    public DBConnection() throws ClassNotFoundException, SQLException {
        dbConnection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public List<Document> getAll(String query) throws SQLException {
        try (var resultSet = dbConnection.createStatement().executeQuery(query)) {
            var list = new ArrayList<Document>();

            while (resultSet.next()) {

                String id = resultSet.getString("id");
                int size = resultSet.getInt("size");
                String name = resultSet.getString("name");

                var user = new Document(id, size, name);

                list.add(user);
            }
            return list;
        }
    }

    public int save(String query, String id, int size, String fileName, byte[] bytes) {
        try (var statement = dbConnection.prepareStatement(query)) {

            statement.setString(1, id);
            statement.setInt(2, size);
            statement.setString(3, fileName);
            statement.setBytes(4, bytes);

            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public FileData get(String query, String id) {
        try (var statement = dbConnection.prepareStatement(query)) {

            statement.setString(1, id);

            var resultSet = statement.executeQuery();

            if (resultSet.next()) {

                var fileName = resultSet.getString("name");

                byte[] bytes = resultSet.getBytes("data");

                return new FileData(fileName, bytes);
            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

        return null;

    }
}
