package com.sa.demo.exoxycarouseldemo.modal

import android.view.View
import android.widget.ImageView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModel
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
@EpoxyModelClass(layout = R.layout.item_grid)
abstract class ItemGridModel : EpoxyModelWithHolder<GridImageHolder>() {

    @EpoxyAttribute
    var imageUrl: String = ""

    @EpoxyAttribute
    var preloading: Boolean = false

    override fun bind(holder: GridImageHolder) {
        holder.glide.loadImage(imageUrl, preloading).into(holder.image)
    }

    override fun bind(holder: GridImageHolder, previouslyBoundModel: EpoxyModel<*>) {
        holder.glide.clear(holder.image)
        holder.image.setImageDrawable(null)
    }

}

class GridImageHolder : KotlinHolder(), Preloadable {
    val image by bind<ImageView>(R.id.image_view)
    val glide by lazy { Glide.with(image.context) }
    override val viewsToPreload: List<View> by lazy { listOf(image) }
}