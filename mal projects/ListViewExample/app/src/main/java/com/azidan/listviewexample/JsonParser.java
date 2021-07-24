package com.azidan.listviewexample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DELL on 6/8/2016.
 */
public class JsonParser {


    post my_post = new post();

    public JsonParser (JSONObject jsonRootObject) {


            my_post.postImage = jsonRootObject.optString("postImage");
            my_post.userImage = jsonRootObject.optString("userPic");
            my_post.name = jsonRootObject.optString("userName");
            my_post.time = jsonRootObject.optString("postTime");
            my_post.text = jsonRootObject.optString("posttext");
            String l = jsonRootObject.optString("likes");
            String c = jsonRootObject.optString("comments");
            String s = jsonRootObject.optString("shares");
            my_post.notification = l + " likes , " + c + " , " + s;



    }
        public post getPost()
             {
                return my_post;
             }




}
