package com.example.dell.benahapp.notify;


import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class LoginParser {

    String line = "";
    String s = "";

    public static void LoginParser(List<NameValuePair> lst, String url) throws IOException {
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(lst));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            Log.e("x-ray",httpResponse.getStatusLine().toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

//getparm---> return if user is student or doctor
    public String getparameter(String url) {
        HttpClient client = new DefaultHttpClient();

            HttpGet request = new HttpGet(url);
            HttpResponse response = null;
            try {
                response = client.execute(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
//Here i try to read the response
            BufferedReader rd = null;
            try {
                String line;
                rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                while ((line = rd.readLine()) != null) {
                    s += line;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
        }




}