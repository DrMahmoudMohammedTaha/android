package com.aboahmed.e007;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button browse = (Button ) findViewById(R.id.browse);
        browse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent i = new Intent(MainActivity.this, demo.class);
			// additional steps to send data between activities
			TextView texter = (TextView) findViewById(R.id.texter);
			i.putExtra("name", texter.getText());
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
}
