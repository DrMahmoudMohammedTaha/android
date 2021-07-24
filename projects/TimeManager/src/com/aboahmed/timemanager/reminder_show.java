package com.aboahmed.timemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class reminder_show extends Activity {
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //we use this line to hide the project name form the program
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	          setContentView(R.layout.reminder_show);
	          
	          RelativeLayout container =  (RelativeLayout) findViewById(R.id.container_reminder);           
	          //container onlongclick listener
	          container.setOnLongClickListener(new OnLongClickListener() {
	  			@Override
	  			public boolean onLongClick(View v) {
	  			Intent i = new Intent();
	  			i.setAction("android.intent.action.deleteall");
	  			startActivity( i);
	  			return false;
	  			}});
	      
	          
	          Button searchr = (Button ) findViewById( R.id.searchr);
	          searchr.setOnClickListener(new OnClickListener() {
	      		@Override
	      		public void onClick(View v) {
	      		
	      			
	      		Intent i = new Intent();
	      		i.setAction("android.intent.action.searcher");
	      		startActivity( i);
	      			
	      		
	      		
	      		}});
	        
	          
	          
	    }

	
}
