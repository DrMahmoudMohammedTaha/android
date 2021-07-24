package com.example.dell.benahapp.safe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dell.benahapp.R;

import java.io.Serializable;

public class safeMain extends AppCompatActivity implements Serializable{

    String[] items = {"Emergency evacuation", "Fire-Smoke", "Personal injury", "Personal threat",
            "Bomb threat", "Suspicious mail or package", "Radioisotope or biohazard spill", "People with specific needs"};

    private void intentMaker(String s){
        Intent intent = new Intent(this, safeWebView.class);
        intent.putExtra("s",s);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safe_main);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.safe_htmls, items);

        ListView listView = (ListView) findViewById(R.id.listVew);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {

                intentMaker(items[position]);
            }
        });



    }
}
