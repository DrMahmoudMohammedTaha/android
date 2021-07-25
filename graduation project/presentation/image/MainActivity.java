package com.example.el_ahram.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ListView mSearchNFilterLv;

    private EditText mSearchEdt;

    private ArrayList<String> mStringList;

    private Main2Activity valueAdapter;

    private TextWatcher mSearchTw;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initUI();

        initData();

        valueAdapter=new Main2Activity(mStringList,this);

        mSearchNFilterLv.setAdapter((ListAdapter) valueAdapter);

        mSearchEdt.addTextChangedListener(mSearchTw);


    }
    private void initData() {

        mStringList=new ArrayList<String>();

        mStringList.add("Time Tabel");

        mStringList.add("Attendance");

        mStringList.add("News");

        mStringList.add("Courses");

        mStringList.add("Maps");

        mStringList.add("Notifications");

        mStringList.add("Exam results");

        mStringList.add("Portal");

        mStringList.add("EKB");

        mStringList.add("Liberary");

        mStringList.add("Office365");

        mStringList.add("Gallary");

        mStringList.add("Staff");

        

        mSearchTw=new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                valueAdapter.getFilter().filter(s);
            }



            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

    }

    private void initUI() {

        mSearchNFilterLv=(ListView) findViewById(R.id.list_view);

        mSearchEdt=(EditText) findViewById(R.id.txt_search);
    }

}