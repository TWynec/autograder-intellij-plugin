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
        String urlString = "http://localhost/";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");

            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);

            DataOutputStream request = new DataOutputStream(connection.getOutputStream());
            request.writeBytes("------WebKitFormBoundary7MA4YWxkTrZu0gW\r\n");
            request.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n");
            request.writeBytes("Content-Type: application/octet-stream\r\n\r\n");

            // Write
            int bytesRead;
            byte[] dataBuffer = new byte[1024];
            while ((bytesRead = fileInputStream.read(dataBuffer)) != -1) {
                request.write(dataBuffer, 0, bytesRead);
            }

            fileInputStream.close();
            request.writeBytes("\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
            request.flush();
            request.close();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
            } else {
                System.out.println("POST request did not work.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
