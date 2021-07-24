package com.example.dell.benahapp.Major;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.benahapp.R;


public class MainPagefrag extends Fragment {
 View v;

    public MainPagefrag() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       v= inflater.inflate(R.layout.fragment_main_pagefrag, container, false);

        return v;
    }


}
