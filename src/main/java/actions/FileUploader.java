package actions;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.FileOutputStream;
import java.util.zip.*;


public class FileUploader {

    public void uploadFile(String filePath) {
        String urlString = "http://home.bichael.net/uploads";
        String urlString1 = "http://localhost/";



        //TODO: add warning for Select Main Project Directory and submit that directory.


        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");

            ///

            ZipDirectory ZD = new ZipDirectory();
            String sourceFile = filePath;

            //System.out.println("ZIP PATH:" + getDir(filePath));
            //System.out.println("ZIP PATH 2:" + filePath.substring(0,filePath.length()-4));

            FileOutputStream fos = new FileOutputStream(getDir(filePath) + "\\dirCompressed.zip");
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            File fileToZip = new File(sourceFile);
            ZD.zipFile(fileToZip, fileToZip.getName(), zipOut);
            zipOut.close();
            fos.close();

            String zipFilePath = (getDir(filePath) + "\\dirCompressed.zip"); //is the zipfile directory.


            ///


            File file = new File(zipFilePath);
            FileInputStream fileInputStream = new FileInputStream(file);

            DataOutputStream request = new DataOutputStream(connection.getOutputStream());
            request.writeBytes("------WebKitFormBoundary7MA4YWxkTrZu0gW\r\n");
            request.writeBytes("Content-Disposition: form-data; name=\"files\"; filename=\"" + file.getName() + "\"\r\n");
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

    private String getDir(String filePath) {//go back one directory
        int count = 0;
        while (filePath.charAt(filePath.length()-1-count) != '/'){
            count++;
        }

        return filePath.substring(0,filePath.length() - count - 1);
    }
}
