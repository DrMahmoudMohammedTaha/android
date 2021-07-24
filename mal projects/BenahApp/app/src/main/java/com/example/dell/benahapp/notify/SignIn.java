package com.example.dell.benahapp.notify;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.dell.benahapp.R;

public class SignIn extends AppCompatActivity {

    EditText username;
    EditText password;
    Button savebtn;
    Button login;
    SharedPreferences preferences;
    DatabaseHelper myDb;
    String job2,username2,username3="";
    boolean b=false;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify_activity_sign_in);
        final ProgressBar simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);

        myDb=new DatabaseHelper(this);
        username=(EditText)findViewById(R.id.sign_in_editText_username); //email
        password=(EditText)findViewById(R.id.sign_in_editText_pass);
        login=(Button)findViewById(R.id.login);
        //check correct user

        username2=username.getText().toString();
for(int i=0;i<username2.length();i++)
{
    if(username2.charAt(i)!='@')
    username3+=username2.charAt(i);
    else
    {
        break;
    }
}

         login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String n=username.getText().toString();//email
                String p=password.getText().toString();
                String type="login";

                if(n.length()!=0 || p.length()!=0) {

                    simpleProgressBar.setVisibility(View.VISIBLE);


                    new LoginData().getdata(n, p, " ", type);
                    String getjob = new LoginParser().getparameter("http://192.168.1.101:8080/WebserverUniversity3/Login");
                    Log.e("eeee",getjob);
                    if (getjob.equals("no job")) {
                        simpleProgressBar.setVisibility(View.INVISIBLE);
                        showMessage("Error", "Email or Password is incorrect ");
                        return;
                    } else if (getjob.equals("Student")) {
                            Intent intent = new Intent(SignIn.this, HomeStudent.class);
                            //intent.putExtra("username", username3);
                            startActivity(intent);
                            simpleProgressBar.setVisibility(View.INVISIBLE);
                        } else if (getjob.equals("Doctor")){
                            Intent intent = new Intent(SignIn.this, HomeDoctor.class);
                           // intent.putExtra("username", username3);
                            startActivity(intent);
                            simpleProgressBar.setVisibility(View.INVISIBLE);
                        }
                    else{
                        simpleProgressBar.setVisibility(View.INVISIBLE);
                        showMessage("Error", "Email or Password is incorrect ");
                        return;
                    }
                    }

                else {
                    simpleProgressBar.setVisibility(View.INVISIBLE);
                    showMessage("Error", "Required Email and Passward");

                }

                }

                /*
                Cursor res = myDb.selectJob(username.getText().toString(), password.getText().toString());
                if (res.getCount() == 0) {

                    showMessage("Error", "User name or Password is incorrect ");
                    return;
                }
                if (res.moveToFirst()) {
                    do {
                        job2 = res.getString(0);
                        if(job2.equals("Student"))
                        {
                            Intent intent = new Intent(SignIn.this, HomeStudent.class);
                            intent.putExtra("username",username.getText().toString());
                            startActivity(intent);
                        }

                        else
                        {
                            Intent intent = new Intent(SignIn.this, HomeDoctor.class);
                            intent.putExtra("username",username.getText().toString());
                            startActivity(intent);
                        }
                    } while (res.moveToNext());
                }*/

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

