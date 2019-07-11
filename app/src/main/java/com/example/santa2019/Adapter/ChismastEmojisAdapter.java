package com.example.santa2019.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.santa2019.Model.Image;
import com.example.santa2019.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ChismastEmojisAdapter extends RecyclerView.Adapter<ChismastEmojisAdapter.ViewHolder> {
    public List<Image> data;
    public Context context;


    public ChismastEmojisAdapter(ArrayList<Image> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_emoji, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Image image = data.get(position);
        Picasso.with(context).load(image.getId()).into(holder.imgEmoji);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Drawable mDrawable = holder.imgEmoji.getDrawable();
            Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();
            String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), mBitmap, "Image Description", null);
            Uri uri = Uri.parse(path);
            Log.e("tét","uri"+uri);
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/jepg");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(String.valueOf(uri)));
            context.startActivity(Intent.createChooser(intent, "Share image"));
        }
    });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgEmoji;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgEmoji = itemView.findViewById(R.id.img_emoji);
        }
    }
}
