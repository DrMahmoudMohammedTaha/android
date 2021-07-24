package com.example.dalelzakah;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        
    	// Has previous state been saved?
		if (savedInstanceState != null) {

		}else{
	        try {
				new filier().readData(MainActivity.this);
			} catch (IOException e) {
				Toast.makeText (getBaseContext (),"error Loading data!", Toast.LENGTH_LONG ).show();
			}			
		}
	
        Button showNames = (Button) findViewById(R.id.showPoor);
        
        showNames.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent lister = new Intent(MainActivity.this, Lister.class);
			lister.putExtra("state", Lister.SHOW_ALL_NAMES);
			lister.putExtra("content", packagePoor.getThePoorNames());
			startActivity(lister);
				
			}
		});
        
        Button showGiven = (Button) findViewById(R.id.showGiven);
        
        showGiven.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent lister = new Intent(MainActivity.this, Lister.class);
				lister.putExtra("state", Lister.SHOW_ALL_GIVENS);
				lister.putExtra("content", packagePoor.getGlobalGiven());
				startActivity(lister);
				}
		});
        Button searcher = (Button) findViewById(R.id.searchBtn);
        
        searcher.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		
				EditText editor = (EditText) findViewById(R.id.searchEdit);
				Intent lister = new Intent(MainActivity.this, Lister.class);
				lister.putExtra("state", Lister.SHOW_SEARCH_NAMES);
				lister.putExtra("extra", editor.getText().toString());
				lister.putExtra("content", packagePoor.findName(editor.getText().toString()));
				startActivity(lister);
				}
		});
        Button addPeople = (Button) findViewById(R.id.addPeople);
        
        addPeople.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				for (int i = 0; i < 10; i++) {
			try {
				
						poor h = new poor("poor" + i);
						if (i % 2 == 0 ) 							
						h.addGiven("work  " );
						if (i % 3 == 0 ) 
						h.addGiven("zabah");
						packagePoor.addPoor(h);
					} catch (CloneNotSupportedException e) {
						
					}
				}	
			
			}
		});
        
        Button addGiven = (Button) findViewById(R.id.addGiven);
        
        addGiven.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				packagePoor.addGlobalGiven("work  " );
				packagePoor.addGlobalGiven("zabah");
		
			for (int i = 0; i < 10; i++) {
				packagePoor.addGlobalGiven("given" + i);
			}	
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
        if (id == R.id.saver) {
         try {
			new filier().writeData(packagePoor.getThePoor(), packagePoor.getGlobalGiven(),MainActivity.this);
		} catch (FileNotFoundException e) {
			Toast.makeText (getBaseContext (), "error saving data!", Toast.LENGTH_LONG ).show();
		}
        	return true;
        }else if (id == R.id.deleteAll) {
        	//TODO add prompot dialoge
        	packagePoor.formatDatabase();
        	 try {
     			new filier().writeData(packagePoor.getThePoor(), packagePoor.getGlobalGiven(),MainActivity.this);
     		} catch (FileNotFoundException e) {
     			Toast.makeText (getBaseContext (), "error saving data!", Toast.LENGTH_LONG ).show();
     		}
        	return true;
        }else if (id == R.id.deleteEvents) {
           	//TODO add prompot dialoge
        	packagePoor.formatGivens();
        	 try {
     			new filier().writeData(packagePoor.getThePoor(), packagePoor.getGlobalGiven() , MainActivity.this);
     		} catch (FileNotFoundException e) {
     			Toast.makeText (getBaseContext (), "error saving data!", Toast.LENGTH_LONG ).show();
     		}
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
    

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {

		
		
	
		//savedInstanceState.putStringArrayList("Data", packagePoor.getThePoorNames());
		super.onSaveInstanceState(savedInstanceState);

		
	}


}
