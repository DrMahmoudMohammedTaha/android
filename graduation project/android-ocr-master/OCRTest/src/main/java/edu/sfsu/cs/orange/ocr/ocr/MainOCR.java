package edu.sfsu.cs.orange.ocr.ocr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import edu.sfsu.cs.orange.ocr.CaptureActivity;
import edu.sfsu.cs.orange.ocr.R;

/**
 * Created by DELL on 9/6/2017.
 */

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
                    if (ocr_servent.check_values(current_p , dest_p))
                        ocr_servent.checkRoute(current_p , dest_p);
                    else
                        Toast.makeText( app , "Not a valid source or destination!", Toast.LENGTH_LONG ).show();
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
