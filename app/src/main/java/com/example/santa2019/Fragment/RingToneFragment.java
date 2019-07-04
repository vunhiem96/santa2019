package com.example.santa2019.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.santa2019.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RingToneFragment extends Fragment {


    public RingToneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ring_tone, container, false);
    }

}
