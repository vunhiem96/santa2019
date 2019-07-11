package com.example.santa2019;


import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.santa2019.Adapter.RingViewPagerAdapter;
import com.example.santa2019.Fragment.RingToneFavorites;
import com.example.santa2019.Fragment.RingToneFragment;
import com.example.santa2019.Model.Ring;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class RingToneActivity extends AppCompatActivity {
    private TabLayout tabLayoutRing;
    private ViewPager viewPagerRing;
ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_tone);
        intData();
        setupViewPager();
        tabLayoutRing.setupWithViewPager(viewPagerRing);

    }

    private void intData() {
        tabLayoutRing = (TabLayout) findViewById(R.id.tabs_ringtune);
        viewPagerRing = (ViewPager) findViewById(R.id.viewpager_ringtune);
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void setupViewPager() {
        RingViewPagerAdapter adapter = new RingViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RingToneFragment(), "Ringtones");
        adapter.addFragment(new RingToneFavorites(), "Favorites");
        viewPagerRing.setAdapter(adapter);
    }

}








