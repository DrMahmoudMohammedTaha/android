package com.aboahmed.e007;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;



public class demo extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
        //this function returns the intent that invokes this activity
        Intent i = this.getIntent();
        //now get data from the intent
        Toast.makeText(this,i.getExtras().getString("name").toString(), Toast.LENGTH_LONG).show();
	
	}
	


									}
	
	

