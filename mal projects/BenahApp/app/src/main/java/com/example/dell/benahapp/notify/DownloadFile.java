package com.example.dell.benahapp.notify;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by basma on 27/06/2017.
 */
public class DownloadFile extends AsyncTask<String, Void, String> {

    static final int BUFFER_SIZE = 4096;
    @Override
    protected String doInBackground(String... urls) {
        HttpURLConnection httpConn=null;


        String pram1=urls[0];
        String fileurl=urls[1];

        try {
           // String PATH = Environment.getExternalStorageDirectory().toString() + File.separator + "/Download";
            //File downloadFile = new File(PATH);
            //FileOutputStream out = new FileOutputStream(downloadFile);

            URL url1 = new URL(pram1);
            httpConn = (HttpURLConnection) url1.openConnection();
            httpConn.setUseCaches(false);
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("fileurl", fileurl);
            httpConn.connect();



/*

            InputStream input =  httpConn.getInputStream();

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;

            System.out.println("Start writing data...");

            while ((bytesRead = input.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            input.close();
*/

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




