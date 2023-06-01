package app.servlets;

import app.Document;
import app.Loader;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static app.utils.Constants.SAVE_DIR;

@WebServlet("/GetAllServlet")
public class GetAllServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        var documents = Loader.provider().create().getAll();

        response.getWriter().print(new Gson().toJson(documents));
    }
}
