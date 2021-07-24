package com.example.dell.indoornavi;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static String language = "eng";
    public static TessOCR mTessOCR ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTessOCR = new TessOCR(this, language);

    }

    private void doOCR (final Bitmap bitmap) {


        new Thread(new Runnable() {
            public void run() {
                final String srcText = mTessOCR.getOCRResult(bitmap);

                /*
                ocrView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        if (srcText != null && !srcText.equals("")) {

                        }
                        mTessOCR.onDestroy();
                    }
                });
                */
            }
        }).start();
    }
}
