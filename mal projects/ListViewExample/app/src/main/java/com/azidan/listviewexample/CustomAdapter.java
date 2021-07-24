package com.azidan.listviewexample;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class CustomAdapter extends BaseAdapter{
    post[] posts;
    Context context;
    private static LayoutInflater inflater = null;
    public CustomAdapter(MainActivity mainActivity,post [] posts )  {
        // TODO Auto-generated constructor stub

        this.posts = posts;
        context= mainActivity;

        Log.i("wwww1",this.posts[2].postImage);
        Log.i("wwww1",this.posts[5].postImage);

        Log.i("wwww2",this.posts[2].name);
        Log.i("wwww2",this.posts[5].name);


        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return posts.length;
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
        TextView name;
        TextView time;
        TextView text;
        TextView notification;
        ImageView postImg;
        ImageView userImg;
        Button like;
        Button comment;
        Button share;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.list_item, null);
        holder.name=(TextView) rowView.findViewById(R.id.textView);
        holder.time=(TextView) rowView.findViewById(R.id.textView2);
        holder.text=(TextView) rowView.findViewById(R.id.textView3);
        holder.notification=(TextView) rowView.findViewById(R.id.textView4);

        holder.userImg=(ImageView) rowView.findViewById(R.id.imageView2);
        holder.postImg=(ImageView) rowView.findViewById(R.id.imageView);


        holder.name.setText(posts[position].name);
        holder.time.setText(posts[position].time);
        holder.text.setText(posts[position].text);
        holder.notification.setText(posts[position].notification);


        //Loading image from below url into imageView
        Picasso.with(context)
                .load(posts[position].userImage)
                .resize(40, 40)                        // optional
                .into(holder.userImg);

        //Loading image from below url into imageView
        Picasso.with(context)
                .load(posts[position].postImage)
                .resize(190, 190)                        // optional
                .into(holder.postImg);


        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Toast.makeText(context, "You Clicked "+ posts[position].name, Toast.LENGTH_LONG).show();
            }
        });

        return rowView;
    }

}