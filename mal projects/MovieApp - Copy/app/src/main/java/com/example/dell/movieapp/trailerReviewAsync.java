package com.example.dell.movieapp;

import android.app.Activity;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.squareup.picasso.Downloader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by DELL on 13/9/2016.
 */
public class trailerReviewAsync extends AsyncTask<Void , String , Void > {


    String dataURL;
    final String my_key = "1a9aa0f722cc75244ff4e13fc2d07575";
    movie my_movie;
    String receivedData;
    Activity act ;
    boolean valid = true;
    public trailerReviewAsync(Activity act ,movie my_movie)
    {
        this.my_movie = my_movie;
        this.act = act;
    }

    @Override
    protected Void doInBackground(Void...unused) {

    try {
    dataURL = getMovieurl();

    getURLData(dataURL);

    addTrailerlink();

    dataURL = getReviewurl();

    getURLData(dataURL);

    addReviewsData();

    }
    catch (Exception e) {
    Log.i("man22", "here");
        valid = false;
    }
        return(null);
    }


    @Override
    protected void onPostExecute(Void unused) {

        if (!valid)
        {
            Toast.makeText(act, "You are offline,Get connected!", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(act, detailsActivity.class);
        //   intent.putExtra("message", message);
        intent.putExtra("movie" , my_movie);
        act.startActivity(intent);

    }

    private void addReviewsData() {

        JSONArray arrayMovieData = null;
        JSONObject jsonRootObject = null;

        try {

            jsonRootObject = new JSONObject(receivedData);
            arrayMovieData = jsonRootObject.getJSONArray("results");
            my_movie.reviews = new review[arrayMovieData.length()];
            Log.i("mmm number " , arrayMovieData.length()+"");
            String w = "{\"content\":\"A great movie for the whole family. Watch my full review here.\\r\\n\\r\\nhttp:\\/\\/www.hweird1reviews.com\\/allreviews\\/\\/the-jungle-book\",\"id\":\"57acf1c89251415882000311\",\"author\":\"Austin Singleton\",\"url\":\"https:\\/\\/www.themoviedb.org\\/review\\/57acf1c89251415882000311\"}";
            JSONObject j =new JSONObject(w);
            Log.i("mmm opt " , j.optString("author"));

            for(int i = 0 ; i < arrayMovieData.length(); i++) {

                Log.i("mmm array",arrayMovieData.getJSONObject(i).toString());
              my_movie.reviews[i] = new review(arrayMovieData.getJSONObject(i).optString("author"), arrayMovieData.getJSONObject(i).optString("content"));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private String getMovieurl()
    {

        Uri.Builder  uriBuilder = new Uri.Builder();

        return uriBuilder.scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath(my_movie.id)
                .appendPath("videos")
                .appendQueryParameter("api_key",my_key).build().toString();

    }


    private String getReviewurl()
    {

        Uri.Builder  uriBuilder = new Uri.Builder();

        return uriBuilder.scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath(my_movie.id)
                .appendPath("reviews")
                .appendQueryParameter("api_key",my_key).build().toString();

    }


    private void getURLData(String url)
    {

        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            response = httpclient.execute(new HttpGet(url));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                receivedData = out.toString();
                out.close();

            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void addTrailerlink()
    {
        JSONArray arrayMovieData = null;
        JSONObject jsonRootObject = null;
        String key = "";

        try {

            jsonRootObject = new JSONObject(receivedData);
            arrayMovieData = jsonRootObject.getJSONArray("results");
            key =       arrayMovieData.getJSONObject(0).optString("key");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Uri.Builder  uriBuilder2 = new Uri.Builder();
        if(!key.equals("")) {
            my_movie.trailerPath = uriBuilder2.scheme("https")
                    .authority("www.youtube.com")
                    .appendPath("watch")
                    .appendQueryParameter("v", key).build().toString();
        }



    }
}
