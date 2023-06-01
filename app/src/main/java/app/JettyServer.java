package app;

import app.servlets.DownloadServlet;
import app.servlets.GetAllServlet;
import app.servlets.UploadServlet;
import jakarta.servlet.MultipartConfigElement;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class JettyServer {
    public static void start() throws Exception {
        Server server = new Server();
        try (ServerConnector connector = new ServerConnector(server)) {
            connector.setPort(8080);
            server.setConnectors(new Connector[]{connector});
        }

        server.setHandler(withHandler());

        server.start();
    }


    private static Handler withHandler() {
        var servletHandler = new ServletHandler();

        int size = 1024 * 1024 / 10;

        servletHandler.addServletWithMapping(UploadServlet.class, "/upload")
                .getRegistration().setMultipartConfig(
                        new MultipartConfigElement("", size, size, size)
                );

        servletHandler.addServletWithMapping(DownloadServlet.class, "/download/*");

        servletHandler.addServletWithMapping(GetAllServlet.class, "/all");

        return servletHandler;
    }
}