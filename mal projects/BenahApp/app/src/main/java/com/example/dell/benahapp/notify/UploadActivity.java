package com.example.dell.benahapp.notify;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.benahapp.R;

import java.io.File;
import java.net.URISyntaxException;

public class UploadActivity extends AppCompatActivity {

    String path2;
    TextView t1;
    Button select,cansel,upload;
    public static final int PERMISSIONS_REQUEST_CODE = 0;
    private static final int FILE_SELECT_CODE = 1;
    filechooser f = new filechooser();
    String path,coursename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify_activity_upload);

        Bundle b=getIntent().getExtras();

        if(b !=null)
        {
            coursename = b.getString("coursename");
        }

        select = (Button) findViewById(R.id.b1);
        cansel = (Button) findViewById(R.id.b2);
        upload = (Button) findViewById(R.id.b3);
        t1 = (TextView) findViewById(R.id.t1);


        // new UploadFile().execute(new String[] { "http://192.168.1.3:8080/ReceiveFileServlet/RecFileServlet"});


        select.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                              intent.setType("*/*");
                                              intent.addCategory(Intent.CATEGORY_OPENABLE);

                                              try {
                                                  startActivityForResult(
                                                          Intent.createChooser(intent, "Select a File to Upload"),
                                                          FILE_SELECT_CODE);
                                                  setResult(notificationMain.RESULT_OK, intent);
                                              } catch (ActivityNotFoundException ex) {
                                                  // Potentially direct the user to the Market with a Dialog
                                                  Toast.makeText(UploadActivity.this, "Please install a File Manager.",
                                                          Toast.LENGTH_SHORT).show();
                                              }

                                          }

                                      }

        );

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (t1.getText().toString().length() != 0) {
                    new UploadFile().execute(new String[] { t1.getText().toString(),"http://192.168.1.101:8080/WebserverUniversity3/RecFileServlet",coursename});


                }
            }        });


        cansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(UploadActivity.this,HomeDoctor.class);
                startActivity(i);

            }
        });
            }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    File myFile = new File(uri.toString());
                    path = myFile.getAbsolutePath();
                    String s = myFile.getName();

                    try {
                        path2 = filechooser.getPath(this,uri);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    t1.setText(path2);

                    //new UploadFile().execute(new String[] { path2,"http://192.168.1.3:8081/WebserverUniversity2/RecFileServlet",coursename});

/*String getresponse=new LoginParser().getparameter("http://192.168.1.3:8080/WebserverUniversity/RecFileServlet");

                    if(getresponse.length()!=0)
                    Toast.makeText(UploadActivity.this,getresponse,Toast.LENGTH_LONG);
                    else
                        Toast.makeText(UploadActivity.this,"no",Toast.LENGTH_LONG); */


                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    }

