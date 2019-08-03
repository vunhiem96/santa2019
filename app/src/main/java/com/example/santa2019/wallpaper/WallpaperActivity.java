package com.example.santa2019.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.santa2019.Adapter.WallpaperAdapter;
import com.example.santa2019.Model.Image;
import com.example.santa2019.R;

import java.util.ArrayList;


public class WallpaperActivity extends AppCompatActivity {
    ImageView imgBack;
    RecyclerView rvWallpaper;
    WallpaperAdapter adapter;
    ArrayList<Image> data = new ArrayList<>();
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
        initData();


    }

    private void initData() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        rvWallpaper = (RecyclerView) findViewById(R.id.rv_wallpaper);

        for (int i = 1; i <= 50; i++) {
            data.add(new Image(R.drawable.image + i));
            adapter = new WallpaperAdapter(WallpaperActivity.this,data);
            GridLayoutManager g = new GridLayoutManager(WallpaperActivity.this, 2);
            rvWallpaper.setLayoutManager(g);
            rvWallpaper.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            rvWallpaper.setHasFixedSize(true);


            imgBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }}
