package com.example.dell.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by DELL on 14/9/2016.
 */
public class reviewsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(MainActivity.twoPane){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_second, new review_fragment(), MainActivity.DETAILFRAGMENT_TAG)
                .commit();}
        else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_main, new review_fragment(), MainActivity.DETAILFRAGMENT_TAG)
                    .commit();}
    }
    }


