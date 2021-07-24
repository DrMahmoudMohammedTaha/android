package com.example.dell.movieapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class AsyncMovies extends AsyncTask<Void , String , Void > {

    String dataURL;
    private String receivedData = "wow";
    GridView gv;
    Context c;
    Activity ac;
    MainActivity mac;
    Jsoner my_json_posts;
    final String my_key = "1a9aa0f722cc75244ff4e13fc2d07575";
    String order ;

    /////



    public AsyncMovies(Context c , GridView gv , MainActivity mac , boolean state)
    {
        this.c = c;
        this.gv  = gv;
        this.mac = mac;
        order = (state)? "popularity.desc": "vote_average.desc";
     }

    @Override
    protected Void doInBackground(Void...unused) {

        Uri.Builder  uriBuilder = new Uri.Builder();
    dataURL =     uriBuilder.scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("discover")
                .appendPath("movie")
                .appendQueryParameter("sort_by", order)
                .appendQueryParameter("api_key",my_key).build().toString();


        InputStream is = null;
        String url = dataURL;

        try {


            ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            } catch (UnsupportedEncodingException e) {
                Log.e("man", "working");
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                Log.e("man1", "working");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            Log.e("man2", "working");

            return null;

        }




        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            receivedData = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        my_json_posts =    new Jsoner(receivedData);
       my_json_posts.parse();


        return(null);
    }
    @Override
    protected void onProgressUpdate(String...item) {


    }
    @Override
    protected void onPostExecute(Void unused) {
        if(!receivedData.equals("wow")) {
            GridAdaptor gra  = new GridAdaptor(mac, my_json_posts.getMovies());
            gv.setAdapter(gra);
            if (MainActivity.twoPane)
            {
                new TrailerReviewAsync(mac , gra.movies[0]).execute();
            }

        }else {
            Toast.makeText(mac, "You are offline,Get connected!", Toast.LENGTH_LONG).show();
        }
    }

}
