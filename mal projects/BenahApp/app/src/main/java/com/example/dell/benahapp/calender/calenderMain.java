package com.example.dell.benahapp.calender;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.benahapp.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class calenderMain extends AppCompatActivity {

    CompactCalendarView compactCalendar;


    SimpleDateFormat fullDate = new SimpleDateFormat("MMM dd yyyy HH:mm");
    SimpleDateFormat evTime = new SimpleDateFormat("HH:mm");
    SimpleDateFormat month = new SimpleDateFormat("MMMM - yyyy");
    SimpleDateFormat day = new SimpleDateFormat("MMM dd yyyy");

    public calenderMain() throws IOException {
    }


    void saveData(String fileName, String key, Set<String> values){
        SharedPreferences sharedPref = getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet(key, values);
        editor.apply();
    }

    Set<String> loadData(String fileName, String key){
        SharedPreferences sharedPref = getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Set<String> vals = sharedPref.getStringSet(key, new HashSet<String>());
        return vals;
    }

    void eventMaker(String d, String ev)  {

        Date date = null;

        try {
            date = fullDate.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        String time = evTime.format(date);

        try {
            date = day.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long epoch = date.getTime();
        Event event = new Event(Color.RED,epoch,ev);
        compactCalendar.addEvent(event);

        if(loadData("eventList", d)==null){
            Set<String> eventList = new HashSet<>();
            eventList.add(ev);
            saveData("eventList",date.toString(),eventList);

            Set<String> eventTimes = new HashSet<>();
            eventTimes.add(time);
            saveData("eventTimes",date.toString(),eventTimes);
        }
        else{

            Set<String> eventList = loadData("eventList", date.toString());
            eventList.add(ev);
            saveData("eventList",date.toString(),eventList);

            Set<String> eventTimes = loadData("eventTimes", date.toString());
            eventTimes.add(time);
            saveData("eventTimes",date.toString(),eventTimes);
        }

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_main);

        SharedPreferences  mPrefs = getPreferences(MODE_PRIVATE);


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);

//        Gson gson = new Gson();
//        String json = mPrefs.getString("MyObject", "");
//
//        if(!json.equals(""))
//            compactCalendar = gson.fromJson(json, CompactCalendarView.class);
//
//        else
            compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);


        actionBar.setTitle(month.format(compactCalendar.getFirstDayOfCurrentMonth()));
        compactCalendar.setUseThreeLetterAbbreviation(true);


//        Uncomment for code testing
        eventMaker("May 08 2017 10:30","Quiz");
        eventMaker("May 08 2017 08:45","Lecture");
        eventMaker("May 18 2017 10:15", "Revision" );
        eventMaker("Jun 18 2017 12:30", "Final Exam" );



        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            TextView events = (TextView) findViewById(R.id.events);
            @Override
            public void onDayClick(Date dateClicked) {

                Context context = getApplicationContext();

     if(!loadData("eventList",dateClicked.toString()).isEmpty()) {
         events.setText("");

         Set<String> eventList =loadData("eventList",dateClicked.toString());
         Set<String> eventTimes = loadData("eventTimes",dateClicked.toString());

         for(int i=0; i<eventList.size(); i++){
             String output = (i+1)+"-"+eventList.toArray()[i]+" at "+eventTimes.toArray()[i]+"\n\n";
             events.append(output);
         }

     }
                else{
                    events.setText("NO EVENT PLANNED FOR THIS DAY");
                    Toast.makeText(context,"No Event",Toast.LENGTH_SHORT).show();

        }

        }
        @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                events.setText("");
                actionBar.setTitle(month.format(firstDayOfNewMonth));
            }
        });

//        SharedPreferences.Editor prefsEditor = mPrefs.edit();
//        gson = new Gson();
//        json = gson.toJson(compactCalendar);
//        prefsEditor.putString("MyObject", json);
//        prefsEditor.commit();


    }


}
