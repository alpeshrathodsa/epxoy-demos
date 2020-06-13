package com.sa.demo.exoxycarouseldemo.views

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.sa.demo.exoxycarouseldemo.R
import com.sa.demo.exoxycarouseldemo.views.carousel.CarouselDemoActivity
import com.sa.demo.exoxycarouseldemo.views.movie.MovieListActivity
import com.sa.demo.exoxycarouseldemo.views.preload.ImagePreloadActivity
import com.sa.demo.exoxycarouseldemo.views.video.VideoListActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_image_preload.setOnClickListener {
            val intent = Intent(this, ImagePreloadActivity::class.java)
            startActivity(intent)
        }

        btn_paging_demo.setOnClickListener {
            val intent = Intent(this, MovieListActivity::class.java)
            startActivity(intent)
        }

        btn_carousel_demo.setOnClickListener {
            val intent = Intent(this, CarouselDemoActivity::class.java)
            startActivity(intent)
        }

        btn_video_demo.setOnClickListener {
            val intent = Intent(this, VideoListActivity::class.java)
            startActivity(intent)
        }

    }
}

fun RequestManager.loadImage(url: String, isPreloading: Boolean): RequestBuilder<Bitmap> {
    val options = RequestOptions
        .diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC)
        .centerCrop()
        .dontAnimate()
        .signature(ObjectKey(url.plus(if (isPreloading) "_preloading" else "_not_preloading")))

    return asBitmap()
        .apply(options)
        .load(url)
}
