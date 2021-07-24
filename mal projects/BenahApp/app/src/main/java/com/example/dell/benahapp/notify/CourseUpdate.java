package com.example.dell.benahapp.notify;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.benahapp.R;

public class CourseUpdate extends AppCompatActivity {
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify_activity_course_update);

        Bundle b=getIntent().getExtras();

        if(b !=null)
        {
            type=b.getString("type");

        }
    }
}
