package com.azidan.listviewexample;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class AsyncFacebookPosts2 extends AsyncTask<Void , String , Void > {

    String dataURL;
    private String receivedData = "wow";
    ListView lv;
    Context c;
    Activity ac;
    Jsoner my_json_posts;
    MainActivity mac;



    /////

    post [] posts = new post[3];


    public AsyncFacebookPosts2(String data , Context c , ListView lv , MainActivity mac)
    {
        this.c = c;
        this.lv  = lv;
        dataURL = data;
        this.mac = mac;
    }

    @Override
    protected Void doInBackground(Void...unused) {

        String dataJSON = "{\n" +
                "    \"comments\": \"7 Comments\",\n" +
                "    \"likes\": \" Mohamed and 15 Others\",\n" +
                "    \"postImage\": \"https://scontent-mxp1-1.xx.fbcdn.net/t31.0-8/s960x960/13002569_1540212309616544_8029862815822088515_o.jpg\",\n" +
                "    \"postText\": \"Sometimes it needs something\",\n" +
                "    \"postTime\": \"15 minutes\",\n" +
                "    \"postType\": 2,\n" +
                "    \"shares\": \"20 Shares\",\n" +
                "    \"userName\": \"Ahmed Ali\",\n" +
                "    \"userPic\": \"https://scontent-mxp1-1.xx.fbcdn.net/v/t1.0-1/p320x320/12933086_1535637210074054_5807795911387928488_n.jpg?oh=04c9166ff9f37b9034c70c725e1b32f2&oe=5825ABC8\"\n" +
                "}";

        try {
            JSONObject jsonRootObject = new JSONObject(dataJSON);

            posts[0] = new JsonParser(jsonRootObject).getPost();
            posts[1] = new JsonParser(jsonRootObject).getPost();
            posts[2] = new JsonParser(jsonRootObject).getPost();


        } catch (JSONException e) {
            e.printStackTrace();
        }


        InputStream is = null;
        String url = dataURL;

        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
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

        lv.setAdapter(new CustomAdapter(mac,my_json_posts.getPosters()));

    }

}
