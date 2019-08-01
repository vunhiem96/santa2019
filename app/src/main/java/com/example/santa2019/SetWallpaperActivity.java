package com.example.santa2019;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.santa2019.Model.Image;
import com.example.santa2019.Model.WallpaperFavorites;
import com.example.santa2019.db.DatabaseHandler;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.DataFormatException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetWallpaperActivity extends AppCompatActivity {
    DatabaseHandler db;
    int id;
    Boolean  isLike = false;

    @BindView(R.id.imgDemoImage)
    ImageView imgDemoImage;
    @BindView(R.id.layoutLock)
    LinearLayout layoutLock;
    @BindView(R.id.layoutHome)
    RelativeLayout layoutHome;
    @BindView(R.id.btndislike)
    RelativeLayout btndislike;
    @BindView(R.id.imgDislike)
    ImageView imgDislike;
    @BindView(R.id.layoutMenuTop)
    RelativeLayout layoutMenuTop;
    @BindView(R.id.layoutMenu)
    RelativeLayout layoutMenu;
    int counter=1;
    @BindView(R.id.View)
    RelativeLayout view;
    ArrayList<Image> contacts;

    @OnClick(R.id.btnBack) void back() {
       finish();
    }
    @OnClick(R.id.btnshare) void share() {

        Drawable mDrawable = imgDemoImage.getDrawable();
        Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();

        String path = MediaStore.Images.Media.insertImage(getContentResolver(), mBitmap, "Image Description", null);
        Uri uri = Uri.parse(path);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(intent, "Share Image"));
    }
    @OnClick(R.id.btnSetHomeScreen) void setHomeScreen() {

        if (layoutLock.getVisibility()==View.VISIBLE){
            layoutLock.setVisibility(View.GONE);
        }
        layoutHome.setVisibility(View.VISIBLE);

    }
    @OnClick(R.id.btnSetLockScreen) void setLockScreen() {
        if (layoutHome.getVisibility()==View.VISIBLE){
            layoutHome.setVisibility(View.GONE);
        }
        layoutLock.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnSave) void save() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Picasso.with(getApplicationContext())
                        .load(R.drawable.a10)
                        .into(new Target() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
                                File myDir = new File(root + "/saved_images_1");
                                myDir.mkdirs();
                                Random generator = new Random();
                                int n = 10000;
                                n = generator.nextInt(n);
                                String fname = "Image-" + n + ".jpg";
                                File file = new File(myDir, fname);
                                if (file.exists())
                                    file.delete();
                                try {
                                    FileOutputStream out = new FileOutputStream(file);
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                                    out.flush();
                                    out.close();
                                    Toast.makeText(SetWallpaperActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();

                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }


                                // Tell the media scanner about the new file so that it is
                                // immediately available to the user.
                                MediaScannerConnection.scanFile(SetWallpaperActivity.this, new String[]{file.toString()}, null,
                                        new MediaScannerConnection.OnScanCompletedListener() {
                                            public void onScanCompleted(String path, Uri uri) {
                                                Log.i("ExternalStorage", "Scanned " + path + ":");
                                                Log.i("ExternalStorage", "-> uri=" + uri);
                                            }
                                        });
                            }

                            @Override
                            public void onBitmapFailed(Drawable errorDrawable) {

                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {

                            }
                        });

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(SetWallpaperActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }


        };
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("Ứng dụng cần quyền truy cập vào Thư Viện ")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();

    }

    @OnClick(R.id.btndislike) void favorite() {
        contacts = new ArrayList<Image>(db.getAllContacts());
        if (isLike){
            for (int i=0 ;i<contacts.size();i++){
                if (contacts.get(i).getId()==id){
                    db.deleteContact(id);
                    imgDislike.setBackgroundResource(R.drawable.dislike);
                    isLike = false;
                }
            }

        }
        else {
            db.addContacts(new Image(id));
            imgDislike.setBackgroundResource(R.drawable.like);
            isLike = true;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_set_wallpaper);
        ButterKnife.bind(SetWallpaperActivity.this);
        db=new DatabaseHandler(this);

        getDada();
       // getallDb();
    }
    private void getDada() {
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("key");

        Picasso.with(this)
                .load(id)
                .fit().centerCrop()
                .into(imgDemoImage);
    }
    private void getallDb() {
        contacts = new ArrayList<>(db.getAllContacts());
        Log.e("thanhdb",contacts.size()+"");
        for (int i=0 ;i<contacts.size();i++){
            if (contacts.get(i).getId()==id){
                db.deleteContact(id);
                imgDislike.setBackgroundResource(R.drawable.dislike);
                isLike = false;
            }
        }
    }


}
