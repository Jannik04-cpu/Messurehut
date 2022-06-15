package com.example.messurehut;

import static java.lang.String.valueOf;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.messurehut.sensor.Soundmeter;

public class SchallpegelmesserActivity extends AppCompatActivity {


    Soundmeter soundmeter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schallpegel);


        TextView maxDecibelValue = findViewById(R.id.maxDecibel);

        setTitle("Schallpegelmesser");
        ActionBar actionBar = getSupportActionBar();

        Intent intent = getIntent();

        maxDecibelValue.setText(String.valueOf(intent.getIntExtra("maxDecibel", 100)));


        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        soundmeter = new Soundmeter(this);

        soundmeter.start(this, this);


    }

    @Override
    protected void onStop() {
        super.onStop();
        soundmeter.stop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickRecord(View v) {
        soundmeter = new Soundmeter(this);
        soundmeter.start(this, this);
    }

    public void onClickStop(View v) {
        TextView decibelValue = findViewById(R.id.decibel);
        if (soundmeter.mediaRecorder.getMaxAmplitude() != 0) {
            decibelValue.setText(valueOf(soundmeter.mediaRecorder.getMaxAmplitude()));
        } else {
            AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
            alertBox.setMessage("Mikrofon funktioniert möglicherweise nicht. Nochmals versuchen?");
            alertBox.setTitle("Microphone does not work");
            alertBox.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getApplicationContext(), SchallpegelmesserActivity.class);
                    startActivity(intent);
                }
            });
            alertBox.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
            alertBox.create().show();
        }
        soundmeter.stop();

    }
}
