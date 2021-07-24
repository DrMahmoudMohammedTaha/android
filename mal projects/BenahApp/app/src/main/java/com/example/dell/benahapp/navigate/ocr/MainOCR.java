package com.example.dell.benahapp.navigate.ocr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.benahapp.R;
import com.example.dell.benahapp.font_factory;
import com.example.dell.benahapp.navigate.CaptureActivity;

import java.io.IOException;


public class MainOCR extends Activity {

    public static String current_p = "";
    public static String dest_p = "" ;
    public static Activity app ;

    public EditText et2;
    public EditText et1;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.home_ocr);

        font_factory.stylish_it1(this,(TextView) findViewById(R.id.textView) , (TextView) findViewById(R.id.textView2) ,  (TextView) findViewById(R.id.textView4)  );
        font_factory.stylish_it2((TextView)findViewById(R.id.instructions) , this);
        app = this;
        et1  = (EditText) findViewById(R.id.dest_p);
        et2 = (EditText) findViewById(R.id.current_p);

        et2.setText(current_p);
        Button scanIT = (Button) findViewById(R.id.scanner);


        scanIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainOCR.this, CaptureActivity.class);
                startActivityForResult(i, 10);

            }
        });

        Button routeIT = (Button) findViewById(R.id.router);

        routeIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             current_p =   et2.getText().toString();
             dest_p =   et1.getText().toString();
                try {
                    if (ocr_servent.check_values(current_p , dest_p)) {
                        String temp = ocr_servent.checkRoute(current_p, dest_p);
                        TextView tv = (TextView) findViewById(R.id.instructions);
                        tv.setText("Instructions: \n"+temp);
                    }
                    else{
                        Toast.makeText( app , "Not a valid source or destination!", Toast.LENGTH_LONG ).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 10) {
            if(resultCode == Activity.RESULT_OK){
                current_p =data.getStringExtra("result");
                Log.e("www" , current_p);
                et2.setText(current_p);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
    }
