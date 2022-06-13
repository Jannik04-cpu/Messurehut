package com.example.messurehut;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.app_name));

        final SensorManager sensorManager;
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor sensor : sensors) {
            Log.d("Sensor", sensor.getName());
        }
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