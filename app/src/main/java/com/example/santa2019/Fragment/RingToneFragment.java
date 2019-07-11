package com.example.santa2019.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.santa2019.Adapter.AudioItemAdapter;
import com.example.santa2019.Model.Ring;
import com.example.santa2019.R;

import java.util.ArrayList;


public class RingToneFragment extends Fragment {
    AudioItemAdapter adapter;
    ArrayList<Ring> data = new ArrayList<>();
    RecyclerView rvRingTone;

    public RingToneFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ring_tone, container, false);
        rvRingTone = (RecyclerView) view.findViewById(R.id.rv_ring_tone);
        getDataIcon();
        return view;

    }


    private void getDataIcon() {
        String[] Titles = {"","Dream Bells", "Techno Remix Xmas", "Relaxing Christmas", "Low Techno", "Old Instrumental", "Rock Xmas", "Christmas Chimes", "Piano Jingle", "Short Nostalgic", "Soft Bells", "Its Christmas", "Santa Bell",
                "Baby Xmas Song", "Christmas Bells", "Christmas Sms", "Christmas Time", "Christmas Tone", "Cool Christmas Tone", "Cute Merry Christmas", "Dogs Christmas Song", "Flute Instrumental", "Funny Donald Duck", "Funny New Year", "Jingle Bells", "Happy New Year", "Ho Ho", "Jazz Jingle Bell", "Jingle Bells", "La La La", "Merry Christmas Chipmunks", "New Jingle Bells", "Piano Merry Christmas", "Rap Jingle Bells", "Relaxing Christmas Song", "Remix Funny Jingle Bells", "Santa Ho Ho", "Santa Merry Christmas", "Saxophone", "Short Message", "Sms Jingle Melody", "Sms Xmas Tone", "Techno Xmas", "Xmas Bells Sms", "Xmas Sms", "Xmas Soft Tone", "Xmas Techno Remix", "Xmas Time", "Christmas Short Tone", "Cute Christmas Tone", "Jingle Bells Keyboard", "Jingle Bells", "Soothing", "We Wish You A Merry Christmas", "Xmas Beat Sms", "Xmas Sms", "Christmas Melody", "Techno", "Cute Melody", "Xmas Chimes","Bells","Jingle Bell Mono"};
        for (int i = 1; i <= 60; i++) {
            data.add(new Ring(R.drawable.a + i, R.raw.a + i, Titles[i]));
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            rvRingTone.setLayoutManager(linearLayoutManager);
            adapter = new AudioItemAdapter(data, getActivity());
            rvRingTone.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            rvRingTone.setHasFixedSize(true);
        }
    }
}





