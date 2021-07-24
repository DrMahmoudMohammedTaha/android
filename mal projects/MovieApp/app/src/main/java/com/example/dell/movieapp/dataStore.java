package com.example.dell.movieapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by DELL on 21/9/2016.
 */
public class dataStore {

static Context activ;


    public static boolean getSavedSelection( ) {
        boolean state ;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activ);
        state =  prefs.getBoolean("stateOrder" , true);
        return  state;
    }
    public static void saveMovies(  movie[] m) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activ);
        prefs.edit().putInt("length",m.length).apply();
        prefs.edit().putStringSet("movies", moviesToStrings(m)).apply();

    }
    public static  void saveSelection(boolean state , Context c ) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activ);
        prefs.edit().putBoolean("stateOrder",state )
                .apply();

    }
    public static void SaveNewMovie(  movie m ) {
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
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activ);
        prefs.edit().putInt("length",movies.length+1).apply();
        prefs.edit().putStringSet("movies", moviesToStrings(mov)).apply();
    }
    public static  boolean isFavorite(movie m ) {
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

    public static movie[] getSavedFavorite() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activ);
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
