package com.aboahmed.e008;

import com.aboahmed.e008.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class second extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        //Toast.makeText(getBaseContext(),"oncreate1", Toast.LENGTH_LONG).show();
        
        TextView x = (TextView ) findViewById(R.id.welcome);
       String waw ="Your name is: " + this.getIntent().getExtras().getCharSequence("name").toString() + "\n" + "Your phone numer is: "+ this.getIntent().getExtras().getCharSequence("phone").toString();
       
       
       x.setText(waw);
        Button browse = (Button ) findViewById(R.id.setting);
        browse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent i = new Intent(second.this, MainActivity.class);
			// additional steps to send data between activities
			i.putExtra("name", "16720627");
		
			
			startActivity(i);
			
		
			//		to open call
			//`		Intent i = new Intent(Intent.ACTION_DAIL);
			//		i.setData(Uri.parse("tel: 01144010476"));
			///////////////////////////////////////////////////////////////////////////////////////
			//		to open a web page
			//		Intent i = new Intent(Intent.ACTION_VIEW);
			//		i.setData(Uri.parse("http:// www.facebook.com"));
						
			}
		});
    
	
	}

    protected void onPause()
    {
 	   
 	   super.onPause();
 	  
 	   //Toast.makeText(getBaseContext(),"onpause1", Toast.LENGTH_LONG).show();
 	  
    }

    
    protected void onStop()
    {
 	   
 	   super.onStop();
 	  
 	   //Toast.makeText(getBaseContext(),"onStop1", Toast.LENGTH_LONG).show();
 	  
    }
    
    protected void onDestroy()
    {
 	   
 	   super.onDestroy();
 	  
 	 //  Toast.makeText(getBaseContext(),"onDestroy1", Toast.LENGTH_LONG).show();
 	  
    }
    
    protected void onStart()
    {
 	   
 	   super.onStart();
 	  
 	   //Toast.makeText(getBaseContext(),"onStart1", Toast.LENGTH_LONG).show();
 	  
    }
    
    protected void onRestart()
    {
 	   
 	   super.onRestart();
 	  
 	   //Toast.makeText(getBaseContext(),"onreStart1", Toast.LENGTH_LONG).show();
 	  
    }
    protected void onResume()
    {
 	   
 	   super.onResume();
 	  
 	   //Toast.makeText(getBaseContext(),"onResume1", Toast.LENGTH_LONG).show();
 	  
    }


}
