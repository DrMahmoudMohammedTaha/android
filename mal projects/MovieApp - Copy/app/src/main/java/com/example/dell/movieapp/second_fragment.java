package com.example.dell.movieapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by DELL on 16/9/2016.
 */
public class second_fragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    movie my_movie;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        try {

            my_movie = (movie) getArguments().getSerializable("movie");

        }catch (Exception e)
        {


        }

        TextView tv = (TextView) rootView.findViewById(R.id.titleText);


        tv.setText(my_movie.title);
        tv = (TextView) rootView.findViewById(R.id.dateText);


        tv.setText(my_movie.date);
        tv = (TextView) rootView.findViewById(R.id.overviewText);
        tv.setText(my_movie.overview);


        ImageView iv = (ImageView) rootView.findViewById(R.id.postView);
        //Loading image from below url into imageView
        Picasso.with((Activity)getContext())
                .load(my_movie.Image)
                .resize(200,200)
                .into(iv);



        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!my_movie.trailerPath.equals("")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(my_movie.trailerPath));
                    getContext().startActivity(intent);
                }

            }
        });

        Button bt  = (Button) rootView.findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(my_movie.reviews.length != 0) {
                    Intent intent = new Intent((Activity)getContext(), reviewsActivity.class);
                    intent.putExtra("movie", my_movie);
                    ((Activity)getContext()).startActivity(intent);
                }
            }
        });

        final Button fbt  = (Button) rootView.findViewById(R.id.button5);

        fbt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(!((MainActivity) getContext()).isFavorite(my_movie))
                {
                    ((MainActivity) getContext()).SaveNewMovie(my_movie);
                    Toast.makeText((Activity)getContext() , "added successfully!!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText((Activity)getContext() , "it has been added before!!", Toast.LENGTH_LONG).show();

                }
            }
        });

        return rootView;

    }

        @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
