package com.example.messurehut.sensor;

import static android.media.AudioManager.MODE_NORMAL;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaRecorder;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;

public class Soundmeter {
    private static final double EMA_FILTER = 0.6;

    public MediaRecorder mediaRecorder;

    private static int MICROPHONE_PERMISSION_CODE = 200;

    public Soundmeter(Activity activity) {
        mediaRecorder = new MediaRecorder(activity.getApplicationContext());
    }

    public void start(Context context, Activity activity) {

        System.out.println("Ist Mikrofon verfügbar: " + isMicrophoneAvailable(context));

        if (isMicrophoneAvailable(context)) {
            getMicrophonePermission(context, activity);
        }

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(context.getFilesDir() + "test");
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        mediaRecorder.start();
    }

    public void stop() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    public double getDecibel() {
        Double doubel = 20 * Math.log10(mediaRecorder.getMaxAmplitude() / 2700.0);
        return doubel;
    }

    private void getMicrophonePermission(Context context, Activity activity) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]
                    {Manifest.permission.RECORD_AUDIO}, MICROPHONE_PERMISSION_CODE);
        }
    }

    public static boolean isMicrophoneAvailable(Context context) {
        AudioManager audioManager = (AudioManager) context.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        return audioManager.getMode() == MODE_NORMAL;
    }
}
