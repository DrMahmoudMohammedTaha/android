package com.aboahmed.timemanager;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class deleteall extends Activity {
	
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //we use this line to hide the project name form the program
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	          setContentView(R.layout.deleteall);
	          
	          Button saver = (Button) findViewById(R.id.delete_editdelete);
	          Button deleter = (Button) findViewById(R.id.button3);   
	          Button today = (Button) findViewById(R.id.edit_editdelete);
	          
	         
	    	    saver.setOnClickListener(new OnClickListener() {
	    				@Override
	    				public void onClick(View v) {
	    			
	    			Toast.makeText(getBaseContext(), "All data saved!", Toast.LENGTH_LONG).show();	
	    					deleteall.this.finish();
	    				}});
	    	
	
	    	    
		      	  
	    	    deleter.setOnClickListener(new OnClickListener() {
	    				@Override
	    				public void onClick(View v) {
	    			
	    			Toast.makeText(getBaseContext(), "All data have been deleted!", Toast.LENGTH_LONG).show();	
	    					deleteall.this.finish();
	    				}});
	    	  
	
	    	    
	    	    
		      	  
	    	    today.setOnClickListener(new OnClickListener() {
	    				@Override
	    					public void onClick(View v) {
	    					
	    					Intent i = new Intent(deleteall.this, MainActivity.class);
	    					startActivity(i);
	    					
	    				}});
	
	    	    
	    }


}
