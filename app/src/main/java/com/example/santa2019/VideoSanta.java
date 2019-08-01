package com.example.santa2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

public class VideoSanta extends AppCompatActivity {
     WebView webView;
     ImageView cane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_santa);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.youtube.com/watch?v=fxuZuhOP-fc");
        cane = (ImageView) findViewById(R.id.cance_video);
        cane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoSanta.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
