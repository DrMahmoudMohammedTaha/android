package com.example.dell.movieapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by DELL on 16/9/2016.
 */
public class main_fragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    TextView title;
    GridView gv;
    Button pri , cat , fav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        title = (TextView) rootView.findViewById(R.id.textViewMain);
        gv= (GridView) rootView.findViewById(R.id.gridview);
        pri = (Button) rootView.findViewById(R.id.button3);
        cat = (Button) rootView.findViewById(R.id.button4);
        fav = (Button) rootView.findViewById(R.id.button2);


        new AsyncMovies(getContext() , gv, (MainActivity) getContext() , dataStore.getSavedSelection()).execute();




        pri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataStore.saveSelection(true , getContext());
                new AsyncMovies(getContext() , gv, (MainActivity) getContext() , true).execute();
            }
        });

        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dataStore.saveSelection(false , getContext());
                new AsyncMovies(getContext() , gv, (MainActivity) getContext() , false).execute();

            }
        });


        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                movie m [] = dataStore.getSavedFavorite();
                if(m != null){
                    gv.setAdapter(new GridAdaptor((MainActivity) getContext(),m));
                    Toast.makeText((MainActivity) getContext() , "favorits loaded!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText( (MainActivity) getContext() , "no favourits found!", Toast.LENGTH_LONG).show();
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
