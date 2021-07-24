package com.example.dell.benahapp.maps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dell.benahapp.R;
import com.example.dell.benahapp.font_factory;


public class mapMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_main);

        font_factory.stylish_it1((TextView)findViewById(R.id.map_title),this);
    }

    public void showBranch(View view){
        View v = findViewById(R.id.branch) ;
        v.setVisibility(v.VISIBLE);
    }

    public void k(View view){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("geo:30.0907292,31.2553186?q="+"Shoubra Faculty of Engineering - Elkhalafawy building"));
        startActivity(i);

    }
    public void r(View view){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("geo:0,0?q="+"كلية هندسة شبرا - روض الفرج"));
        startActivity(i);
    }

    public void openMaps(View view){

        Button b = (Button)view;
        String buttonText = b.getText().toString();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("geo:0,0?q="+buttonText+" Benha University"));
        startActivity(i);

    }

}
