package com.example.santa2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class CallSanta extends AppCompatActivity {
   MediaPlayer mediaPlayer;
  ImageView btnCan;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_santa);
        btnCan = (ImageView) findViewById(R.id.img_cancel);
        btnCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               stopHandler();
                finish();
            }
        });

        mediaPlayer = MediaPlayer.create(CallSanta.this, R.raw.tut);
        mediaPlayer.start();
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                screenRouter();
            }
        }, 10000);
    }
    public void stopHandler() {
        handler.removeMessages(0);
    }
    @Override
    protected void onPause() {
        stopHandler();
        mediaPlayer.stop();
        super.onPause();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    void screenRouter(){
        Intent intent = new Intent(this, InCallStanta.class);
        startActivity(intent);
    }

}
