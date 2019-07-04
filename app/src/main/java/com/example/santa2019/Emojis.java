package com.example.santa2019;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.example.santa2019.Fragment.EmojisFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Emojis extends AppCompatActivity {
    ImageView imgBack;
    ViewPager viewPagerEmojis;
    TabLayout tabLayoutEmojis;


    @OnClick(R.id.img_back) void back() {
        finish();
    }
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.pager)
    ViewPager pager;
    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emojis);
        ButterKnife.bind(this);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        pager.setCurrentItem(0);

    }



    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] Titles = {"Christmas Emoji", "Christmas Hats", "Chrismas Santa", "Gifts", "Santa", "Sticker Merry Chrismas", "Tree Christmas", "Bells", "Ball"};

        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Titles[position];
        }

        @Override
        public int getCount() {
            return Titles.length;
        }

        @Override
        public Fragment getItem(int position) {
            return EmojisFragment.newInstance(position);
        }
    }
}
