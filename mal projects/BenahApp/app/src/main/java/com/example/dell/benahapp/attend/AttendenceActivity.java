package com.example.dell.benahapp.attend;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.example.dell.benahapp.R;

public class AttendenceActivity extends AppCompatActivity implements SensorEventListener {

    public static Button cameraBt ;
    public static  AlphaAnimation buttonClick = new AlphaAnimation(1F, 0F);
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.attendance_main);

        attendance_servant.init_modules(this);

        cameraBt = (Button)findViewById(R.id.btnCamera);

        cameraBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.startAnimation(buttonClick);
                attendance_servant.recordAttendence();

            }
        });


        Button PlusrBt = (Button)findViewById(R.id.plubtn);

        PlusrBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AttendenceActivity.this , pluserActivity.class);
                startActivity(intent);
            }
        });

        Button newBtn = (Button)findViewById(R.id.newbtn);

        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               data_factory.showFileChooser(attendance_servant.app);
            }
        });



    }



    @Override
    public void onResume() {
        super.onResume();
        attendance_servant.resume_Activity();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        Movement_sensor.sensor_changed(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       attendance_servant.check_Activity_results(requestCode,resultCode,data);
    }

    @Override
    protected void onPause() {
        new Runnable() {
            @Override
            public void run() {
                attendance_servant.pause_Activity();

            }
        }.run();
        super.onPause();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }

}