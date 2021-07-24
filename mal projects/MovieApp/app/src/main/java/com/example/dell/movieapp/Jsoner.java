package com.example.dell.movieapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DELL on 6/8/2016.
 */
public class Jsoner implements parser {

        private movie [] movies;
        JSONArray arrayMovie ;
     public Jsoner(String data)
     {

         JSONObject  jsonRootObject ;

         try {

             jsonRootObject = new JSONObject(data);

             arrayMovie = jsonRootObject.getJSONArray("results");

         } catch (JSONException e) {
             e.printStackTrace();
         }



            movies = new movie[arrayMovie.length()];
     }

    @Override
    public void parse() {



        try {

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i = 0 ; i < arrayMovie.length(); i++){
                  movies[i] =  new JsonParser(arrayMovie.getJSONObject(i)).getMovie();
            }

        } catch (JSONException e) {e.printStackTrace(); Log.i( "www", e.getMessage() );   }
    }

    public movie[] getMovies() {
        return movies;
    }
}
