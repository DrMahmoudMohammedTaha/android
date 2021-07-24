package com.example.dell.movieapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by DELL on 14/9/2016.
 */
public class ReviewAdaptor extends BaseAdapter {


    movie mov;
    Activity act;
    Context context;
    private static LayoutInflater inflater = null;

    public ReviewAdaptor(Activity mainActivity, movie mov )  {
        // TODO Auto-generated constructor stub
        this.mov = mov;
        context= mainActivity;
        act = mainActivity;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mov.reviews.length;
    }

    @Override
    public Object getItem(int i) {
        // TODO Auto-generated method stub
        return i;
    }

    @Override
    public long getItemId(int i) {
        // TODO Auto-generated method stub
        return i;
    }

    public class Holder
    {
        TextView name;
        TextView comment;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.review, null);

        holder.name =(TextView) rowView.findViewById(R.id.textView6);
        holder.comment =(TextView) rowView.findViewById(R.id.textView8);

        holder.name.setText(mov.reviews[i].auther);
        holder.comment.setText(mov.reviews[i].content);

        return rowView;

    }
}
