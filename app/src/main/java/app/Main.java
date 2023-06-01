package app;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("Jetty server is starting");

        JettyServer.start();

        System.out.println("Jetty server started successfully");
    }
}