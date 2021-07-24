package com.example.dell.benahapp.attend;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.benahapp.R;

public class plusrismAdapter  extends BaseAdapter {


    Activity act;
    Context context;
    private static LayoutInflater inflater = null;

    public plusrismAdapter(Activity mainActivity )  {
        // TODO Auto-generated constructor stub

        context= mainActivity;
        act = mainActivity;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data_factory.pluserismData.size();
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

        final Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.pluser_list_item, null);


        holder.name =(TextView) rowView.findViewById(R.id.pluserText);

        holder.name.setText(data_factory.pluserismData.get(i));
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            attendance_servant.removeAttend(data_factory.pluserismData.get(i));
                data_factory.pluserismData.remove(i);

                holder.name.setClickable(false);
                holder.name.setBackgroundColor(Color.RED);

                Log.v("xtest","I am there");

            }
        });

        return rowView;
    }


}