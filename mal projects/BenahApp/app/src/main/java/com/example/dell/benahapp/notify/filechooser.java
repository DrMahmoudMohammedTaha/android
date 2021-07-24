package com.example.dell.benahapp.notify;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Created by basma on 23/06/2017.
 */
public class filechooser {
    public static AppCompatActivity app;
    public static File goalFile;
    private static final int FILE_SELECT_CODE = 1;


    public static  void showFileChooser(AppCompatActivity ma) {
        app=ma;
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

    public static String check_file(int requestCode, int resultCode , Intent data) {
        String path = null;
        if ( requestCode ==  FILE_SELECT_CODE ){
            if (resultCode == app.RESULT_OK) {
                // Get the Uri of the selected file
                Uri uri = data.getData();

                // Get the path

                try {
                    path = getPath(app, uri);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                // Get the file instance
                goalFile = new File(path);
                // Initiate the upload


            }
        }
        return path;
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



}
