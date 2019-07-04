package com.example.santa2019.Adapter;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.santa2019.Model.Ring;
import com.example.santa2019.R;

import java.util.List;

public class RingAdapter extends RecyclerView.Adapter<RingAdapter.RingViewHolder> {
    Context context;
    List<Ring> data;
    @NonNull
    @Override
    public RingAdapter.RingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RingViewHolder(LayoutInflater.from(context).inflate(R.layout.item_ring,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RingAdapter.RingViewHolder ringViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RingViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        public RingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name_ring);
        }
    }
}
