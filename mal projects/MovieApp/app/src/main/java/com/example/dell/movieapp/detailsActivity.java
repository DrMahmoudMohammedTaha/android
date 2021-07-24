package com.example.dell.movieapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by DELL on 12/8/2016.
 */


public class detailsActivity extends AppCompatActivity {


    Activity act = this;
    movie my_movie;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_layout);

        movie m = (movie) getIntent().getExtras().getSerializable("movie");
        MainActivity mm = (MainActivity) getIntent().getExtras().getSerializable("main");
        second_fragment second = new second_fragment();
        Bundle b = new Bundle();
        b.putSerializable("movie", m);
        second.setArguments(b);

        getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_detail_one,second, MainActivity.DETAILFRAGMENT_TAG)
                    .commit();

    }

}
