package com.english_quiz;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ListActivity {

	
	ArrayAdapter<String> adapter;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
     
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
	    setListAdapter(adapter);
	    Words_Data.clear();
	    Words_Data.english.clear();
	    Words_Data.arabic.clear();
	    try {
	    	
			Words_Data.intial(getResources());
		} catch (IOException e) {
			Toast.makeText (getBaseContext (), "error loading data!", Toast.LENGTH_LONG ).show();
		}
	     
		    
        
		    
        
        
        Button next = (Button) findViewById(R.id.next);
        Button end = (Button) findViewById(R.id.end);
        Button yes = (Button) findViewById(R.id.yes);
        Button no = (Button) findViewById(R.id.no);
        
        end.setVisibility(android.view.View.INVISIBLE);        
        yes.setVisibility(android.view.View.INVISIBLE);
        no.setVisibility(android.view.View.INVISIBLE);
       
        next.setOnClickListener(new OnClickListener() {
        	 Button next = (Button) findViewById(R.id.next);
        	 Button end = (Button) findViewById(R.id.end);
             Button yes = (Button) findViewById(R.id.yes);
             Button no = (Button) findViewById(R.id.no);
             TextView answer = (TextView) findViewById(R.id.answer);
			
            @Override
			public void onClick(View v) {
			if(end.getVisibility() != android.view.View.VISIBLE)
			{
		
				answer.setText("");
 				adapter.clear();
 				end.setVisibility(android.view.View.VISIBLE);        
 		        next.setText("next");
 		        NewQues();
			}
			else{
				
				if (yes.getVisibility() == android.view.View.VISIBLE) {
					Words_Data.mistakes.add(Words_Data.old.get(Words_Data.old.size() - 1));
					NewQues();
				}else{
					Words_Data.clock.cancel();
					yes.setVisibility(android.view.View.VISIBLE);
					no.setVisibility(android.view.View.VISIBLE);
					answer.setText(Words_Data.arabic.get(Words_Data.old.get(Words_Data.old.size() - 1)));
				}
				
			}
			}	
     		
        	
        });        
        
        end.setOnClickListener(new OnClickListener() {
        	 	@Override
     			public void onClick(View v) {
        	 		tempEnd();	
     			     			
     			}
     		});
     	   
        yes.setOnClickListener(new OnClickListener() {
    	 	@Override
 			public void onClick(View v) {
    	 		Words_Data.score++;
    	 		NewQues();		     			
 			}
 		});
 	
        
        no.setOnClickListener(new OnClickListener() {
    	 	@Override
 			public void onClick(View v) {
    	 		Words_Data.mistakes.add(Words_Data.old.get(Words_Data.old.size() - 1));
    	 		NewQues();		     			
 			}
 		});
 	
    }

    public void NewQues()
    {
    	
    	Button yes = (Button) findViewById(R.id.yes);
        Button no = (Button) findViewById(R.id.no);
        TextView title = (TextView) findViewById(R.id.title);	
        TextView ques = (TextView) findViewById(R.id.ques);	
        TextView answer = (TextView) findViewById(R.id.answer);
        answer.setText("");
		if (Words_Data.newQues(answer, title, ques, yes, no ) == -1) {
	        	Toast.makeText (getBaseContext (), "Sorry,No more words in the database" , Toast.LENGTH_LONG ).show();
	        	tempEnd();
	        }	

    }
    
    public  void tempEnd()
    {
   	 Button next = (Button) findViewById(R.id.next);
	 Button end = (Button) findViewById(R.id.end);
     Button yes = (Button) findViewById(R.id.yes);
     Button no = (Button) findViewById(R.id.no);
     TextView title = (TextView) findViewById(R.id.title);	
     TextView ques = (TextView) findViewById(R.id.ques);	
     TextView answer = (TextView) findViewById(R.id.answer);	
	
     		Words_Data.QuizEnd();
			Toast.makeText (getBaseContext (), "your score is: "+ Words_Data.getScore() , Toast.LENGTH_LONG ).show();
			title.setText("");
			ques.setText("");
			answer.setText("Important  Answers");
			adapter.addAll(Words_Data.getMistakes());
			end.setVisibility(android.view.View.INVISIBLE);        
	        yes.setVisibility(android.view.View.INVISIBLE);
	        no.setVisibility(android.view.View.INVISIBLE);
	        next.setText("Start");
	        Words_Data.mistakes.clear();
			Words_Data.clear();
    	
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
