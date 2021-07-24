package com.example.dell.movieapp;

import org.json.JSONObject;

/**
 * Created by DELL on 6/8/2016.
 */
public class JsonParser {

    final String imageURL = "http://image.tmdb.org/t/p/w185/";

    movie my_movie ;

    public JsonParser(JSONObject jsonRootObject) {

        my_movie = new movie(jsonRootObject.optString("original_title"), imageURL + jsonRootObject.optString("poster_path"));
        my_movie.overview =  jsonRootObject.optString("overview");
        my_movie.date = jsonRootObject.optString("release_date");
        my_movie.id = jsonRootObject.optString("id");




    }
        public movie getMovie()
             {
                 return my_movie;
             }

}
