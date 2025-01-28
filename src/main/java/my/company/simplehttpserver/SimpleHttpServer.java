package my.company.simplehttpserver;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class SimpleHttpServer {

    private static final int PORT = 8080;
    private static final String BASE_DIRECTORY = "public"; // Asegúrate de que esta carpeta exista y contenga los archivos estáticos.

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    handleClient(clientSocket);
                } catch (Exception e) {
                    System.err.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    private static void handleClient(Socket clientSocket) throws IOException {
        InputStream input = clientSocket.getInputStream();
        OutputStream output = clientSocket.getOutputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        PrintWriter writer = new PrintWriter(output);

        // Read the HTTP request line
        String requestLine = reader.readLine();
        if (requestLine == null || requestLine.isEmpty()) {
            return;
        }

        System.out.println("Request: " + requestLine);

        // Parse the request line
        String[] parts = requestLine.split(" ");
        if (parts.length < 3 || !parts[0].equals("GET")) {
            sendResponse(writer, 400, "Bad Request", "Unsupported HTTP method.");
            return;
        }

        String path = parts[1];
        if (path.equals("/")) {
            path = "/index.html"; // Default file
        }

        // Check for REST service calls
        if (path.equals("/greeting")) {
            sendResponse(writer, 200, "OK", "Hola, bienvenido!");
        } else if (path.equals("/hey")) {
            sendResponse(writer, 200, "OK", "HEY!");
        } else if (path.equals("/date")) {
            sendResponse(writer, 200, "OK", "Fecha actual: " + java.time.LocalDate.now());
        } else if (path.equals("/arep")) {
            File imageFile = new File(BASE_DIRECTORY + "/images/eci_logo.png");
            if (imageFile.exists() && imageFile.isFile()) {
                sendFileResponse(writer, output, imageFile);
            } else {
                sendResponse(writer, 404, "Not Found", "Imagen no encontrada.");
            }
        } else {
            File file = new File(BASE_DIRECTORY + path);
            if (file.exists() && file.isFile()) {
                sendFileResponse(writer, output, file);
            } else {
                sendResponse(writer, 404, "Not Found", "File not found.");
            }
        }
    }

    private static void sendResponse(PrintWriter writer, int statusCode, String statusMessage, String body) {
        writer.printf("HTTP/1.1 %d %s\r\n", statusCode, statusMessage);
        writer.println("Content-Type: text/plain");
        writer.println("Content-Length: " + body.length());
        writer.println();
        writer.println(body);
        writer.flush();
    }

    private static void sendFileResponse(PrintWriter writer, OutputStream output, File file) throws IOException {
        String contentType = Files.probeContentType(file.toPath());
        long contentLength = file.length();

        writer.printf("HTTP/1.1 200 OK\r\n");
        writer.println("Content-Type: " + contentType);
        writer.println("Content-Length: " + contentLength);
        writer.println();
        writer.flush();

        // Send file content
        try (FileInputStream fileInput = new FileInputStream(file)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = fileInput.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            output.flush();
        }
    }
}
