package com.example.dell.movieapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.LinkedHashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    static boolean twoPane = false;
    GridView gv    ;
    MainActivity act = this;
    public static final String DETAILFRAGMENT_TAG = "DFTAG";
    //static ArrayList <movie> savedMovies = new ArrayList<movie>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("sss1" , "started");

        setContentView(R.layout.activity_main);
        Log.i("sss1" , "set");

/*
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main, new main_fragment(), DETAILFRAGMENT_TAG)
                .commit();
*/
        if (findViewById(R.id.fragment_second) != null) {
            twoPane = true;
            Log.i("sss1" , "two pane found");

        }
        else
        {
            twoPane = false;
            Log.i("sss1" , "one pane found");
        }






        // api id 1a9aa0f722cc75244ff4e13fc2d07575
        //images http://image.tmdb.org/t/p/w185/


    }


    public  void displayMovie(movie m)
    {

        second_fragment second = new second_fragment();
        Bundle b = new Bundle();
        b.putSerializable("movie",  m);
        second.setArguments(b);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_second, second, DETAILFRAGMENT_TAG)
                .commit();


    }

    public   boolean getSavedSelection( )
    {
        boolean state ;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        state =  prefs.getBoolean("stateOrder" , true);
        return  state;
    }



    public  void saveMovies(  movie[] m) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putInt("length",m.length).apply();
        prefs.edit().putStringSet("movies", moviesToStrings(m)).apply();

    }


        public static  void saveSelection(boolean state , Context c )
{

   SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
    prefs.edit().putBoolean("stateOrder",state )
            .apply();

}


    public  void SaveNewMovie(  movie m) {
        movie movies []= getSavedFavorite();
        if (movies == null)
        {
            movies = new movie[0];
        }
        movie mov []= new movie[movies.length+1];
        for (int i = 0; i < movies.length; i++) {
            mov[i] = movies[i];
        }
        mov[movies.length]=m;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putInt("length",movies.length+1).apply();
        prefs.edit().putStringSet("movies", moviesToStrings(mov)).apply();

        Log.i("hhh chechker" ,  ( getSavedFavorite() == null) + " here" );
    }

    public  boolean isFavorite(movie m) {
        movie movies []= getSavedFavorite();
        if (movies == null)
            return false;

        for (int i = 0; i < movies.length; i++) {
            if(movies[i].title.equals(m.title)) {
                return  true;
            }

        }
        return  false;
    }

    public  movie[] getSavedFavorite( ) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        try {
            Set<String> movieData= new LinkedHashSet<String>();
            movieData = prefs.getStringSet("movies", movieData);
            int len = prefs.getInt("length",0);
            String s[]= new String[len];
            s = movieData.toArray(s);
            return  stringsToMovies(s);
        }
        catch (Exception e)
        {
            Log.i("hhh gsf" , e.toString());
            return  null;
        }
    }


    public static  movie[] stringsToMovies(  String [] s) {
        movie m []= new movie[s.length];
        for (int i = 0; i < s.length; i++) {
            m[i]=new movie();
            m[i].id = s[i].split("===")[0];
            m[i].title = s[i].split("===")[1];
            m[i].Image = s[i].split("===")[2];
            m[i].date = s[i].split("===")[3];
            m[i].overview = s[i].split("===")[4];

        }
        return m;
    }
    public static  Set<String> moviesToStrings( movie[]  m) {
        Set<String> s = new LinkedHashSet<String>();
        for (int i = 0; i < m.length; i++) {
            s.add(  m[i].id + "===" +
                    m[i].title + "===" +
                    m[i].Image + "===" +
                    m[i].date + "===" +
                    m[i].overview );
        }
        return  s;
    }

}
