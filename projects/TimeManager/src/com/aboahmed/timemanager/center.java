package com.aboahmed.timemanager;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class center extends Activity {
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //we use this line to hide the project name form the program
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	          setContentView(R.layout.center);
	      	
	          
	          
	          RelativeLayout container =  (RelativeLayout) findViewById(R.id.container_center);
	          //container onlongclick listener
	          container.setOnLongClickListener(new OnLongClickListener() {
	  			@Override
	  			public boolean onLongClick(View v) {
	  			Intent i = new Intent();
	  			i.setAction("android.intent.action.deleteall");
	  			startActivity( i);
	  			return false;
	  			}});
	      
	          
	          TextView date = (TextView) findViewById(R.id.date);
	          date.setText(new SimpleDateFormat("yyyy/MM/dd  EE.").format(new Date(0)));
	             
	
	          Button search = (Button ) findViewById( R.id.search);
	          search.setOnClickListener(new OnClickListener() {
	      		@Override
	      		public void onClick(View v) {
	      		
	      			
	      		Intent i = new Intent();
	      		i.setAction("android.intent.action.searcher");
	      		startActivity( i);
	      			
	      		
	      		
	      		}});
	             
	          
	          
	          
	    }


}
