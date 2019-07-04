package com.example.santa2019;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RelativeLayout rlRingtone, rlEmojis;
    TextView tvDay, tvHoursMinutes, tvSencond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        checkAndRequestPermissions();
        final Handler h = new Handler();
        h.post(new Runnable() {
            @Override
            public void run() {
                countDownStart();
                h.postDelayed(this, 1000);
            }
        });

    }

    private void countDownStart() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date noel = dateFormat.parse("2019-12-25");
            Date currentDate = new Date();
            if (!currentDate.after(noel)) {
                long diff = noel.getTime() - currentDate.getTime();
                long days = diff / (24 * 60 * 60 * 1000);
                diff -= days * (24 * 24 * 1000);
                long hours = diff / (60 * 60 * 1000);
                diff -= hours * (60 * 60 * 1000);
                long minutes = diff / (60 * 1000);
                diff -= minutes * (60 * 1000);
                long seconds = diff / 1000;

                tvDay.setText("" + String.format("%d", days) + " Days");
                tvHoursMinutes.setText("" + String.format("%d", hours) + " hours, " + String.format("%d", minutes) + " minutes");
                tvSencond.setText("" + String.format("%d", seconds) + " seconds");
            } else {
                Toast.makeText(this, "Merry Christmas", Toast.LENGTH_SHORT).show();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    private void checkAndRequestPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
        }
    }

    private void initData() {
        tvDay = (TextView) findViewById(R.id.tv_countdown_day);
        tvHoursMinutes = (TextView) findViewById(R.id.tv_hours_countdown);
        tvSencond = (TextView) findViewById(R.id.tv_second_countdown);
        rlRingtone = (RelativeLayout) findViewById(R.id.rl_ringtone);
        rlEmojis = (RelativeLayout) findViewById(R.id.rl_emojis);

        rlRingtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RingTone.class);
                startActivity(intent);
            }
        });

        rlEmojis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Emojis.class);
                startActivity(intent);
            }
        });
    }
}
