package actions;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUploader {

    public void uploadFile(String filePath) {
        //String filePath = "path/to/your/file.ext";
        String urlString = "http://localhost/index.php";

        try {
            // Open a connection to the server
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            System.out.println(connection.getResponseCode());

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Set the request headers
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");

            // Open a stream to the file
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);

            // Start building the request body
            DataOutputStream request = new DataOutputStream(connection.getOutputStream());
            request.writeBytes("------WebKitFormBoundary7MA4YWxkTrZu0gW\r\n");
            request.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n");
            request.writeBytes("Content-Type: application/octet-stream\r\n\r\n");

            // Write the file data to the request body
            int bytesRead;
            byte[] dataBuffer = new byte[1024];
            while ((bytesRead = fileInputStream.read(dataBuffer)) != -1) {
                request.write(dataBuffer, 0, bytesRead);
            }

            // Close the file input stream and request body
            fileInputStream.close();
            request.writeBytes("\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
            request.flush();
            request.close();

            // Get the response from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = reader.readLine();
            reader.close();

            // Print the response
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
