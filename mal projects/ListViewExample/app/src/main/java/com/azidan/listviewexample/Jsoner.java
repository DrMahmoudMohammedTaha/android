package com.azidan.listviewexample;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by DELL on 6/8/2016.
 */
public class Jsoner implements parser {

        private String []jsonTotal ;
        private post [] posters  ;

     public Jsoner(String data)
     {



         jsonTotal = data.substring(1).replaceFirst("]","").split(", ");
            posters = new post[jsonTotal.length];



     }

    @Override
    public void parse() {



        try {

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i = 0 ; i < jsonTotal.length; i++){

                jsonTotal [i] = jsonTotal[i].substring(0,jsonTotal[i].length()-2) .replaceAll("n  " , "  ")+ "}";

                if(i == jsonTotal.length-1)
                {
                    jsonTotal[i] = jsonTotal[i].substring(0,jsonTotal[i].length()-2) + "}";
                }


                JSONObject jsonObject =  new JSONObject(jsonTotal[i]);
                posters[i] =  new JsonParser(jsonObject).getPost();

            }

        } catch (JSONException e) {e.printStackTrace(); Log.i( "www", e.getMessage() );   }
    }

    public post[] getPosters() {

        return  posters;
    }
}
