package com.example.dell.benahapp.notify;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by basma on 14/06/2017.
 */
public class LoginData {
    String s;
    LoginParser Logparser;
    String urlReg="http://192.168.1.101:8080/WebserverUniversity3/Register";
    String urlLog="http://192.168.1.101:8080/WebserverUniversity3/Login";
    String urlNotif="http://192.168.1.101:8080/WebserverUniversity3/Notification";
    String fileurl="http://192.168.1.101:8080/WebserverUniversity3/dowonloadFile";

    public void getdata(String nm,String p ,String j,String type)
    {
        List<NameValuePair> list=new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("name",nm));
        list.add(new BasicNameValuePair("pass",p));

        try {
            if(type.equals("login"))
                Logparser.LoginParser(list,urlLog);
            else {

                list.add(new BasicNameValuePair("job",j));
                Logparser.LoginParser(list, urlReg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setdownloadfile(String n)
    {
        List<NameValuePair> list=new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("fileurl",fileurl));

        try {

                Logparser.LoginParser(list,fileurl);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setNotif(String coursename)
    {
        List<NameValuePair> list=new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("coursename",coursename));
        //list.add(new BasicNameValuePair("res",s));
        try {

            Logparser.LoginParser(list,urlNotif);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}