package com.example.santa2019.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.santa2019.Model.Image;
import com.example.santa2019.Model.Ring;
import com.example.santa2019.Model.RingFa;
import com.example.santa2019.R;
import com.example.santa2019.db.DatabaseHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class AudioItemAdapter extends RecyclerView.Adapter<AudioItemAdapter.AudioItemsViewHolder>  {
    ArrayList<RingFa> contacts;
  Ringtone ringTone;
  DatabaseHandler db;
    private MediaPlayer mediaPlayer;

    private Handler uiUpdateHandler;

    private List<Ring> audioItems;
    private int playingPosition;
    private AudioItemsViewHolder playingHolder;
    Context context;
    public AudioItemAdapter(List<Ring> audioItems, Context ctx) {
        this.audioItems = audioItems;
        this.playingPosition = -1;
        this.context=ctx;

    }

    @Override
    public AudioItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AudioItemsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ring, parent, false));
    }

    @Override
    public void onBindViewHolder(AudioItemsViewHolder holder, int position) {
        Ring ring = audioItems.get(position);
        if (position == playingPosition) {
            playingHolder = holder;

            updatePlayingView();
        } else {

            updateNonPlayingView(holder);
        }

        holder.tvNameRing.setText(ring.getTitle());
        holder.ivIconRing.setImageResource(ring.getId());
        holder.ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, holder.ivMore);
                popup.getMenuInflater()
                        .inflate(R.menu.bottom_item, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.nav_share:
                                File dest = Environment.getExternalStorageDirectory();
                                InputStream in = context.getResources().openRawResource(ring.getMp3());

                                try
                                {
                                    OutputStream out = new FileOutputStream(new File(dest, "lastshared.mp3"));
                                    byte[] buf = new byte[1024];
                                    int len;
                                    while ( (len = in.read(buf, 0, buf.length)) != -1)
                                    {
                                        out.write(buf, 0, len);
                                    }
                                    in.close();
                                    out.close();
                                }
                                catch (Exception e) {}

                                Intent share = new Intent(Intent.ACTION_SEND);
                                share.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory().toString() + "/lastshared.mp3"));
                                share.setType("audio/*");
                                context.startActivity(Intent.createChooser(share, ""));
                                break;
                            case R.id.nav_ring_phone:
                                Toast.makeText(context, "Chức năng chuẩn bị phát triển", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.nav_like:
                                Toast.makeText(context, "Chức năng đang phát triển", Toast.LENGTH_LONG).show();

                                break;
                        }
//
                        return true;
                    }
                });

                popup.show();

            }
        });

            mediaPlayer = MediaPlayer.create(context, ring.getMp3());
            SimpleDateFormat dangGio = new SimpleDateFormat("mm:ss");
            holder.tvTime.setText((dangGio.format(mediaPlayer.getDuration())));

    }

    private void setRingtone(int ringtoneType, Ring itemRingtone, File fileRing) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DATA, fileRing.getAbsolutePath());
        values.put(MediaStore.MediaColumns.TITLE, itemRingtone.getTitle());
        values.put(MediaStore.MediaColumns.SIZE, fileRing.length());
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");

        switch (ringtoneType) {
            case RingtoneManager.TYPE_RINGTONE:
                values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
                values.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
                values.put(MediaStore.Audio.Media.IS_ALARM, false);
                values.put(MediaStore.Audio.Media.IS_MUSIC, false);
                break;
            case RingtoneManager.TYPE_NOTIFICATION:
                values.put(MediaStore.Audio.Media.IS_RINGTONE, false);
                values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
                values.put(MediaStore.Audio.Media.IS_ALARM, false);
                values.put(MediaStore.Audio.Media.IS_MUSIC, false);
                break;
            case RingtoneManager.TYPE_ALARM:
                values.put(MediaStore.Audio.Media.IS_RINGTONE, false);
                values.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
                values.put(MediaStore.Audio.Media.IS_ALARM, true);
                values.put(MediaStore.Audio.Media.IS_MUSIC, false);
                break;

        }


        Uri uri = MediaStore.Audio.Media.getContentUriForPath(fileRing.getAbsolutePath());
        context.getContentResolver().delete(uri, MediaStore.MediaColumns.DATA + "=\"" + fileRing.getAbsolutePath() + "\"", null);
        Uri newUri = context.getContentResolver().insert(uri, values);
        if (newUri == null) {
            setRingtone(ringtoneType, itemRingtone, fileRing);
            return;
        }
        try {
            RingtoneManager.setActualDefaultRingtoneUri(context, ringtoneType, newUri);
            String ringType = "default ";
            switch (ringtoneType) {
                case RingtoneManager.TYPE_RINGTONE:
                    ringType = ringType + " Ringtone";
                    break;
                case RingtoneManager.TYPE_NOTIFICATION:
                    ringType = ringType + " Notification";
                    break;
                case RingtoneManager.TYPE_ALARM:
                    ringType = ringType + " Alarm";
                    break;

            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return audioItems.size();
    }

    @Override
    public void onViewRecycled(AudioItemsViewHolder holder) {
        super.onViewRecycled(holder);
        if (playingPosition == holder.getAdapterPosition()) {

            updateNonPlayingView(playingHolder);
            playingHolder = null;
        }
    }


    private void updateNonPlayingView(AudioItemsViewHolder holder) {
        if (holder == playingHolder) {
        }
        holder.ivPlayPause.setImageResource(R.drawable.play_ring_tune);
    }


    private void updatePlayingView() {
        if (mediaPlayer.isPlaying()) {
            playingHolder.ivPlayPause.setImageResource(R.drawable.icon_pause);
        } else {
            playingHolder.ivPlayPause.setImageResource(R.drawable.play_ring_tune);
        }
    }

    void stopPlayer() {
        if (null != mediaPlayer) {
            releaseMediaPlayer();
        }
    }

    public class AudioItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivPlayPause, ivIconRing, ivMore;
        TextView tvNameRing, tvTime;

        AudioItemsViewHolder(View itemView) {
            super(itemView);
            ivIconRing = (ImageView) itemView.findViewById(R.id.icon_ring);
            ivPlayPause = (ImageView) itemView.findViewById(R.id.img_play_ring);
            ivPlayPause.setOnClickListener(this);
            tvNameRing = (TextView) itemView.findViewById(R.id.tv_name_ring);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time_ring);
            ivMore = itemView.findViewById(R.id.more_ring);

        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() == playingPosition) {
                // toggle between play/pause of audio
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
            } else {
                // start another audio playback
                playingPosition = getAdapterPosition();
                if (mediaPlayer != null) {
                    if (null != playingHolder) {
                        updateNonPlayingView(playingHolder);
                    }
                    mediaPlayer.release();
                }
                playingHolder = this;
                startMediaPlayer(audioItems.get(playingPosition).getMp3());
            }
            updatePlayingView();
        }



    }

    private void startMediaPlayer(int audioResId) {
        mediaPlayer = MediaPlayer.create(context, audioResId);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                releaseMediaPlayer();
            }
        });
        mediaPlayer.start();
    }

    private void releaseMediaPlayer() {
        if (null != playingHolder) {
            updateNonPlayingView(playingHolder);
        }
        mediaPlayer.release();
        mediaPlayer = null;
        playingPosition = -1;
    }

}


