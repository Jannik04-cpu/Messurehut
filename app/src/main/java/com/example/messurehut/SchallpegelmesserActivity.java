package com.example.messurehut;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SchallpegelmesserActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schallpegel);

        setTitle("Schallpegelmesser");
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
