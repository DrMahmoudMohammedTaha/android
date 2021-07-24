package com.aboahmed.timemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class searcher extends Activity {
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //we use this line to hide the project name form the program
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	          setContentView(R.layout.searcher);
	          
	          
	          Button backs = (Button ) findViewById( R.id.backs);
	          backs.setOnClickListener(new OnClickListener() {
	      		@Override
	      		public void onClick(View v) {
	      		
	      			
	      		searcher.this.finish();	
	      		
	      		
	      		}});
	        
	    }

}
