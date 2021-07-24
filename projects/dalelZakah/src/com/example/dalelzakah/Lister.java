package com.example.dalelzakah;


import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Lister extends ListActivity {
	
	ArrayAdapter<String> adapter;
	
	public static final int SHOW_ALL_NAMES = 0;
	public static final int SHOW_ALL_GIVENS = 1;
	public static final int SHOW_SEARCH_NAMES = 2;
	public static final int SHOW_NAME_GIVENS = 3;
	public static final int SHOW_GIVEN_NAMES = 4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.list_activity);
	final ListView lv = getListView();
	
    TextView textTitel = (TextView) findViewById(R.id.textView);
    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
	setListAdapter(adapter);
    Intent receiver = getIntent();
    adapter.addAll(receiver.getStringArrayListExtra("content"));
 
    int state = receiver.getIntExtra("state", 0);
    switch (state) {
    	case SHOW_ALL_NAMES:
    		textTitel.setText("اسماء المحتاجين و الفقراء");
    		lv.setOnItemClickListener(new OnItemClickListener() {
    			public void onItemClick(AdapterView<?> parent, View view,
    					int position, long id) {
    				Intent flow = new Intent(Lister.this, Lister.class);
    				
    				flow.putExtra("state",SHOW_NAME_GIVENS);
    				flow.putExtra("extra", ((TextView) view).getText().toString());
    				flow.putExtra("content", packagePoor.getPoor(lv.getSelectedItemPosition() + 1 ).getGiven());
    				startActivity(flow);
    			}
    		});
    		break;
    	case SHOW_ALL_GIVENS:
    		textTitel.setText("العطاءات");
    		lv.setOnItemClickListener(new OnItemClickListener() {
    			public void onItemClick(AdapterView<?> parent, View view,
    					int position, long id) {
    				Intent flow = new Intent(Lister.this, Lister.class);
    				
    				flow.putExtra("state",SHOW_GIVEN_NAMES);
    				flow.putExtra("extra", ((TextView) view).getText().toString());
    				flow.putExtra("content",packagePoor.findWithGiven(((TextView) view).getText().toString()));
					startActivity(flow);
    			}
    		});
    	    		break;
    	case SHOW_SEARCH_NAMES:
    		textTitel.setText(receiver.getStringExtra("extra"));
    		lv.setOnItemClickListener(new OnItemClickListener() {
    			public void onItemClick(AdapterView<?> parent, View view,
    					int position, long id) {
    				
    			}
    		});

    		break;
    	case SHOW_NAME_GIVENS:
    		textTitel.setText(receiver.getStringExtra("extra"));
    		lv.setOnItemClickListener(new OnItemClickListener() {
    			public void onItemClick(AdapterView<?> parent, View view,
    					int position, long id) {

    			}
    		});

    		break;
    	case SHOW_GIVEN_NAMES:
    		textTitel.setText(receiver.getStringExtra("extra"));
    		lv.setOnItemClickListener(new OnItemClickListener() {
    			public void onItemClick(AdapterView<?> parent, View view,
    					int position, long id) {
    				TextView owner = ((TextView) view);
    				if (owner.getText().toString().contains("\n√")) {
						owner.setText(owner.getText().toString().replace("\n√", ""));
    					
					}else
					{
						owner.setText(owner.getText().toString() + "\n√" );
						
					}
    			}
    		});

    		break;
    				}
	Button back = (Button) findViewById(R.id.returner);
	back.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
	finish();
	}
	});
 }
}