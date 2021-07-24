package com.example.dell.movieapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by DELL on 16/9/2016.
 */
public class second_fragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    movie my_movie;
    Activity activ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        try  {

            my_movie = (movie) getArguments().getSerializable("movie");

        }catch (Exception e)
        {


            TextView tv = (TextView) rootView.findViewById(R.id.titleText);

            tv.setVisibility(View.INVISIBLE);
            tv = (TextView) rootView.findViewById(R.id.dateText);
            tv.setVisibility(View.INVISIBLE);
            tv = (TextView) rootView.findViewById(R.id.overviewText);
            tv.setVisibility(View.INVISIBLE);
            tv = (TextView) rootView.findViewById(R.id.reviewBt);
            tv.setVisibility(View.INVISIBLE);
            tv = (TextView) rootView.findViewById(R.id.trailerTv);
            tv.setVisibility(View.INVISIBLE);
            ImageView iv = (ImageView) rootView.findViewById(R.id.postView);
            iv.setVisibility(View.INVISIBLE);
            Button fbt  = (Button) rootView.findViewById(R.id.button5);
            fbt.setVisibility(View.INVISIBLE);
            ListView lv = (ListView) rootView.findViewById(R.id.listView);
            lv.setVisibility(View.INVISIBLE);


            return  rootView;
        }


        TextView tv = (TextView) rootView.findViewById(R.id.titleText);


        tv.setText(my_movie.title);
        tv = (TextView) rootView.findViewById(R.id.dateText);


        tv.setText(my_movie.date);
        tv = (TextView) rootView.findViewById(R.id.overviewText);
        tv.setText(my_movie.overview);

        ListView lv = (ListView) rootView.findViewById(R.id.listView);

        lv.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });



        if(my_movie.reviews!=null && my_movie.reviews.length != 0) {
            lv.setAdapter(new ReviewAdaptor((Activity) getContext(), my_movie));
        }
        else{
            tv = (TextView) rootView.findViewById(R.id.reviewBt);
            tv.setVisibility(View.INVISIBLE);
          }
        setListViewHeightBasedOnChildren(lv);


        ListView lv2 = (ListView) rootView.findViewById(R.id.trailerList);

        lv2.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


        if(my_movie.trailers!=null && my_movie.trailers.length != 0) {
            lv2.setAdapter(new TrailerAdaptor((Activity) getContext(), my_movie));
        }
        else{
            tv = (TextView) rootView.findViewById(R.id.trailerTv);
            tv.setVisibility(View.INVISIBLE);
        }

        setListViewHeightBasedOnChildren(lv2);

        ImageView iv = (ImageView) rootView.findViewById(R.id.postView);
        //Loading image from below url into imageView
        Picasso.with((Activity)getContext())
                .load(my_movie.Image)
                .resize(200,200)
                .into(iv);




        final Button fbt  = (Button) rootView.findViewById(R.id.button5);

        fbt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(!(dataStore.isFavorite(my_movie )))
                {
                    dataStore.SaveNewMovie(my_movie);
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

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
