package com.example.dell.benahapp.Major;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.benahapp.R;
import com.example.dell.benahapp.attend.AttendenceActivity;
import com.example.dell.benahapp.calender.calenderMain;
import com.example.dell.benahapp.course.courseMain;
import com.example.dell.benahapp.emergency.emergMain;
import com.example.dell.benahapp.font_factory;
import com.example.dell.benahapp.gallery.galleryMain;
import com.example.dell.benahapp.login.LoginActivity;
import com.example.dell.benahapp.maps.mapMain;
import com.example.dell.benahapp.navigate.ocr.MainOCR;
import com.example.dell.benahapp.notify.notificationMain;
import com.example.dell.benahapp.protal.portalMain;
import com.example.dell.benahapp.ques.Student;
import com.example.dell.benahapp.result.resultMain;
import com.example.dell.benahapp.safe.safeMain;
import com.example.dell.benahapp.website.websiteActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class studentservicefragment extends Fragment {

    public static final String ALERT = "Notification";
    public static final String LIBRARY = "Library";
    public static final String NAVI = "Navigate";
    public static final String MAP = "Maps";
    public static final String ATTENDANCE = "Attendance";
    public static final String STAFF = "Staff";
    public static final String OFFICE = "Office 365";
    public static final String EKB = "EKB";
    public static final String PORTAL = "Portal";
    public static final String GALLERY = "News";
    public static final String COURSE = "Courses";
    public static final String CALENDER = "Time Table";
    public static final String RESULT = "Exam Results";
    public static final String EMERG = "Emergency";
    public static final String SAFE = "safety";
    public static final String QUES = "Questionair";


    View v;
    Toolbar toolbar;
    public List<ViewHolder> ArrayOfData=new ArrayList<>();
    public studentservicefragment() {

    }



    private static final String[] autoWords = new String[] {
            ALERT ,  LIBRARY ,  NAVI,  MAP ,  ATTENDANCE ,  STAFF
            , OFFICE ,  EKB ,  PORTAL
            , GALLERY ,  COURSE ,  CALENDER
            , RESULT , EMERG , SAFE , QUES

};

    public void searchAutoComplete2()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, autoWords);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                v.findViewById(R.id.autoCompleteTextView);
        textView.setAdapter(adapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      v=inflater.inflate(R.layout.fragment_startfragment, container, false);
        //      toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        //        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);


        //ListView l=(ListView)v.findViewById(R.id.l1);


        Button Go = (Button) v.findViewById(R.id.buttonGo);

        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AutoCompleteTextView textView = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView);
                String temp = textView.getText().toString();
                if(temp.equals(OFFICE))
                {
                    Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://outlook.office.com/owa/"));
                    startActivity(i);
                }
                else if(temp.equals(STAFF))
                {
                    Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.bu.edu.eg/portal/index.php?act=104"));
                    startActivity(i);
                }
                else if(temp.equals(ATTENDANCE))
                {
                    Intent i=new Intent( getActivity(), AttendenceActivity.class);
                    startActivity(i);
                }
                else if(temp.equals(LIBRARY))
                {
                    Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://srv3.eulc.edu.eg/eulc_v5/libraries/start.aspxn"));
                    startActivity(i);
                }else if(temp.equals(EKB))
                {
                    Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ekb.eg/login"));
                    startActivity(i);
                }
                else if(temp.equals(PORTAL))
                {
                    Intent i=new Intent( getActivity(), portalMain.class);
                    startActivity(i);
                }
                else if(temp.equals(GALLERY))
                {
                    Intent i=new Intent( getActivity(), galleryMain.class);
                    startActivity(i);
                }else if(temp.equals(COURSE))
                {
                    Intent i=new Intent( getActivity(), courseMain.class);
                    startActivity(i);
                }
                else if(temp.equals(CALENDER))
                {
                    if(!temp.equals("English")) {
                        Intent i = new Intent(getActivity(), calenderMain.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(getActivity(),"Calender is allowed in English mode only",Toast.LENGTH_LONG).show();
                    }
                }
                else if(temp.equals(RESULT))
                {
                    Intent i=new Intent( getActivity(), resultMain.class);
                    startActivity(i);
                }
                else if(temp.equals(MAP))
                {
                    Intent i=new Intent( getActivity(), mapMain.class);
                    startActivity(i);
                }
                else if(temp.equals(EMERG))
                {
                    Intent i=new Intent( getActivity(), emergMain.class);
                    startActivity(i);
                }
                else if(temp.equals(NAVI))
                {
                    Intent i=new Intent( getActivity(), MainOCR.class);
                    startActivity(i);
                }
                else if(temp.equals(SAFE))
                {
                    Intent i=new Intent( getActivity(), safeMain.class);
                    startActivity(i);
                }else if(temp.equals(ALERT))
                {
                    Intent i=new Intent( getActivity(), notificationMain.class);
                    startActivity(i);
                }else if(temp.equals(QUES))
                {
                    Intent i=new Intent( getActivity(), Student.class);
                    startActivity(i);
                }
            }
        });

        Button b = (Button) v.findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
        });


        Button b2 = (Button) v.findViewById(R.id.button2);

        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), websiteActivity.class);
                startActivity(i);
            }
        });

        Button b3 = (Button) v.findViewById(R.id.button3);


        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent x=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/benhamis/?ref=br_rs"));
                startActivity(x);
            }
        });
        Button b7 = (Button) v.findViewById(R.id.button7);
        final ScrollView myScrollView = (ScrollView)v.findViewById(R.id.scrollViewmain) ;

        b7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myScrollView.scrollTo(0, 0);
            }
        });

        final Button b6 = (Button) v.findViewById(R.id.button6);
        final String temp = b6.getText().toString();

        b6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Locale locale;
                if(temp.equals("English"))
                {
                    locale = new Locale("en");
                    Toast.makeText(getActivity(), "Language: English", Toast.LENGTH_LONG).show();
                   }
                else
                {
                    locale = new Locale("ar");
                    Toast.makeText(getActivity(), "اللغة: العربية", Toast.LENGTH_LONG).show();
                }

                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getActivity().getBaseContext().getResources().updateConfiguration(config, getActivity().getBaseContext().getResources().getDisplayMetrics());
                getActivity().finish();
                startActivity(getActivity().getIntent());

            }
        });

        font_factory.stylish_it1(getActivity(),b,b2,b3,b6,b7);
        searchAutoComplete2();

        GridView g=(GridView)v.findViewById(R.id.gridView);

        ViewHolder v1=new ViewHolder();
        v1.settitle(ALERT);
        v1.setid(R.drawable.ic_alert);

        ViewHolder v2=new ViewHolder();
        v2.settitle(LIBRARY);
        v2.setid(R.drawable.library);

        ViewHolder v3=new ViewHolder();
        v3.settitle(MAP);
        v3.setid(R.drawable.map);

        ViewHolder v4=new ViewHolder();
        v4.settitle(ATTENDANCE);
        v4.setid(R.drawable.ic_rafid);

        ViewHolder v5=new ViewHolder();
        v5.settitle(OFFICE);
        v5.setid(R.drawable.outlook);

        ViewHolder v6=new ViewHolder();
        v6.settitle(STAFF);
        v6.setid(R.drawable.searchstaff);

        ViewHolder v7=new ViewHolder();
        v7.settitle(EKB);
        v7.setid(R.drawable.ekb);

        ViewHolder v8=new ViewHolder();
        v8.settitle(PORTAL);
        v8.setid(R.drawable.portal);

        ViewHolder v9=new ViewHolder();
        v9.settitle(GALLERY);
        v9.setid(R.drawable.gallery);

        ViewHolder v10=new ViewHolder();
        v10.settitle(COURSE);
        v10.setid(R.drawable.course);

        ViewHolder v11=new ViewHolder();
        v11.settitle(CALENDER);
        v11.setid(R.drawable.calender);

        ViewHolder v12=new ViewHolder();
        v12.settitle(RESULT);
        v12.setid(R.drawable.results);

        ViewHolder v13=new ViewHolder();
        v13.settitle(EMERG);
        v13.setid(R.drawable.emerg);

        ViewHolder v14=new ViewHolder();
        v14.settitle(NAVI);
        v14.setid(R.drawable.navi);



        ViewHolder v15=new ViewHolder();
        v15.settitle(SAFE);
        v15.setid(R.drawable.safe);


        ViewHolder v16=new ViewHolder();
        v16.settitle(QUES);
        v16.setid(R.drawable.ques);

        ArrayOfData.add(v4); // attendence
        ArrayOfData.add(v14); // indoor navigation
        ArrayOfData.add(v11); // calender
        ArrayOfData.add(v9); // news

        ArrayOfData.add(v10); // courses
        ArrayOfData.add(v3); // Maps
        ArrayOfData.add(v1); // alert
        ArrayOfData.add(v16); // qeustionaire

        ArrayOfData.add(v7); // EKB
        ArrayOfData.add(v5);  // office
        ArrayOfData.add(v2); // library
        ArrayOfData.add(v8); // portal

        ArrayOfData.add(v12); // results
        ArrayOfData.add(v6); // staff
        ArrayOfData.add(v13); // emergency
        ArrayOfData.add(v15); // safety

        ImageAdapter a=new ImageAdapter(getContext(),R.layout.griditem,ArrayOfData);
        g.setAdapter(a);

        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ViewHolder vh=ArrayOfData.get(position);
                Log.v("xtest" , "oncllick items");


                if(vh.gettitle().equals(OFFICE))
                {
                    Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://outlook.office.com/owa/"));
                    startActivity(i);
                }
                else if(vh.gettitle().equals(STAFF))
                {
                    Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.bu.edu.eg/portal/index.php?act=104"));
                    startActivity(i);
                }
                else if(vh.gettitle().equals(ATTENDANCE))
                {
                    Intent i=new Intent( getActivity(), AttendenceActivity.class);
                    startActivity(i);
                }
                else if(vh.gettitle().equals(LIBRARY))
                {
                    Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://srv3.eulc.edu.eg/eulc_v5/libraries/start.aspxn"));
                    startActivity(i);
                }else if(vh.gettitle().equals(EKB))
                {
                    Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ekb.eg/login"));
                    startActivity(i);
                }
                else if(vh.gettitle().equals(PORTAL))
                {
                    Intent i=new Intent( getActivity(), portalMain.class);
                    startActivity(i);
                }
                else if(vh.gettitle().equals(GALLERY))
                {
                    Intent i=new Intent( getActivity(), galleryMain.class);
                    startActivity(i);
                }else if(vh.gettitle().equals(COURSE))
                {
                    Intent i=new Intent( getActivity(), courseMain.class);
                    startActivity(i);
                }
                else if(vh.gettitle().equals(CALENDER))
                {
                    if(!temp.equals("English")) {
                        Intent i = new Intent(getActivity(), calenderMain.class);
                        startActivity(i);
                    }
                else{
                        Toast.makeText(getActivity(),"Calender is allowed in English mode only",Toast.LENGTH_LONG).show();
                    }
                }
                else if(vh.gettitle().equals(RESULT))
                {
                    Intent i=new Intent( getActivity(), resultMain.class);
                    startActivity(i);
                }
                else if(vh.gettitle().equals(MAP))
                {
                    Intent i=new Intent( getActivity(), mapMain.class);
                    startActivity(i);
                }
                else if(vh.gettitle().equals(EMERG))
                {
                    Intent i=new Intent( getActivity(), emergMain.class);
                    startActivity(i);
                }
                else if(vh.gettitle().equals(NAVI))
                {
                    Intent i=new Intent( getActivity(), MainOCR.class);
                    startActivity(i);
                }else if(vh.gettitle().equals(SAFE))
                {
                    Intent i=new Intent( getActivity(), safeMain.class);
                    startActivity(i);
                }else if(vh.gettitle().equals(ALERT))
                {
                    Intent i=new Intent( getActivity(), notificationMain.class);
                    startActivity(i);
                }else if(vh.gettitle().equals(QUES))
                {
                    Intent i=new Intent( getActivity(), Student.class);
                    startActivity(i);
                }
            }
            });


        Go.requestFocus();

        TextView tv_news = (TextView) v.findViewById(R.id.newsTxt);
        tv_news.setText( "الإعداد للملتقى العلمى العربى الاول لمركز تنمية القدرات بجامعة بنها 2017/02/23"+ " ******* "+
                "منتدى الحوار الأول للجامعات الحكومية والخاصة 2017/02/22"+" ******* "+
                "جلسات إستماع لتطوير التعليم بجامعة بنها 2017/02/06"+" ******* "+
                "قطاع الدراسات العليا والبحوث بجامعة بنها ينظم ورش عمل عن بنك المعرفة المصري 2016/11/16" +" ******* "
        );

        font_factory.stylish_it1(tv_news,getActivity());
        tv_news.setSelected(true);


        return v;
    }

}
