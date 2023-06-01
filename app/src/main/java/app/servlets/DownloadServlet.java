package app.servlets;

import app.Document;
import app.Loader;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        var id = request.getPathInfo().substring(1);

        Document document = Loader.provider().create().get(id);

        response.getWriter().print(new Gson().toJson(document));
    }
}
