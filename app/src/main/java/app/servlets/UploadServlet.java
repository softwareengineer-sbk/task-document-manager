package app.servlets;

import app.Document;
import app.Loader;
import app.utils.Utils;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var part = (Part) request.getParts().toArray()[0];

        String fileName = Utils.extractFileName(part);

        Document document = null;
        try {
            document = Loader.provider().create().save(part.getInputStream(), fileName);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.getWriter().print(new Gson().toJson(document));
    }
}
