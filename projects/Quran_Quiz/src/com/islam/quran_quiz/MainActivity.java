package com.islam.quran_quiz;
import java.util.ArrayList;
import java.util.Random;
import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ListActivity {

	ArrayAdapter<String> adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

 
        
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
	    setListAdapter(adapter);
	     Quran_Data.name.clear();
	     Quran_Data.content.clear();
	     Quran_Data.mtshbh.clear();
        
        
        try {
			Quran_Data.intial(getResources());
		} catch (Exception e) {
			Toast.makeText (getBaseContext (), "error loading data", Toast.LENGTH_LONG ).show();
		}
        Button next = (Button ) findViewById(R.id.next);
       RadioButton chk1 = (RadioButton) findViewById(R.id.chk1);
       RadioButton chk2 = (RadioButton) findViewById(R.id.chk2);
     
       
       chk1.setChecked(true);
       chk1.setOnClickListener(new OnClickListener() {
      		
      		@Override
      		public void onClick(View v) {
      		       RadioButton chk2 = (RadioButton) findViewById(R.id.chk2);
      		    
      		       if(chk2.isChecked())
      		       chk2.setChecked(!chk2.isChecked());
      	    	
      			
      		}
      	});
       chk2.setOnClickListener(new OnClickListener() {
      		
      		@Override
      		public void onClick(View v) {
      			   RadioButton chk1 = (RadioButton) findViewById(R.id.chk1);
      			 if(chk1.isChecked())
      			   chk1.setChecked(!chk1.isChecked());
      	   	
      			
      		}
      	});
       next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				   RadioButton chk1 = (RadioButton) findViewById(R.id.chk1);
			       TextView ques =  (TextView) findViewById(R.id.ques);
			       TextView ansNum =  (TextView) findViewById(R.id.ansNum);
			       
			       
			       if (chk1.isChecked() && Quran_Data.target) {
                   adapter.addAll(Quran_Data.quesAns(ques , ansNum));
                    Quran_Data.target = false;
                } else if (chk1.isChecked() && !Quran_Data.target) {
                    ansNum.setText("");
                    adapter.clear();
                    Quran_Data.target = true;
                    Quran_Data.target_m=false;
                    Quran_Data.newQues(ques);
                } else if (Quran_Data.target_m) {
                    
                    adapter.addAll(Quran_Data.mtshbhAns(ansNum));
                    Quran_Data.target_m = false;
                } else {
                    ansNum.setText("");
                    adapter.clear();
                    Quran_Data.target_m = true;
                    Quran_Data.target = false;
                    Quran_Data.newMtshbh(ques);
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    

}