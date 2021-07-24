package com.example.dell.movieapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by DELL on 12/8/2016.
 */
public class detailsActivity extends AppCompatActivity {


    Activity act = this;
    movie my_movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(MainActivity.twoPane){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_second, new second_fragment(), MainActivity.DETAILFRAGMENT_TAG)
                    .commit();}
        else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_main, new second_fragment(), MainActivity.DETAILFRAGMENT_TAG)
                    .commit();}

    }
}
