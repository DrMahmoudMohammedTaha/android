package com.example.dell.benahapp.attend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dell.benahapp.R;
import com.example.dell.benahapp.font_factory;

/**
 * Created by DELL on 13/2/2017.
 */
public class pluserActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.pluser_layout);

        font_factory.stylish_it1((TextView)findViewById(R.id.textView),this);

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new plusrismAdapter(this));
    }
        @Override
        public void onResume() {
            super.onResume();
        }



        @Override
        protected void onPause() {
            super.onPause();
        }

    @Override
    protected void onStop() {
        attendance_servant.save_data();
        data_factory.saveLast(data_factory.getLastData());
        Log.v("xtest","pluser stop");
        super.onStop();
    }


}




