package com.example.zekra3;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;


public class MainActivity extends Activity {

	
	 static Timer clock = new Timer();
	 static boolean direction = true;
	 
	
	
	
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		  
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
	
	  Button exit = (Button) findViewById(R.id.exit);
	   exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				
				System.exit(0);
				
			}
		});
	   
	   
	   
	   
	   
	   Button show = (Button) findViewById(R.id.show);
	   show.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				

				direction = true;
				Intent i = new Intent (MainActivity.this,Hdith.class);
				startActivity (i);

				
			}
		});
	   
	   Button menu = (Button) findViewById(R.id.menu);
	   menu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
	
				direction = false; 
				Intent i = new Intent (MainActivity.this,Hdith.class);
				startActivity (i);
	
			}
		});
	   
	   
	   final RadioButton r15 = (RadioButton) findViewById(R.id.radioButton1);
	   final RadioButton r30 = (RadioButton) findViewById(R.id.radioButton2);
	   final RadioButton r60 = (RadioButton) findViewById(R.id.radioButton3);
	   
	   

	   Button time = (Button) findViewById(R.id.time);
	   time.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				long x = 0;
				if(r15.isChecked())
					x = 15;
				else if (r30.isChecked())
					x = 30;
				else x = 60 ;
				
				startTimer(x);
			}
		});
	   
	   
	 OnClickListener radio = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(r15.equals((RadioButton)  v))
			{
				r30.setChecked(false);
				r60.setChecked(false);
			}
			else  if(r30.equals((RadioButton)  v))
			{
				r15.setChecked(false);
				r60.setChecked(false);
			}
			else
			{
				r15.setChecked(false);
				r30.setChecked(false);
		    }
			
		}
	};   
	   
	   r15.setOnClickListener(radio);
	   r30.setOnClickListener(radio);
	   r60.setOnClickListener(radio);
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




    public  void startTimer(long period) {
        clock.schedule(new TimerTask() {
			@Override
			public void run() {

				direction = true;
				Intent i = new Intent (MainActivity.this,Hdith.class);
  				startActivity (i);
			}
		}, 0 , period * 60 * 1000);
    }

   




}
