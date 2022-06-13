package com.example.messurehut;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.messurehut.sensor.Gyroscope;

public class SpiritLevelActivity extends AppCompatActivity {

    private Gyroscope gyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spirit_level);

        setTitle("Wasserwaage");

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        gyroscope = new Gyroscope(this);

        gyroscope.setListener(new Gyroscope.Listener() {

            @Override
            public void onRotation(float rx, float ry, float rz) {

                ImageView imgUp = (ImageView)findViewById(R.id.up);
                ImageView imgDown = (ImageView)findViewById(R.id.down);
                ImageView imgLeft = (ImageView)findViewById(R.id.left);
                ImageView imgRight = (ImageView)findViewById(R.id.right);

                imgUp.setVisibility(View.INVISIBLE);
                imgDown.setVisibility(View.INVISIBLE);
                imgLeft.setVisibility(View.INVISIBLE);
                imgRight.setVisibility(View.INVISIBLE);
                if(rx > 0f){
                    imgDown.setVisibility(View.VISIBLE);
                }
                else if(rx < 0f){
                    imgUp.setVisibility(View.VISIBLE);
                }
                if(ry > 0f){
                    imgRight.setVisibility(View.VISIBLE);
                }
                else if(ry < 0f){
                    imgLeft.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        gyroscope.register();
    }

    @Override
    protected void onPause(){
        super.onPause();

        gyroscope.unregister();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}