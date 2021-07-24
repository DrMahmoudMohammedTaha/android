package com.azidan.listviewexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context=this;
        lv=(ListView) findViewById(R.id.listView);
        new AsyncFacebookPosts2("https://dl.dropboxusercontent.com/s/7rvknz9e6tfprun/facebookFeed.json" , context, lv , this).execute();

/*
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
            JSONObject  jsonRootObject = new JSONObject(dataJSON);

            post [] posts = new post[3];
            posts[0] = new JsonParser(jsonRootObject).getPost();
            posts[1] = new JsonParser(jsonRootObject).getPost();
            posts[2] = new JsonParser(jsonRootObject).getPost();

            lv.setAdapter(new CustomAdapter(this,posts));

        } catch (JSONException e) {
            e.printStackTrace();
        }

*/

    }


}