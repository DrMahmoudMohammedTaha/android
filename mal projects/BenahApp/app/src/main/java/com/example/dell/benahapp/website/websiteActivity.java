package com.example.dell.benahapp.website;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dell.benahapp.R;
import com.example.dell.benahapp.font_factory;

import java.util.ArrayList;

public class websiteActivity extends AppCompatActivity {

    public static AppCompatActivity app ;
    public static ArrayList<WEBS> data = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.websites);
            app = this;
            fill_webs();
            fill_listeners();

            font_factory.stylish_it1((TextView) findViewById(R.id.textView5),this);

        }


    public static void fill_webs()
    {
        data.add(new WEBS("UDACITY","https://www.udacity.com", (Button) app.findViewById(R.id.button_)));
        data.add(new WEBS("وزارة التعليم العالى","http://portal.mohesr.gov.eg/ar-eg/Pages/default.aspx", (Button) app.findViewById(R.id.button2_)));
        data.add(new WEBS("EDX","https://www.edx.org/", (Button) app.findViewById(R.id.button3_)));
        data.add(new WEBS("COURSERA","https://www.coursera.org/", (Button) app.findViewById(R.id.button4_)));
        data.add(new WEBS("KHAN ACADEMY","https://www.khanacademy.org/", (Button) app.findViewById(R.id.button6_)));
        data.add(new WEBS("TED","https://www.ted.com/", (Button) app.findViewById(R.id.button7_)));
        data.add(new WEBS("LYNDA","https://www.lynda.com/", (Button) app.findViewById(R.id.button8_)));

    }

    public static void fill_listeners()
    {

        for (int i = 0; i < data.size(); i++) {
            final int index = i;
            data.get(index).target.setText(data.get(index).name);
            data.get(index).target.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent x=new Intent(Intent.ACTION_VIEW, Uri.parse(data.get(index).URL));
                    app.startActivity(x);
                }
            });
            font_factory.stylish_it2(data.get(index).target,app);
        }

    }

}
