package com.example.dell.benahapp.attend;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class Movement_sensor {

    // Start with some variables
    private static SensorManager sensorMan;
    private static Sensor accelerometer;
    private static float[] mGravity;
    private static float mAccel;
    private static float mAccelCurrent;
    private static float mAccelLast;
    private static AppCompatActivity ACA;
    private static SensorEventListener SEL ;
    private static int index = 0 ;
    private static int timer = 0 ;
    private static int state = 0;

    public static void start_Accelerator(AppCompatActivity ma) {
        ACA = ma;
        SEL = (SensorEventListener) ma;
        sensorMan = (SensorManager)ma.getSystemService(ACA.SENSOR_SERVICE);
        accelerometer = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
    }

    public  static boolean is_stable()
    {
        return state < 2;
    }
    public static void setState() {
        Movement_sensor.state++;
    }

    public static void resetState() {
        Movement_sensor.state = 0;
    }

    public static void resume_Accelerometer() {
        sensorMan.registerListener(SEL , accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    public static void pause_Accelerometer()
    {
        sensorMan.unregisterListener(SEL);
    }

    public static void sensor_changed(SensorEvent event) {

        if ( (timer++) % 8 == 0 ) {
            if ( event.sensor.getType() == Sensor.TYPE_ACCELEROMETER ) {
                mGravity = event.values.clone();
                // Shake detection
                float x = mGravity[0];
                float y = mGravity[1];
                float z = mGravity[2];
                mAccelLast = mAccelCurrent;
                mAccelCurrent = (float) Math.sqrt( x * x +  y * y + z * z) ;

                float delta = mAccelCurrent - mAccelLast;
                //mAccel = mAccel * 0.9f + delta;
                mAccel = Math.abs( delta);
                // Make this higher or lower according to how much
                // motion you want to detect
                if (mAccel > 0.8) {
                    index++;

                } else if ( mAccel < 0.1 && index > 2) {
                    Toast.makeText(ACA, "I have sensed move! " + (index), Toast.LENGTH_SHORT).show();
                    setState();
                    index = 0;

                }
            }
        }
    }

}
