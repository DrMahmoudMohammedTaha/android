package com.example.dell.benahapp.attend;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class data_factory {

    public static AppCompatActivity app;
    public static ArrayList<String> studentData = new ArrayList<>();
    public static ArrayList<String> pluserismData = new ArrayList<>();
    public static File goalFile;
    public static String globalName = "class "+new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date()) + ".csv";
    private static final int FILE_SELECT_CODE = 1;

    public static  void showFileChooser(AppCompatActivity ma) {
        app = ma;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            app.startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(app, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public static void check_file(int requestCode, int resultCode , Intent data) {

           if ( requestCode ==  FILE_SELECT_CODE ){
                if (resultCode == app.RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();

                    // Get the path
                    String path = null;
                    try {
                        path = getPath(app, uri);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                    // Get the file instance
                    goalFile = new File(path);
                    // Initiate the upload
                    studentData = new ArrayList<String>();
                    pluserismData = new ArrayList<String>();
                    get_CSV_data();

                }
        }

    }

    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }




    public static Boolean get_txt_data (File f) {

        boolean state ;

        BufferedReader reader ;
        try {
            reader = new BufferedReader(new FileReader(f));
            studentData = new ArrayList<>();
            pluserismData = new ArrayList<>();
            globalName = null;
            String line;
            globalName = reader.readLine();
            while ((line = reader.readLine()) != null) {
                studentData.add(line);
            }
            Log.v("xtest get_txt_data", " last file "+ studentData.size());
            state = true;
        } catch (IOException e) {
            Log.v("xtest get_txt_data", " cannot find last file");
            state = false;
            e.printStackTrace();
        }

        return state;
    }

    public static Boolean get_CSV_data () {

        boolean state ;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(goalFile));

            String line;
            reader.readLine();
            studentData = new ArrayList<String>();
            pluserismData = new ArrayList<String>();
            globalName = "class "+new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date()) + ".csv";

            while ((line = reader.readLine()) != null) {

                String[] RowData = line.split(",");

                if(RowData.length > 3) {
                    studentData.add(formatItem(RowData[0], RowData[1], RowData[RowData.length-1]));
                }
            }

            saveLast(getLastData());
            Toast.makeText(app ,"working by " + studentData.size() + " student", Toast.LENGTH_SHORT).show();
            state = true;
        } catch (IOException e) {
            e.printStackTrace();
            state = false;

        }

        return state;
    }

    private static String formatItem(String id , String name , String num) {
        if(num == "")
            num = "0";

        return  id.trim() + " - "  + name.trim() + " - "+ num.trim();
    }

    public static boolean setAttendence(String item) {

        for (int i = 0; i < studentData.size(); i++) {
            if(studentData.get(i).contains(item))
            {
                updateItem(i);
                return true;
            }
        }

        return false;
    }

    public static boolean removeAttendence(String item) {
        for (int i = 0; i < studentData.size(); i++) {
            if(studentData.get(i).contains(item))
            {
                lowerItem(i);

                QR_factory.QR_data = "none";
                return true;
            }
        }

        return false;
    }

    public static boolean loadLast()
    {
        return get_txt_data(getOutputLastFile());
    }

    public static void updateItem(int index) {

        String str []= studentData.get(index).split("-");
        int tmp = Integer.parseInt(str[2].trim());
       studentData.set(index , formatItem(str[0] , str[1] , (++tmp) +"") );
    }
    public static void lowerItem(int index) {

        String str []= studentData.get(index).split("-");
        int tmp = Integer.parseInt(str[2].trim());
        studentData.set(index , formatItem(str[0] , str[1] , (--tmp) +"") );
    }


    public static void saveLast(String data)
    {
        File dataFile = getOutputLastFile();

        if(dataFile == null){

            return;
        }

        try{

            FileOutputStream fos = new FileOutputStream(dataFile);
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    fos, "UTF-8"));
            try {
                out.write("");
                out.append(data);
            } finally {
                out.close();
            }

        }catch(FileNotFoundException e){

        } catch (IOException e){

        }

    }

    public static void saveAttendence(String data)
    {

        File dataFile = getOutputMediaFile();

        if(dataFile == null){

            return;
        }

        try{

            FileOutputStream fos = new FileOutputStream(dataFile);
            Writer out = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
            try {
                out.write(data);

            } finally {
                out.close();
            }


        }catch(FileNotFoundException e){

        } catch (IOException e){

        }

    }


    private static File getOutputLastFile(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"BenhApp");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if(!mediaStorageDir.exists()){
            if(!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        File lastFile;
        lastFile = new File(mediaStorageDir.getPath()+File.separator+"last"+".txt");
        return lastFile;
    }


    private static File getOutputMediaFile(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"BenhApp");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if(!mediaStorageDir.exists()){
            if(!mediaStorageDir.mkdirs()){
                return null;
            }
        }


        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath()+File.separator+globalName);
        return mediaFile;
    }


    public static String getStringData() {
        String temp = "";
        for (int i = 0; i < studentData.size(); i++) {
            temp += studentData.get(i).replaceAll("-",",")+"\n";
            }
        return  temp;
        }

    public static String getLastData() {
        String temp = globalName + "\n";
        for (int i = 0; i < studentData.size(); i++) {
            temp += studentData.get(i) +"\n";
        }
        return  temp;
    }
    }

