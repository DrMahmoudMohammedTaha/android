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

public class profile_show extends Activity {
	
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //we use this line to hide the project name form the program
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	          setContentView(R.layout.profile_show);
	          
	        
	          //////////////////////////
	          RelativeLayout container =  (RelativeLayout) findViewById(R.id.container_prrofile);          
	          //container onlongclick listener
	          container.setOnLongClickListener(new OnLongClickListener() {
	  			@Override
	  			public boolean onLongClick(View v) {
	  			Intent i = new Intent();
	  			i.setAction("android.intent.action.deleteall");
	  			startActivity( i);
	  			return false;
	  			}});
	      ////////////////////////////////
	          
	          
	          Button searchp = (Button ) findViewById( R.id.searchp);
	          searchp.setOnClickListener(new OnClickListener() {
	      		@Override
	      		public void onClick(View v) {
	      		
	      			
	      		Intent i = new Intent();
	      		i.setAction("android.intent.action.searcher");
	      		startActivity( i);
	      			
	      		
	      		
	      		}});
	        
	          
	    }


}
