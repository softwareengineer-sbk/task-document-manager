package app;


import app.utils.Utils;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class LocalDocumentManagerImpl implements DocumentManager {

    @Override
    public List<Document> getAll() {

        try {

            return getAllFiles();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Document> getAllFiles() throws SQLException, ClassNotFoundException {
        var query = "select * from documents";
        return new DBConnection().getAll(query);
    }

    @Override
    public Document save(InputStream inputStream, String fileName) throws IOException, SQLException, ClassNotFoundException {

        if (Utils.isValidFile(fileName)) {

            var bytes = inputStream.readAllBytes();

            var id = UUID.randomUUID().toString();

            var size = bytes.length;

            var document = new Document(id, size, fileName);

            var callback = saveFile(document, bytes);

            System.out.println("callback : " + callback);

            return document;

        } else {

            throw new IllegalArgumentException("Not valid file");

        }
    }

    public int saveFile(Document document, byte[] bytes) throws SQLException, ClassNotFoundException {
        var query = "insert into documents(id, size, name, data) values(?, ?, ?, ?)";
        return new DBConnection().save(query, document.id(), document.size(), document.name(), bytes);
    }


    @Override
    public Document get(String id) {

        try {

            var fileData = getFile(id);

            var path = System.getProperty("user.dir") + File.separator + fileData.getFileName();

            var result = new File(path).createNewFile();

            System.out.println("File created : " + result);

            Reader reader = new InputStreamReader(new ByteArrayInputStream(fileData.getBytes()));

            FileWriter fileWriter = new FileWriter(path);

            int i;
            while ((i = reader.read()) != -1) {
                fileWriter.write((char) i);
            }

            fileWriter.close();

            return new Document(null, 0, fileData.getFileName());

        } catch (IOException | SQLException | ClassNotFoundException e) {

            throw new RuntimeException(e);

        }
    }

    public FileData getFile(String id) throws SQLException, ClassNotFoundException {
        var query = "SELECT * FROM documents WHERE id = ?";
        return new DBConnection().get(query, id);
    }
}
