package com.example.santa2019;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InCallStanta extends Activity {
    private Camera.PictureCallback mPicture;
    private boolean cameraFront = false;
   ImageView cance, switchCam, santan;
   MediaPlayer mp;
  private Boolean fontcam = true;
    private Camera mCamera;
    private CameraPreview mPreview;
    private Context myContext;
    private LinearLayout cameraPreview;


    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
        mCamera.release();
    }
    protected void onStop() {
        super.onStop();
        mp.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_call_stanta);
        santan = (ImageView) findViewById(R.id.im_gif);

        cameraPreview = (LinearLayout) findViewById(R.id.cPreview);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        myContext = this;


        mCamera =  Camera.open(1);
        mCamera.setDisplayOrientation(90);
        mPreview = new CameraPreview(myContext, mCamera);
        cameraPreview.addView(mPreview);
        mCamera.startPreview();




        List<String> santaList = new ArrayList<String>();

        santaList.add("https://media.giphy.com/media/9JnU6uRRblKM3hGzu3/giphy.gif");
        santaList.add("http://giphygifs.s3.amazonaws.com/media/5xtDaryAMLjvAyN4eiY/giphy.gif");
        santaList.add("https://media.giphy.com/media/dJ47OiaxekzODjyWUq/giphy.gif");
        santaList.add("https://media.giphy.com/media/5h7tcpS1O2ojo48DzX/giphy.gif");

       int randomIndex = new Random().nextInt(santaList.size());
        String randomName = santaList.get(randomIndex);

        Glide.with(this).asGif().load(randomName).placeholder(R.drawable.image14).into(santan);
//        webView = (WebView) findViewById(R.id.im_gif);
//        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        webView.getSettings().setLoadsImagesAutomatically(true);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl("https://media.giphy.com/media/9JnU6uRRblKM3hGzu3/giphy.gif");
        List<Integer> soundList = new ArrayList<Integer>();
        soundList.add(R.raw.onggianoel1);
        soundList.add(R.raw.noe2);
        soundList.add(R.raw.noel3);
        soundList.add(R.raw.noel1);
        soundList.add(R.raw.onggianoel2);
        int randomInt = (new Random().nextInt(soundList.size()));
        int sound = soundList.get(randomInt);
        mp = MediaPlayer.create(this, sound);
        mp.start();

//        MediaPlayer mediaPlayer = MediaPlayer.create(InCallStanta.this, R.raw.noel1);
//        mediaPlayer.start();


        cance = (ImageView) findViewById(R.id.huy);
        switchCam = (ImageView) findViewById(R.id.swicthcame);
//       switchCam.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               if(fontcam==true){
//                   mCamera =  Camera.open(0);
//                   mCamera.setDisplayOrientation(90);
//                   mPreview = new CameraPreview(myContext, mCamera);
//                   cameraPreview.addView(mPreview);
//                   fontcam=false;
//               }else if (fontcam == false){
//                   mCamera =  Camera.open(1);
//                   mCamera.setDisplayOrientation(90);
//                   mPreview = new CameraPreview(myContext, mCamera);
//                   cameraPreview.addView(mPreview);
//                   fontcam=true;
//               }
//           }
//       });
        switchCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the number of cameras
                int camerasNumber = Camera.getNumberOfCameras();
                if (camerasNumber > 1) {
                    //release the old camera instance
                    //switch camera, from the front and the back and vice versa

                    releaseCamera();
                    chooseCamera();
                } else {

                }
            }
        });

        cance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                Intent intent = new Intent(InCallStanta.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void releaseCamera() {
        // stop and release camera
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }
    public void chooseCamera() {
        //if the camera preview is the front
        if (cameraFront) {
            int cameraId = findBackFacingCamera();
            if (cameraId >= 0) {
                //open the backFacingCamera
                //set a picture callback
                //refresh the preview

                mCamera = Camera.open(cameraId);
                mCamera.setDisplayOrientation(90);
                mPreview.refreshCamera(mCamera);
            }
        } else {
            int cameraId = findFrontFacingCamera();
            if (cameraId >= 0) {
                //open the backFacingCamera
                //set a picture callback
                //refresh the preview
                mCamera = Camera.open(cameraId);
                mCamera.setDisplayOrientation(90);
                mPreview.refreshCamera(mCamera);
            }
        }
    }
    private int findBackFacingCamera() {
        int cameraId = -1;
        //Search for the back facing camera
        //get the number of cameras
        int numberOfCameras = Camera.getNumberOfCameras();
        //for every camera check
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                cameraFront = false;
                break;

            }

        }
        return cameraId;
    }

    private int findFrontFacingCamera() {

        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                cameraFront = true;
                break;
            }
        }
        return cameraId;

    }
}
