package com.example.dell.benahapp.notify;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dell.benahapp.R;

import java.util.ArrayList;

public class HomeDoctor extends AppCompatActivity {


    SharedPreferences preferences;
    Button logout;
    EditText etext;
    Button add,coursedetail;
    Button update,uploadbtn;
    Button delete;
    TextView tvName;

    String username;
    DatabaseHelper myDb;
    ArrayList<String> getdepartments;
    ArrayList<String> getstaff1;
    ArrayList<String> getstaff2;
    ArrayList<String> Faculities;
    ArrayList<String> courses;

    Spinner spinnercourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify_activity_home_doctor);

     /*   Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
        } */

        tvName = (TextView) findViewById(R.id.textViewD);
        tvName.setText(username);

        logout = (Button) findViewById(R.id.logout);

        //  coursedetail=(Button)findViewById(R.id.coursedetail);
        /*
        add=(Button)findViewById(R.id.add);
       update=(Button)findViewById(R.id.update);
        delete=(Button)findViewById(R.id.delete); */


        //logout=(Button)findViewById(R.id.logout);

/*
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("username");
                editor.remove("password");
                editor.commit();

                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
*/


        myDb = new DatabaseHelper(this);
        // btnenroll=(Button)findViewById(R.id.btnenroll);


        final Spinner spinnerfac = (Spinner) findViewById(R.id.spinnerfac);
        final Spinner spinnerdep = (Spinner) findViewById(R.id.spinnerdep);
        final Spinner spinnerstaff = (Spinner) findViewById(R.id.spinnerstaff);
        spinnercourse = (Spinner) findViewById(R.id.spinnercourse);

        final ArrayAdapter<String> dataAdapter2;
        final ArrayAdapter<String> dataAdapter3;
        final ArrayAdapter<String> dataAdapter4;


        getdepartments = new ArrayList<String>();
        getstaff1 = new ArrayList<String>();
        getstaff2 = new ArrayList<String>();
        Faculities = new ArrayList<String>();
        courses = new ArrayList<String>();

        Faculities.add("Faculities");
        Faculities.add("Agriculture");
        Faculities.add("Veterinary Medicine");
        Faculities.add("Commerce");
        Faculities.add("Nursing");
        Faculities.add("Engineering,Shoubra");
        Faculities.add("Engineering,Benha");
        Faculities.add("Science");
        Faculities.add("Arts");
        Faculities.add("Education");
        Faculities.add("Physical Educaton");
        Faculities.add("Laws");
        Faculities.add("Information & Technology");
        Faculities.add("Applied Arts");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Faculities);

        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerfac.setAdapter(dataAdapter1);


        getdepartments.add("Department");
        getstaff1.add("Staff");
        getstaff2.add("Staff");
        courses.add("Course");

        dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getdepartments);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerdep.setAdapter(dataAdapter2);

        dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getstaff1);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerstaff.setAdapter(dataAdapter3);

        dataAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, courses);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercourse.setAdapter(dataAdapter4);

        spinnerfac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                //fill department spinner
                Cursor res1 = myDb.getselectedjointable2And1(spinnerfac.getSelectedItem().toString());

                if ( res1.getCount() == 0) {
                    //Toast.makeText(MainActivity.this, "No data", Toast.LENGTH_LONG).show();
                    getdepartments.clear();
                    getdepartments.add("Department");
                    spinnerdep.setAdapter(dataAdapter2);

                }
                if (res1.moveToFirst()) {
                    getdepartments.clear();
                    getdepartments.add("Department");
                    spinnerdep.setAdapter(dataAdapter2);

                    do {
                        getdepartments.add(res1.getString(0));
                    } while (res1.moveToNext());
                }

                spinnerdep.setAdapter(dataAdapter2);

                //fill staff spinner
                Cursor res2 = myDb.getselectedstaffbyfac(spinnerfac.getSelectedItem().toString());

                //!spinnerfac.getSelectedItem().toString().equals("Faculities")&& res2.getCount() ==0
                if (res2.getCount() == 0) {
                    // Toast.makeText(MainActivity.this, "No data", Toast.LENGTH_LONG).show();
                    getstaff1.clear();
                    getstaff1.add("Staff");
                    spinnerstaff.setAdapter(dataAdapter3);
                }

                if (res2.moveToFirst()) {
                    getstaff1.clear();
                    getstaff1.add("Staff");
                    spinnerstaff.setAdapter(dataAdapter3);
                    do {

                        getstaff1.add(res2.getString(0));


                    } while (res2.moveToNext());

                    spinnerstaff.setAdapter(dataAdapter3);
                }

                //fill course spinner
                Cursor res3 = myDb.getselectedcourse(spinnerfac.getSelectedItem().toString());

                if (res3.getCount() == 0) {
                    courses.clear();
                    courses.add("Course");
                    spinnercourse.setAdapter(dataAdapter4);
                }

                if (res3.moveToFirst()) {
                    courses.clear();
                    courses.add("Course");
                    spinnercourse.setAdapter(dataAdapter4);
                    do {

                        courses.add(res3.getString(0));


                    } while (res3.moveToNext());

                    spinnercourse.setAdapter(dataAdapter4);
                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        spinnerdep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                //fill staff spinner
                if (spinnerdep.getSelectedItem().equals("Department")) {
                    Cursor res2 = myDb.getselectedstaffbyfac(spinnerfac.getSelectedItem().toString());

                    //!spinnerfac.getSelectedItem().toString().equals("Faculities")&& res2.getCount() ==0
                    if (res2.getCount() == 0) {
                        // Toast.makeText(MainActivity.this, "No data", Toast.LENGTH_LONG).show();
                        getstaff1.clear();
                        getstaff1.add("Staff");
                        spinnerstaff.setAdapter(dataAdapter3);
                    }

                    if (res2.moveToFirst()) {
                        getstaff1.clear();
                        getstaff1.add("Staff");
                        spinnerstaff.setAdapter(dataAdapter3);
                        do {

                            getstaff1.add(res2.getString(0));


                        } while (res2.moveToNext());

                        spinnerstaff.setAdapter(dataAdapter3);
                    }
                } else {
                    Cursor res2 = myDb.getselectedstaffbydep(spinnerfac.getSelectedItem().toString(), spinnerdep.getSelectedItem().toString());

                    if (res2.getCount() == 0 && !spinnerdep.getSelectedItem().equals("Department")) {
                        getstaff1.clear();
                        getstaff1.add("Staff");
                        spinnerstaff.setAdapter(dataAdapter3);
                    }

                    if (res2.moveToFirst() && !spinnerdep.getSelectedItem().equals("Department")) {
                        getstaff1.clear();
                        getstaff1.add("Staff");
                        do {

                            getstaff1.add(res2.getString(0));


                        } while (res2.moveToNext());
                        spinnerstaff.setAdapter(dataAdapter3);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        spinnerstaff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                //fill course spinner
                if (spinnerstaff.getSelectedItem().equals("Staff")) {

                    Cursor res3 = myDb.getselectedcourse(spinnerfac.getSelectedItem().toString());

                    if (res3.getCount() == 0) {
                        courses.clear();
                        courses.add("Course");
                        spinnercourse.setAdapter(dataAdapter4);
                    }

                    if (res3.moveToFirst()) {
                        courses.clear();
                        courses.add("Course");
                        spinnercourse.setAdapter(dataAdapter4);
                        do {

                            courses.add(res3.getString(0));


                        } while (res3.moveToNext());

                        spinnercourse.setAdapter(dataAdapter4);
                    }

                } else {
                    Cursor res2 = myDb.getselectedcoursebystaff(spinnerfac.getSelectedItem().toString(), spinnerstaff.getSelectedItem().toString());

                    if (res2.getCount() == 0 && !spinnerstaff.getSelectedItem().equals("Staff")) {
                        courses.clear();
                        courses.add("Course");
                        spinnercourse.setAdapter(dataAdapter4);
                    }

                    if (res2.moveToFirst() && !spinnerstaff.getSelectedItem().equals("Staff")) {
                        courses.clear();
                        courses.add("Course");

                        do {

                            courses.add(res2.getString(0));


                        } while (res2.moveToNext());
                        spinnercourse.setAdapter(dataAdapter4);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        /*
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!spinnercourse.getSelectedItem().toString().equals("Course")) {
                            boolean res = myDb.actionslide(spinnercourse.getSelectedItem().toString(),spinnercourse.getSelectedItem().toString() ,"slide is added");
                            if(res)
                                Toast.makeText(HomeDoctor.this,"slide is added",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            showMessage("error","Please select course");
                        }

                        /*
                        Intent intent = new Intent(HomeDoctor.this, HomeStudent.class);
                        intent.putExtra("coursename",spinnercourse.getSelectedItem().toString());
                        intent.putExtra("action","slide is added in this course ");
                       // startActivity(intent);
                    }
                }
      //  );

        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!spinnercourse.getSelectedItem().toString().equals("Course")) {
                            boolean res = myDb.actionslide(spinnercourse.getSelectedItem().toString(),spinnercourse.getSelectedItem().toString() ,"slide is deleteded");
                        }
                        else
                            showMessage("error","Please select course");

                    }
                }
        );

        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!spinnercourse.getSelectedItem().toString().equals("Course")) {

                            boolean res = myDb.actionslide(spinnercourse.getSelectedItem().toString(),spinnercourse.getSelectedItem().toString() ,"slide is updateded");
                        }
                        else
                            showMessage("error","Please select course");
                    }
                }
        ); */

        uploadbtn = (Button) findViewById(R.id.share);

        uploadbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!spinnercourse.getSelectedItem().toString().equals("Course")) {

                            Intent i = new Intent(HomeDoctor.this, UploadActivity.class);
                            i.putExtra("coursename", spinnercourse.getSelectedItem().toString());

                            startActivity(i);


                        } else
                            showMessage("error", "Please select course");
                    }
                }
        );

        logout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(HomeDoctor.this, notificationMain.class);

                        startActivity(i);

                    }
                }
        );

       /* coursedetail.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(HomeDoctor.this,CourseDetail.class);
                         i.putExtra("coursename",spinnercourse.getSelectedItem().toString());
                        startActivity(i);

                    }
                }
        ); */
    }
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

/*
<Button
        android:layout_width="200dp"
        android:layout_height="50dp"
        android:id="@+id/coursedetail"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:text="Course Detail"
        android:layout_below="@id/share"
        android:layout_marginTop="15dp"
        android:layout_centerInParent="true"

        />
 */