package com.example.dell.benahapp.notify;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;


public class UploadFile extends AsyncTask<String, Void, String> {

    static final int BUFFER_SIZE = 4096;
    @Override
    protected String doInBackground(String... urls) {
        HttpURLConnection httpConn=null;

        String file=urls[0];
        String pram1=urls[1];
        String coursename=urls[2];

        try
        {
            //String file = Environment.getExternalStorageDirectory().getAbsolutePath() +"/UploadServlet30.zip";

            File uploadFile = new File(file);
            FileInputStream inputStream = new FileInputStream(uploadFile);


            //  String file=urls[1];
            //  File uploadFile = new File(file);
            // FileInputStream inputStream = new FileInputStream(uploadFile);

            System.out.println("File to upload: " + file);

            // creates a HTTP connection
            URL url1 = new URL(pram1);
            httpConn = (HttpURLConnection) url1.openConnection();
            httpConn.setUseCaches(false);
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("fileName", uploadFile.getName());
            httpConn.setRequestProperty("coursename", coursename);

            httpConn.connect();



            // sets file name as a HTTP header
            Log.i("fileName", uploadFile.getName());

            // opens output stream of the HTTP connection for writing data

            OutputStream outputStream = httpConn.getOutputStream();

            // Opens input stream of the file for reading data


            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;

            System.out.println("Start writing data...");

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("Data was written.");
            outputStream.close();
            inputStream.close();
        }
        catch(SocketTimeoutException e)
        {
            Log.e("Debug", "error: " + e.getMessage(), e);
        }
        catch (MalformedURLException ex)
        {
            Log.e("Debug", "error: " + ex.getMessage(), ex);
        }
        catch (IOException ioe)
        {
            Log.e("Debug", "error: " + ioe.getMessage(), ioe);
        }

        try
        {

            // always check HTTP response code from server
            int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // reads server's response
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                String response = reader.readLine();
                System.out.println("Server's response: " + response);
            } else {
                System.out.println("Server returned non-OK code: " + responseCode);
            }
        }
        catch (IOException ioex){
            Log.e("Debug", "error: " + ioex.getMessage(), ioex);
        }
        return null;
    }
    @Override
    protected void onPostExecute(String result) {
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
    }
}


