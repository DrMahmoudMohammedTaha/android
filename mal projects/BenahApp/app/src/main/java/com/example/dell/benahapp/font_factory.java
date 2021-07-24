package com.example.dell.benahapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Created by DELL on 28/6/2017.
 */
public class font_factory {


    public static void stylish_it1(TextView tv, Activity app) {

        Typeface face = Typeface.createFromAsset(app.getAssets(), "fonts/Snickles.ttf");
        tv.setTypeface(face);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, app.getResources().getDimension(R.dimen.text_mid));



    }

    public static void stylish_it2(TextView tv, Activity app) {

        Typeface face = Typeface.createFromAsset(app.getAssets(), "fonts/NovaRound.ttf");
        tv.setTypeface(face);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,  app.getResources().getDimension(R.dimen.text_mid));

    }

    public static void stylish_it1(Activity app, TextView... tv) {

        Typeface face = Typeface.createFromAsset(app.getAssets(), "fonts/Snickles.ttf");
        for (int i = 0; i < tv.length; i++) {
            tv[i].setTypeface(face);
            tv[i].setTextSize(TypedValue.COMPLEX_UNIT_SP,  app.getResources().getDimension(R.dimen.text_mid));

        }

    }

    public static void stylish_it2(Activity app, TextView... tv) {

        Typeface face = Typeface.createFromAsset(app.getAssets(), "fonts/NovaRound.ttf");
        for (int i = 0; i < tv.length; i++) {
            tv[i].setTypeface(face);
            tv[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, app.getResources().getDimension(R.dimen.text_mid));

        }

    }
}
