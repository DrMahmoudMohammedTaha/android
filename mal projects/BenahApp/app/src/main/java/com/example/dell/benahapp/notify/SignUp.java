package com.example.dell.benahapp.notify;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dell.benahapp.R;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {

    EditText username;
    EditText password;

    Button signup;
    DatabaseHelper myDb;
    ArrayList<String> job;
     ProgressBar simpleProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify_activity_sign_up);

        myDb=new DatabaseHelper(this);

        username=(EditText)findViewById(R.id.sign_up_editText_username);
        password=(EditText)findViewById(R.id.sign_up_editText_pass);
        final Spinner spinnerjob = (Spinner) findViewById(R.id.editText_job);
        final ArrayAdapter<String> dataAdapter4;
        job=new ArrayList<String>();
        job.add("job");
        job.add("Student");
        job.add("Doctor");

        simpleProgressBar  = (ProgressBar) findViewById(R.id.simpleProgressBar);
        dataAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,job);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerjob.setAdapter(dataAdapter4);

        signup=(Button)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String n=username.getText().toString();
                String p=password.getText().toString();
                String j=spinnerjob.getSelectedItem().toString();
                String type="register";


                if(n.length()!=0 || p.length()!=0 || j.length()!=0) {

                   if(!n.contains("@feng.bu.edu")){showMessage("Error","Email is incorrect,Type your edu mail");
                       simpleProgressBar.setVisibility(View.VISIBLE);}
                   else {
                       new LoginData().getdata(n, p, j, type);
                       simpleProgressBar.setVisibility(View.INVISIBLE);
                       Log.e("workers hhh    " , n +"$"+ p +"$"+ j +"$"+ type);
                       Toast.makeText(SignUp.this, "Done you can back to logIn", Toast.LENGTH_LONG).show();
                   }
                }/*
                boolean res=myDb.signup(username.getText().toString(),password.getText().toString(),job.getText().toString());

                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent); */

            }
        });

    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
