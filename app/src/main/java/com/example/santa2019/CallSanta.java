package com.example.santa2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class CallSanta extends AppCompatActivity {
   MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_santa);

        mediaPlayer = MediaPlayer.create(CallSanta.this, R.raw.a1);
        mediaPlayer.start();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                screenRouter();
            }
        }, 10000);
    }

    @Override
    protected void onPause() {
        mediaPlayer.stop();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }

    void screenRouter(){
        Intent intent = new Intent(this, InCallStanta.class);
        startActivity(intent);
    }

}
