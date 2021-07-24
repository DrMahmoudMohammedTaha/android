package com.example.dell.movieapp;

import android.app.Activity;
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

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    static boolean twoPane = false;
    GridView gv    ;
    MainActivity act = this;
    public static final String DETAILFRAGMENT_TAG = "DFTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataStore.activ = getApplicationContext();
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_second) != null) {
            twoPane = true;
            Log.i("sss1" , "two pane found");
            if (savedInstanceState == null) {

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_second, new second_fragment(),
                                DETAILFRAGMENT_TAG)
                        .commit();
            }

        }
        else
        {
            twoPane = false;
        }
        // api id 1a9aa0f722cc75244ff4e13fc2d07575
        //images http://image.tmdb.org/t/p/w185/
    }


    public  void displayMovie(movie m)
    {


        if(twoPane) {
            second_fragment second = new second_fragment();
            Bundle b = new Bundle();
            b.putSerializable("movie", m);
            second.setArguments(b);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_second, second, DETAILFRAGMENT_TAG)
                    .commit();
        }
        else{
            Intent intent = new Intent(MainActivity.this , detailsActivity.class);
            intent.putExtra("movie",m);
            startActivity(intent);
        }

    }

}
