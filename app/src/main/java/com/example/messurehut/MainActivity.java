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
    }

    public void onClickEar(View v){
        Intent intent = new Intent(getApplicationContext(), SchallpegelmesserActivity.class);
        startActivity(intent);
    }

    public void onClickSpirit(View v){
        Intent intent = new Intent(getApplicationContext(), SpiritLevelActivity.class);
        startActivity(intent);
    }
}