package app;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public interface DocumentManager {

    List<Document> getAll();

    List<Document> getAllFiles() throws SQLException, ClassNotFoundException;

    Document save(InputStream inputStream, String filename) throws ServletException, IOException, SQLException, ClassNotFoundException;

    int saveFile(Document document, byte[] bytes) throws SQLException, ClassNotFoundException;

    Document get(String id);

    FileData getFile(String id) throws SQLException, ClassNotFoundException;
}
