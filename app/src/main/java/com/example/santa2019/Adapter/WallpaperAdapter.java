package com.example.santa2019.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.santa2019.Interface.ILoadMore;
import com.example.santa2019.Model.Image;
import com.example.santa2019.R;
import com.example.santa2019.SetWallpaperActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHoler> {
    public Context context;
    public List<Image> data;




    public WallpaperAdapter(Context context, List<Image> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public WallpaperViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WallpaperViewHoler(LayoutInflater.from(context).inflate(R.layout.row_wallpaper, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperAdapter.WallpaperViewHoler holder, int position) {
        Image image = data.get(position);
        Picasso.with(context).load(image.getId()).into(holder.imgWallpaper);
//        holder.imgWallpaper.setImageResource(image.getId());
        holder.imgWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SetWallpaperActivity.class);
                intent.putExtra("key", image.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class WallpaperViewHoler extends RecyclerView.ViewHolder {
        ImageView imgWallpaper;
        public WallpaperViewHoler(@NonNull View itemView) {
            super(itemView);
            imgWallpaper = itemView.findViewById(R.id.imgWallpaper);
        }
    }

}
