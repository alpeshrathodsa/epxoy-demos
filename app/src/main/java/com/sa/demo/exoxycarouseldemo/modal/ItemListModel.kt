package com.sa.demo.exoxycarouseldemo.modal

import android.view.View
import android.widget.ImageView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.airbnb.epoxy.preload.Preloadable
import com.bumptech.glide.Glide
import com.sa.demo.exoxycarouseldemo.R
import com.sa.demo.exoxycarouseldemo.helper.KotlinHolder
import com.sa.demo.exoxycarouseldemo.views.loadImage

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 29/5/20
 */
@EpoxyModelClass(layout = R.layout.item_list)
abstract class ItemListModel : EpoxyModelWithHolder<ImageHolder>() {
    @EpoxyAttribute
    lateinit var imageUrl: String

    @EpoxyAttribute
    var preloading: Boolean = false

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var clickListener: View.OnClickListener? = null

    @EpoxyAttribute
    var position: Int = 0


    override fun bind(holder: ImageHolder) {
        //holder.glide.load(imageUrl).into(holder.image)
        holder.glide.loadImage(imageUrl, preloading).into(holder.image)
        holder.close.setOnClickListener(clickListener)
    }

    override fun unbind(holder: ImageHolder) {
        holder.glide.clear(holder.image)
        holder.image.setImageDrawable(null)
        holder.close.setOnClickListener(null)
    }
}

class ImageHolder : KotlinHolder(), Preloadable {
    val image by bind<ImageView>(R.id.image_view)
    val close by bind<ImageView>(R.id.iv_close)
    val glide by lazy { Glide.with(image.context) }
    override val viewsToPreload: List<View> by lazy { listOf(image) }
}