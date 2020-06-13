package com.sa.demo.exoxycarouseldemo.modal

import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatImageView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.github.ybq.android.spinkit.SpinKitView
import com.sa.demo.exoxycarouseldemo.R
import com.sa.demo.exoxycarouseldemo.helper.KotlinHolder

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 8/6/20
 */

@EpoxyModelClass(layout = R.layout.item_video_list)
abstract class ItemVideoModel : EpoxyModelWithHolder<VideoHolder>() {

    @EpoxyAttribute
    lateinit var videoPath: String

    @EpoxyAttribute
    var drawableRes: Int = 0

    @EpoxyAttribute
    @ColorInt
    var color: Int = 0

    @EpoxyAttribute
    var position: Int = 0

    override fun bind(holder: VideoHolder) {
        //holder.mediaContainer.setBackgroundColor(color)
        //holder.glide.loadImage(drawableRes, true).into(holder.thumbnail)
        holder.glide.asDrawable().load(drawableRes).centerCrop().into(holder.thumbnail)
    }


    override fun unbind(holder: VideoHolder) {
        holder.glide.clear(holder.thumbnail)
        holder.thumbnail.setImageDrawable(null)
    }

}

class VideoHolder : KotlinHolder() {

    override fun bindView(itemView: View) {
        super.bindView(itemView)
        this@VideoHolder.itemView = itemView
    }

    lateinit var itemView: View
    val thumbnail by bind<AppCompatImageView>(R.id.iv_thumbnail)
    val progressBar by bind<SpinKitView>(R.id.spin_kit)
    val glide by lazy { Glide.with(thumbnail.context) }
    val mediaContainer by bind<FrameLayout>(R.id.fl_container)
}