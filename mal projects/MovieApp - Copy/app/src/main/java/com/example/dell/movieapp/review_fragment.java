package com.example.dell.movieapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by DELL on 16/9/2016.
 */
public class review_fragment extends Fragment implements LoaderManager.LoaderCallbacks <Cursor> {



    movie my_movie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_review, container, false);
        Intent intent = ((Activity) getContext()).getIntent();
        my_movie = (movie) intent.getExtras().getSerializable("movie");
        TextView tv = (TextView) rootView.findViewById(R.id.textView2);
        tv.setText(my_movie.title + " REVIEWS...");
        ListView lv = (ListView) rootView.findViewById(R.id.listView);

        lv.setAdapter(new ReviewAdaptor((Activity) getContext(),my_movie));

        return  rootView;
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
