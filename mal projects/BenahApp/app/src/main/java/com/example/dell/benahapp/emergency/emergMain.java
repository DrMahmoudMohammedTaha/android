package com.example.dell.benahapp.emergency;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dell.benahapp.R;
import com.example.dell.benahapp.font_factory;

public class emergMain extends AppCompatActivity {

    String[] items = {"Ambulance (123)", "Police (122)", "Fire Fighting (180)", "Electricity Emergency (121)",
            "Gas Emergency (129)" , "Tourist police (126)" , "Traffic police (128)" ,
            "emergency (129)" , "Mobile phones (112)"

    };

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emerg_main);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.emerg_list_view, items);

        ListView listView = (ListView) findViewById(R.id.emerg_listVew);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {

                String phone =  items[position].split("\\(")[1].replaceAll("\\)","");

                dialContactPhone(phone);

            }
        });

        font_factory.stylish_it1((TextView)findViewById(R.id.textView3),this);
    }
}
