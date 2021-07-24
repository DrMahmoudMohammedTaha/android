package com.example.dell.benahapp.safe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.dell.benahapp.R;

import java.io.Serializable;

public class safeWebView extends AppCompatActivity implements Serializable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safe_activity_web_view);

        String HTMLFile="rules.html";

        WebView web;
        web =(WebView)findViewById(R.id.webView);
        HTMLFile = (String) getIntent().getSerializableExtra("s");
        web.loadUrl("file:///android_asset/" + HTMLFile + ".html");
    }

}
