package com.example.santa2019

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_in_call_stanta.*
import java.util.*

class SantaGo : AppCompatActivity() {
    lateinit var santa:ImageView
    lateinit var mp: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_call_stanta)
        mp = MediaPlayer.create(this, R.raw.xuathien)
        mp.start()
//        webView = findViewById<View>(R.id.im_gif) as WebView
//        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)
//        webView.getSettings().setLoadsImagesAutomatically(true)
//        webView.getSettings().setJavaScriptEnabled(true)
//        webView.loadUrl("https://media.giphy.com/media/KDH9hJ2mSEUYU/giphy.gif")

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
    }

    override fun onStop() {
        super.onStop()
        mp.stop()
    }
}
