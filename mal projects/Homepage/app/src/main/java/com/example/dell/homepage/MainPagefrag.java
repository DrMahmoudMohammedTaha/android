package com.example.dell.homepage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class MainPagefrag extends Fragment {
 View v;


    public MainPagefrag() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       v= inflater.inflate(R.layout.fragment_main_pagefrag, container, false);


        TextView t1=(TextView)v.findViewById(R.id.facultyname);
        //t1.setTypeface(MainActivity.face);
        t1.setText("Shoubra faculty Engineering ");



        ImageView facimg=(ImageView)v.findViewById(R.id.facultyimg);
        facimg.setImageResource(R.mipmap.benha);

        TextView t2=(TextView)v.findViewById(R.id.visiontex);
t2.setText("Shoubra faculty Engineering Benha University is looking forward to be " +
        "pilot college in the fields of Engineering Education and Scientific Research" +
        "at the regional and international levels and to community service.");

        TextView t3=(TextView)v.findViewById(R.id.mssgtex);

        t3.setText("Shoubra faculty Engineering is commited to prepare" +
            "graduates with the knowledge and skills that qualify them to compete in the labor market," +
                " is also committed to the production of scientific research as a privileged location at" +
                " the international level and community services, and within the framework of the human and ethical values.");

        TextView t4=(TextView)v.findViewById(R.id.goalstex);

t4.setText("To achieve the vision and the message of the college to be in line with the strategic objectives " +
        "of the university,we aim to raise the level of quality of education and learning " +
        "and work to provide educational service distinguished. " +
        "Upgrading system higher studies and scientific research. " +
        "The attention of service to the community and the development of the surrounding environment");
        return v;
    }


}
