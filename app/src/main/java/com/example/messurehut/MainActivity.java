package com.example.messurehut;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.app_name));
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            actionBar.setIcon(R.drawable.icon_messurehut);
        }



    }

    public void onClick(View v){
        Intent intent = new Intent(getApplicationContext(), SchallpegelmesserActivity.class);
        startActivity(intent);
    }
}