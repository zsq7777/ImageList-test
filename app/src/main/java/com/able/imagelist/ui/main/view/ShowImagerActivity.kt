package com.able.imagelist.ui.main.view

import android.app.WallpaperManager
import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.able.imagelist.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.activity_show_imager.*

class ShowImagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_imager)
        val iv = findViewById<ImageView>(R.id.iv)
        Glide.with(this).load(intent.getStringExtra("url"))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .placeholder(R.mipmap.ic_launcher)
            .transition(
                DrawableTransitionOptions().crossFade()
            )
            .into(iv)
        btn.setOnClickListener {


            Thread{
                val wpm: WallpaperManager =
                    getSystemService(Context.WALLPAPER_SERVICE) as WallpaperManager
                val bitmap=Glide.with(this).asBitmap().load(intent.getStringExtra("url")).submit().get()
                wpm.setBitmap(bitmap)
            }.start()

        }
    }
}
