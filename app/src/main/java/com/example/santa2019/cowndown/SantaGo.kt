package com.example.santa2019.cowndown

import android.content.Context
import android.content.Intent
import android.hardware.Camera
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.santa2019.CameraPreview
import com.example.santa2019.MainActivity
import com.example.santa2019.R
import kotlinx.android.synthetic.main.activity_in_call_stanta.*
import java.util.*

class SantaGo : AppCompatActivity() {
    private var cameraFront = false
    lateinit var santa:ImageView
    lateinit var mp: MediaPlayer
    private var mCamera: Camera? = null
    private var mPreview: CameraPreview? = null
    private var myContext: Context? = null
    private var cameraPreview: LinearLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_call_stanta)
        mp = MediaPlayer.create(this, R.raw.xuathien)
        mp.start()

        cameraPreview = findViewById<View>(R.id.cPreview) as LinearLayout
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        myContext = this
        mCamera = Camera.open(1)
        mCamera!!.setDisplayOrientation(90)
        mPreview = CameraPreview(myContext, mCamera)
        cameraPreview!!.addView(mPreview)
        mCamera!!.startPreview()



        val santaList = ArrayList<String>()

        santaList.add("https://media.giphy.com/media/KDH9hJ2mSEUYU/giphy.gif")
        santaList.add("https://media.giphy.com/media/8UHe7kWeUg0ZwWMNLZ/giphy.gif")
        santaList.add("http://giphygifs.s3.amazonaws.com/media/bMLToKGWvhLeU/giphy.gif")
        santaList.add("https://media.giphy.com/media/26ybuZsHBYL9VpU76/giphy.gif")
        santaList.add("https://media.giphy.com/media/3o751XQV6A7XTxP4FG/giphy.gif")

        val randomIndex = Random().nextInt(santaList.size)
        val randomName = santaList[randomIndex]



        Glide.with(this).asGif().load(randomName).placeholder(R.drawable.image16).into(im_gif)

        huy.setOnClickListener {
            val intent = Intent(this@SantaGo, MainActivity::class.java)
            startActivity(intent)
        }


        swicthcame.setOnClickListener {
                //get the number of cameras
                val camerasNumber = Camera.getNumberOfCameras()
                if (camerasNumber > 1) {
                    //release the old camera instance
                    //switch camera, from the front and the back and vice versa

               releaseCamera()
                    chooseCamera()
                } else {
                }

        }
    }
    private fun releaseCamera() {
        // stop and release camera
        if (mCamera != null) {
            mCamera!!.stopPreview()
            mCamera!!.setPreviewCallback(null)
            mCamera!!.release()
            mCamera = null
        }
    }

    fun chooseCamera() {
        //if the camera preview is the front
        if (cameraFront) {
            val cameraId = findBackFacingCamera()
            if (cameraId >= 0) {
                //open the backFacingCamera
                //set a picture callback
                //refresh the preview

                mCamera = Camera.open(cameraId)
                mCamera!!.setDisplayOrientation(90)
                mPreview!!.refreshCamera(mCamera)
            }
        } else {
            val cameraId = findFrontFacingCamera()
            if (cameraId >= 0) {
                //open the backFacingCamera
                //set a picture callback
                //refresh the preview
                mCamera = Camera.open(cameraId)
                mCamera!!.setDisplayOrientation(90)
                mPreview!!.refreshCamera(mCamera)
            }
        }
    }

    private fun findBackFacingCamera(): Int {
        var cameraId = -1
        //Search for the back facing camera
        //get the number of cameras
        val numberOfCameras = Camera.getNumberOfCameras()
        //for every camera check
        for (i in 0 until numberOfCameras) {
            val info = Camera.CameraInfo()
            Camera.getCameraInfo(i, info)
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i
                cameraFront = false
                break

            }

        }
        return cameraId
    }

    private fun findFrontFacingCamera(): Int {

        var cameraId = -1
        // Search for the front facing camera
        val numberOfCameras = Camera.getNumberOfCameras()
        for (i in 0 until numberOfCameras) {
            val info = Camera.CameraInfo()
            Camera.getCameraInfo(i, info)
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i
                cameraFront = true
                break
            }
        }
        return cameraId

    }
    override fun onPause() {
        super.onPause()
        mCamera!!.release()
    }

    override fun onStop() {
        super.onStop()
        mp.stop()
    }
}
