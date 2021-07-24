package com.example.dell.movieapp;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

public class GridAdaptor extends BaseAdapter{
    movie[] movies;
    Context context;
    MainActivity act;
    private static LayoutInflater inflater = null;
    public GridAdaptor(MainActivity mainActivity, movie[] posts  )  {
        // TODO Auto-generated constructor stub
        this.movies = posts;
        context= mainActivity;
        act = mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return movies.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        ImageView movieImg;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.grid_item, null);

        holder.movieImg =(ImageView) rowView.findViewById(R.id.imageView2);


        //Loading image from below url into imageView
        Picasso.with(context)
                .load(movies[position].Image)
                .resize(200 , 300)
                .into(holder.movieImg);



        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new trailerReviewAsync(act , movies[position]).execute();
            }
        });
        if (MainActivity.twoPane)
        {
            act.displayMovie(movies[0]);
        }
        return rowView;
    }

}