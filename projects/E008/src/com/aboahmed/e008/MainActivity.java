package com.aboahmed.e008;


import com.aboahmed.e008.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(getBaseContext(),"onpause1", Toast.LENGTH_LONG).show();
   
        Button browse = (Button ) findViewById(R.id.goto2);
        browse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//this constractor here takes the action name
				Intent i = new Intent("android.intent.action.second");
			//define catagory
			i.addCategory("android.intent.category.DEFAULT");
			
			// additional steps to send data between activities
			EditText x = (EditText) findViewById(R.id.editText1);
			i.putExtra("name", x.getText());
			x = (EditText) findViewById(R.id.editText2);
			i.putExtra("phone", x.getText());
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
    
        browse = (Button ) findViewById(R.id.intent);
        browse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Button x = (Button) findViewById(R.id.goto2);
			 	
			 //	  x.setBackgroundColor(Integer.parseInt(MainActivity.this.getIntent().getExtras().getCharSequence("name").toString()));
				//  x.setText(MainActivity.this.getIntent().getExtras().getCharSequence("name").toString());
			 x.setBackgroundColor(0);
		
			}
			
        
        });
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



    protected void onPause()
    {
 	   
 	   super.onPause();
 	  
 	 //  Toast.makeText(getBaseContext(),"onpause", Toast.LENGTH_LONG).show();
 	  
    }

    
    protected void onStop()
    {
 	   
 	   super.onStop();
 	  
 	   //Toast.makeText(getBaseContext(),"onStop", Toast.LENGTH_LONG).show();
 	  
    }
    
    protected void onDestroy()
    {
 	   
 	   super.onDestroy();
 	  
 	   //Toast.makeText(getBaseContext(),"onDestroy", Toast.LENGTH_LONG).show();
 	  
    }
    
    protected void onStart()
    {
 	   
 	   super.onStart();
 	  
 	 //  Toast.makeText(getBaseContext(),"onStart", Toast.LENGTH_LONG).show();
 	  
    }
    
    protected void onRestart()
    {
 	   
 	   super.onRestart();
 	//  Toast.makeText(getBaseContext(),"onRestart", Toast.LENGTH_LONG).show(); 	  
 	   
    }
    protected void onResume()
    {
 	   
 	   super.onResume();
 	  
 	 //  Toast.makeText(getBaseContext(),"onResume", Toast.LENGTH_LONG).show();
 	  
    }





}
