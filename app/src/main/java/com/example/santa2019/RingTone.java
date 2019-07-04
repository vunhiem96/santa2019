package com.example.santa2019;


import android.os.Bundle;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.santa2019.Model.Ring;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class RingTone extends AppCompatActivity {
    private TabLayout tabLayoutRing;
    private ViewPager viewPagerRing;
  List<Ring> listRing;
  Ring ring;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_tone);
        intData();
        listRaw();

    }

    private void intData() {
        tabLayoutRing = (TabLayout) findViewById(R.id.tabs_ringtune);
        viewPagerRing = (ViewPager) findViewById(R.id.viewpager_ringtune);
    }

    public void listRaw(){
        ring = new Ring();
        Field[] fields=R.drawable.class.getFields();
        listRing = new ArrayList<>();
        for(int count=0; count < fields.length; count++){
            if (fields[count].getName().contains("christmas_emoji"))
            Log.i("Raw Asset: ", fields[count].getName());
            String i = fields[count].getName();
            ring.setId(i);
            listRing.add(ring);
        }
    }




}
