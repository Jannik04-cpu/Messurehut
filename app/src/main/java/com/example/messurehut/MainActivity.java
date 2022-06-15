package com.example.messurehut;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText txtMaxdB;
    ImageButton btSchallpegelmesser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.app_name));

        txtMaxdB = findViewById(R.id.editTextTextPersonName);
        btSchallpegelmesser = findViewById(R.id.imageButton3);

        txtMaxdB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean isMatch = Pattern.compile("^[0-9]*$")
                        .matcher(txtMaxdB.getText().toString())
                        .find();
                if(!isMatch){
                    txtMaxdB.setError("Es dürfen nur Zahlen eingegeben werden");
                }
            }
        });
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