package com.example.dell.nightprayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {



     double fajr = Double.parseDouble(getFajrTime().split(":")[0]) + Double.parseDouble(getFajrTime().split(":")[1]) / 60;
     final double maghrib = Double.parseDouble(getMaghribTime().split(":")[0]) + Double.parseDouble(getMaghribTime().split(":")[1]) / 60;
     final double pureMaghrib = 24 - maghrib;
     final double timer = fajr + pureMaghrib;
     final double third2 = maghrib + timer / 3;
     final double half = maghrib + timer / 2;
     final double third3 = maghrib + 2 * timer / 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        TextView tv = (TextView) findViewById(R.id.textNow);
        tv.setText(getNow());

        tv = (TextView) findViewById(R.id.textStart);
        tv.setText(timeprayer(handler(maghrib)));


        tv = (TextView) findViewById(R.id.textEnd);
        tv.setText(getFajrTime());


        tv = (TextView) findViewById(R.id.textNum);
        tv.setText(timeprayer(timer));


        tv = (TextView) findViewById(R.id.textHalf);
        tv.setText(timeprayer(handler(half)));


        tv = (TextView) findViewById(R.id.textThird);
        tv.setText(timeprayer(handler(third3)));

    }


    private  String getNow() {
        String now = new SimpleDateFormat("hh:mm").format(new Date());
        double NOW = Double.parseDouble(now.split(":")[0]) + Double.parseDouble(now.split(":")[1]) / 60;
        now = new SimpleDateFormat("aa").format(new Date());

        fajr = fajr + 24;

        if ("AM".equals(now) && (int) NOW != 12) {
            NOW += 24;
        } else if (("PM".equals(now) && (int) NOW != 12) || "AM".equals(now)) {
            NOW += 12;
        }

        if (NOW <= third2 && NOW >= maghrib) {
            now = " ثلث الليل الأول ";
        } else if (NOW >= third2 && NOW <= third3) {
            now = " منتصف الليل ";
        } else if (NOW >= third3 && NOW <= fajr) {
            now = " ثلث الليل الآخر ";
        } else {
            now = " النهار ";
        }
        return now;
    }

    public static double handler(double f) {
        if (f >= 24) {
            f = f - 24;
        } else {
            f = f - 12;
        }
        return f;
    }

    public static String getFajrTime() {

        final int timeZone = 2;
        final double longitude = 30.2, latitude = 30, fajrTwilight = -19.5, ishaTwilight = -17.5;
        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date())), month = Integer.parseInt(new SimpleDateFormat("MM").format(new Date())), day = Integer.parseInt(new SimpleDateFormat("dd").format(new Date())), hours = Integer.parseInt(new SimpleDateFormat("HH").format(new Date())), minutes = Integer.parseInt(new SimpleDateFormat("mm").format(new Date()));
        double fajrTime, zuhrTime;

        double D = (367 * year) - ((year + (int) ((month + 9) / 12)) * 7 / 4) + (((int) (275 * month / 9)) + day - 730531.5);
        double L = 280.461 + 0.9856474 * D;
        L = moreLess360(L);
        double M = 357.528 + (0.9856003) * D;
        M = moreLess360(M);
        double Lambda = L + 1.915 * Math.sin(degToRad(M)) + 0.02 * Math.sin(degToRad(2 * M));
        Lambda = moreLess360(Lambda);
        double Obliquity = 23.439 - 0.0000004 * D;
        double Alpha = radToDeg(Math.atan((Math.cos(degToRad(Obliquity)) * Math.tan(degToRad(Lambda)))));
        Alpha = moreLess360(Alpha);
        Alpha = Alpha - (360 * (int) (Alpha / 360));
        Alpha = Alpha + 90 * (Math.floor(Lambda / 90) - Math.floor(Alpha / 90));
        double ST = 100.46 + 0.985647352 * D;
        double Dec = radToDeg(Math.asin(Math.sin(degToRad(Obliquity)) * Math.sin(degToRad(Lambda))));
        double Durinal_Arc = radToDeg(Math.acos((Math.sin(degToRad(-0.8333)) - Math.sin(degToRad(Dec)) * Math.sin(degToRad(latitude))) / (Math.cos(degToRad(Dec)) * Math.cos(degToRad(latitude)))));
        double Noon = Alpha - ST;
        Noon = moreLess360(Noon);
        double UT_Noon = Noon - longitude;

        // 2) Zuhr Time [Local noon]
        zuhrTime = UT_Noon / 15 + timeZone;

        double Fajr_Arc = radToDeg(Math.acos((Math.sin(degToRad(fajrTwilight)) - Math.sin(degToRad(Dec)) * Math.sin(degToRad(latitude))) / (Math.cos(degToRad(Dec)) * Math.cos(degToRad(latitude)))));
        fajrTime = zuhrTime - (Fajr_Arc / 15);

        return new DecimalFormat("00").format(fajrTime) + ":" + new DecimalFormat("00").format((fajrTime % 1) * 60);
    }

    //make sure a value is between 0 and 360
    static double moreLess360(double value) {
        while (value > 360 || value < 0) {
            if (value > 360) {
                value -= 360;
            } else if (value < 0) {
                value += 360;
            }
        }

        return value;
    }

    static double degToRad(double degree) {
        return ((3.1415926 / 180) * degree);
    }

    static double radToDeg(double radian) {
        return (radian * (180 / 3.1415926));
    }

    public static String getMaghribTime() { //defination of variables for calculating prayer times
        final int timeZone = 2;
        final double longitude = 30.2, latitude = 30, fajrTwilight = -19.5, ishaTwilight = -17.5;
        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date())), month = Integer.parseInt(new SimpleDateFormat("MM").format(new Date())), day = Integer.parseInt(new SimpleDateFormat("dd").format(new Date())), hours = Integer.parseInt(new SimpleDateFormat("HH").format(new Date())), minutes = Integer.parseInt(new SimpleDateFormat("mm").format(new Date()));
        double fajrTime, sunRiseTime, zuhrTime, asrTime, maghribTime, ishaTime;
        //end of prayer time variables

        //end of stage 1
        //satge 2 update your prayer times
        double D = (367 * year) - ((year + (int) ((month + 9) / 12)) * 7 / 4) + (((int) (275 * month / 9)) + day - 730531.5);
        double L = 280.461 + 0.9856474 * D;
        L = moreLess360(L);
        double M = 357.528 + (0.9856003) * D;
        M = moreLess360(M);
        double Lambda = L + 1.915 * Math.sin(degToRad(M)) + 0.02 * Math.sin(degToRad(2 * M));
        Lambda = moreLess360(Lambda);
        double Obliquity = 23.439 - 0.0000004 * D;
        double Alpha = radToDeg(Math.atan((Math.cos(degToRad(Obliquity)) * Math.tan(degToRad(Lambda)))));
        Alpha = moreLess360(Alpha);
        Alpha = Alpha - (360 * (int) (Alpha / 360));
        Alpha = Alpha + 90 * (Math.floor(Lambda / 90) - Math.floor(Alpha / 90));
        double ST = 100.46 + 0.985647352 * D;
        double Dec = radToDeg(Math.asin(Math.sin(degToRad(Obliquity)) * Math.sin(degToRad(Lambda))));
        double Durinal_Arc = radToDeg(Math.acos((Math.sin(degToRad(-0.8333)) - Math.sin(degToRad(Dec)) * Math.sin(degToRad(latitude))) / (Math.cos(degToRad(Dec)) * Math.cos(degToRad(latitude)))));
        double Noon = Alpha - ST;
        Noon = moreLess360(Noon);
        double UT_Noon = Noon - longitude;
        // 2) Zuhr Time [Local noon]
        zuhrTime = UT_Noon / 15 + timeZone;
        // Asr Hanafi
        // Asr Shafii
        double Asr_Alt = radToDeg(Math.atan(1 + Math.tan(degToRad(latitude - Dec))));
        double Asr_Arc = radToDeg(Math.acos((Math.sin(degToRad(90 - Asr_Alt)) - Math.sin(degToRad(Dec)) * Math.sin(degToRad(latitude))) / (Math.cos(degToRad(Dec)) * Math.cos(degToRad(latitude)))));
        Asr_Arc = Asr_Arc / 15;
        // 3) Asr Time
        asrTime = zuhrTime + Asr_Arc;
        // 1) Shorouq Time
        sunRiseTime = zuhrTime - (Durinal_Arc / 15);
        // 4) Maghrib Time
        maghribTime = zuhrTime + (Durinal_Arc / 15);
        double Esha_Arc = radToDeg(Math.acos((Math.sin(degToRad(ishaTwilight)) - Math.sin(degToRad(Dec)) * Math.sin(degToRad(latitude))) / (Math.cos(degToRad(Dec)) * Math.cos(degToRad(latitude)))));
        // 5) Isha Time
        ishaTime = zuhrTime + (Esha_Arc / 15);
        // 0) Fajr Time
        double Fajr_Arc = radToDeg(Math.acos((Math.sin(degToRad(fajrTwilight)) - Math.sin(degToRad(Dec)) * Math.sin(degToRad(latitude))) / (Math.cos(degToRad(Dec)) * Math.cos(degToRad(latitude)))));
        fajrTime = zuhrTime - (Fajr_Arc / 15);
        //end of stage 2

        return timeprayer(maghribTime);

    }

    public static String timeprayer(double f) {
        return new DecimalFormat("00").format(Math.floor(f)) + ":" + new DecimalFormat("00").format((f % 1) * 60);
    }

}
