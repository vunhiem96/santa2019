package com.example.santa2019;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

public class InCallStanta extends Activity {
   LinearLayout lncall;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_call_stanta);
        lncall = (LinearLayout) findViewById(R.id.ln_call);

        videoView = new VideoView(InCallStanta.this);
        videoView.setMediaController(new MediaController(this));
        lncall.addView(videoView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.setVideoURI(Uri.parse("https://www.facebook.com/quatangcuocsongvideo/videos/245705779425144/"));
        videoView.start();

    }
}
