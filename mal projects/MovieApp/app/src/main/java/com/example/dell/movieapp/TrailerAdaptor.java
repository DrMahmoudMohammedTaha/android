package com.example.dell.movieapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by DELL on 21/9/2016.
 */
public class TrailerAdaptor  extends BaseAdapter {


    movie mov;
    Activity act;
    Context context;
    private static LayoutInflater inflater = null;

    public TrailerAdaptor(Activity mainActivity, movie mov )  {
        // TODO Auto-generated constructor stub
        this.mov = mov;

        context= mainActivity;
        act = mainActivity;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        if (mov.trailers == null)
            return 0;
        return mov.trailers.length;
    }

    @Override
    public Object getItem(int i) {
        return  i;

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class Holder
    {
        TextView name;
    }


    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.trailer_item, null);


        holder.name =(TextView) rowView.findViewById(R.id.trailerText);

        holder.name.setText("Trailer " +   (i+1));
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!mov.trailers[i].equals("")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mov.trailers[i]));
                    act.startActivity(intent);
                }

            }
        });

        return rowView;
    }
}
