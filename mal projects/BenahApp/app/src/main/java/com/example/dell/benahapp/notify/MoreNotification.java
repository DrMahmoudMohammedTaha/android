package com.example.dell.benahapp.notify;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dell.benahapp.R;

import java.util.ArrayList;

public class MoreNotification extends AppCompatActivity {
ArrayList<String> arr;
    ArrayList<String> arr1;
    TextView t1;
    String course,selectedItem,fileurl="";
    Button clear;
    boolean b2=false;
    static ArrayAdapter<String> adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify_activity_more_notification);
        arr1 = new ArrayList<String>();
        Bundle b = getIntent().getExtras();

        if (b != null) {
            arr = b.getStringArrayList("list");
            course = b.getString("coursename");
        }
        clear = (Button) findViewById(R.id.cl);
        t1 = (TextView) findViewById(R.id.course);

        for (int i = arr.size() - 1; i >= 0; i--) {
            arr1.add(arr.get(i));
        }
        final ListView list = (ListView) findViewById(R.id.listviewA);
        //arr=new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arr1);

        t1.setText(course);
        list.setAdapter(adapter);

        clear.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arr1.clear();
                        list.setAdapter(adapter);

                    }
                });


   /*
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id){

selectedItem=list.getSelectedItem().toString();

             int x= selectedItem.indexOf('>');
                for (int i=x+1;i<selectedItem.length();i++)
                {

                    fileurl+=selectedItem.charAt(i);
                }
                Toast.makeText(MoreNotification.this,fileurl,Toast.LENGTH_LONG).show();
              //  new LoginData().setdownloadfile(fileurl);

                //new DownloadFile().execute(new String[] {"http://192.168.1.3:8080/WebserverUniversity2/dowonloadFile", fileurl});
            }
        });
*/




    }
}
