package com.example.messurehut;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.messurehut.sensor.RotationVector;

public class SpiritLevelActivity extends AppCompatActivity {

    private RotationVector rotationVector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_spirit_level);

        setTitle("Wasserwaage");

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
//        gyroscope = new Gyroscope(this);
        rotationVector = new RotationVector(this);
    }

    @Override
    protected void onStart(){
        super.onStart();

        TextView X = (TextView)findViewById(R.id.X);
        TextView Y = (TextView)findViewById(R.id.Y);

        ImageView imgUp = (ImageView)findViewById(R.id.up);
        ImageView imgDown = (ImageView)findViewById(R.id.down);
        ImageView imgLeft = (ImageView)findViewById(R.id.left);
        ImageView imgRight = (ImageView)findViewById(R.id.right);

        rotationVector.setListener(new RotationVector.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz) {
                X.setText(String.format("%.2f", rx));
                Y.setText(String.format("%.2f", ry));

                imgUp.setVisibility(View.INVISIBLE);
                imgDown.setVisibility(View.INVISIBLE);
                imgLeft.setVisibility(View.INVISIBLE);
                imgRight.setVisibility(View.INVISIBLE);

                if(Float.parseFloat(String.format("%.2f", rx)) > 0f){
                    imgDown.setVisibility(View.VISIBLE);
                }
                else if(Float.parseFloat(String.format("%.2f", rx)) < 0f){
                    imgUp.setVisibility(View.VISIBLE);
                }
                if(Float.parseFloat(String.format("%.2f", ry)) > 0f){
                    imgRight.setVisibility(View.VISIBLE);
                }
                else if(Float.parseFloat(String.format("%.2f", ry)) < 0f){
                    imgLeft.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        rotationVector.register();
    }

    @Override
    protected void onPause(){
        super.onPause();

        rotationVector.unregister();
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