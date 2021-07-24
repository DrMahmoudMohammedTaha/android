package com.aboahmed.timemanager;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity {
	
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    //	RelativeLayout container =  (RelativeLayout) findViewById(R.id.container_main);
       
    	super.onCreate(savedInstanceState);
        //we use this line to hide the project name form the program
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
       
        //you must add component after requestwindowfeature function
        Button next = (Button) findViewById(R.id.next);
        TextView date = (TextView) findViewById(R.id.datestart);
        RelativeLayout container = (RelativeLayout) findViewById(R.id.container_main);
        
        
        date.setText(new SimpleDateFormat("yyyy/MM/dd  EE.").format(new Date(0)));
         // ScrollView s = (ScrollView) findViewById(R.id.scrollView1);

        
        //next onclick listener
        next.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
		Intent i = new Intent();
		i.setAction("android.intent.action.center");
		startActivity( i);
			}});
       
        
        
        //container onlongclick listener
        container.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
			Intent i = new Intent();
			i.setAction("android.intent.action.deleteall");
			startActivity( i);
			return false;
			}});
        
        
    }

    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
