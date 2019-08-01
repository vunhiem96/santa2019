package com.example.santa2019

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_dem.*

class DemActivity : AppCompatActivity() {
    lateinit var handler:Handler
    lateinit var mp:MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dem)
        hander()
        mp = MediaPlayer.create(this, R.raw.nutdacam)
        mp.start()
        btn_count.setOnClickListener {
            mp.stop()
            mp = MediaPlayer.create(this, R.raw.chintam)
            mp.start()
           cowndown()
        }

    }
    private fun hander() {
        handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    1001 -> tv_time.setText(msg.arg1.toString())
                    1002 ->
                        mp.stop()

                    else -> {
                    }
                }


            }
        }

    }
    private fun cowndown() {
        val thread = Thread(Runnable {
            var time = 10
            do {
                time--
                val msg = Message()
                msg.what = 1001
                msg.arg1 = time
                handler.sendMessage(msg)
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            } while (time > 0)
            handler.sendEmptyMessage(1002)
            val intent = Intent(this@DemActivity, SantaGo::class.java)
            startActivity(intent)
        })
        thread.start()
    }

    override fun onPause() {
        super.onPause()
        mp.stop()
    }

    override fun onStop() {
        super.onStop()
        mp.stop()
    }

}
