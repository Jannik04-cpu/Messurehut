package com.example.messurehut;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
                if (!isMatch) {
                    txtMaxdB.setError("Es dürfen nur Zahlen eingegeben werden");
                }
            }
        });
    }

    public void onClickEar(View v) {
        Intent intent = new Intent(getApplicationContext(), SchallpegelmesserActivity.class);
        TextView decibelValueMain = findViewById(R.id.editTextTextPersonName);
        intent.putExtra("maxDecibel", Integer.parseInt(decibelValueMain.getText().toString()));
        startActivity(intent);
    }

    public void onClickSpirit(View v) {
        Intent intent = new Intent(getApplicationContext(), SpiritLevelActivity.class);
        startActivity(intent);
    }
}