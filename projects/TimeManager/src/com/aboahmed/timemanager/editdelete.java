package com.aboahmed.timemanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class editdelete extends Activity {
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //we use this line to hide the project name form the program
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	         setContentView(R.layout.editdelete);
	          
	          
	          
	    }


}
