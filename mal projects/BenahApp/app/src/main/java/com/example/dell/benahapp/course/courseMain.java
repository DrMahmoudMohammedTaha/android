package com.example.dell.benahapp.course;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dell.benahapp.R;
import com.example.dell.benahapp.font_factory;

public class courseMain extends AppCompatActivity {

    

    public void courses_data() {

     Button b1;
     Button b2;
     Button b3;
     Button b4;
     Button b5;
     Button b6;
     Button b7;
     Button b8;
    
                b1 = (Button) findViewById(R.id.button11);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse("http://www.feng.bu.edu.eg/feng/index.php/mathematical-and-physical-engineering-academic-programs-and-courses"));
                        startActivity(intent);

                    }


                });




        b2 = (Button) findViewById(R.id.button22);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.feng.bu.edu.eg/feng/index.php/civil-academic-programs-and-courses"));
                startActivity(intent);

            }


        });
        b3 = (Button) findViewById(R.id.button33);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.feng.bu.edu.eg/feng/index.php/architectural-academic-programs-and-courses"));
                startActivity(intent);

            }


        });
        b4 = (Button) findViewById(R.id.button44);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.feng.bu.edu.eg/feng/index.php/survey-sciences-academic-programs-and-courses"));
                startActivity(intent);

            }


        });
        b5 = (Button) findViewById(R.id.button55);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.feng.bu.edu.eg/feng/index.php/electrical-academic-programs-and-courses"));
                startActivity(intent);

            }


        });
        b6 = (Button) findViewById(R.id.button66);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.feng.bu.edu.eg/feng/index.php/mechanical-academic-programs-and-courses"));
                startActivity(intent);

            }


        });


        font_factory.stylish_it1((TextView)findViewById(R.id.textView2),this);

            }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_main);
        courses_data();

    }

}

