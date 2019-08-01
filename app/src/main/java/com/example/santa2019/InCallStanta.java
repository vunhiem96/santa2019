package com.example.santa2019;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InCallStanta extends Activity {
   ImageView cance, videocall, santan;

   MediaPlayer mp;

    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
    }
    protected void onStop() {
        super.onStop();
        mp.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_call_stanta);
        santan = (ImageView) findViewById(R.id.im_gif);
        List<String> santaList = new ArrayList<String>();

        santaList.add("https://media.giphy.com/media/9JnU6uRRblKM3hGzu3/giphy.gif");
        santaList.add("http://giphygifs.s3.amazonaws.com/media/5xtDaryAMLjvAyN4eiY/giphy.gif");
        santaList.add("https://media.giphy.com/media/dJ47OiaxekzODjyWUq/giphy.gif");
        santaList.add("https://media.giphy.com/media/5h7tcpS1O2ojo48DzX/giphy.gif");

        int randomIndex = new Random().nextInt(santaList.size());
        String randomName = santaList.get(randomIndex);



        Glide.with(this).asGif().load(randomName).placeholder(R.drawable.image14).into(santan);
//        webView = (WebView) findViewById(R.id.im_gif);
//        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        webView.getSettings().setLoadsImagesAutomatically(true);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl("https://media.giphy.com/media/9JnU6uRRblKM3hGzu3/giphy.gif");
        List<Integer> soundList = new ArrayList<Integer>();
        soundList.add(R.raw.onggianoel1);
        soundList.add(R.raw.noe2);
        soundList.add(R.raw.noel3);
        soundList.add(R.raw.noel1);
        soundList.add(R.raw.onggianoel2);
        int randomInt = (new Random().nextInt(soundList.size()));
        int sound = soundList.get(randomInt);
        mp = MediaPlayer.create(this, sound);
        mp.start();

//        MediaPlayer mediaPlayer = MediaPlayer.create(InCallStanta.this, R.raw.noel1);
//        mediaPlayer.start();


        cance = (ImageView) findViewById(R.id.huy);
        videocall = (ImageView) findViewById(R.id.video_call);
        videocall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                Intent intent = new Intent(InCallStanta.this, VideoSanta.class);
                startActivity(intent);
            }
        });
        cance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                Intent intent = new Intent(InCallStanta.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }



}
