package com.sa.demo.exoxycarouseldemo.modal

import com.airbnb.epoxy.*
import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator
import com.sa.demo.exoxycarouseldemo.R
import java.util.*

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 *
 * Created on 10/6/20
 */
@EpoxyModelClass(layout = R.layout.item_group_carousel)
abstract class GroupListCarouselModel(models: ArrayList<EpoxyModel<*>?>?) : EpoxyModelGroup(R.layout.item_group_carousel, models) {

    override fun bind(holder: ModelGroupHolder) {
        super.bind(holder)
        val carousel: Carousel = holder.viewHolders[0].itemView as Carousel
        val indicator: IndefinitePagerIndicator = holder.viewHolders[1].itemView as IndefinitePagerIndicator
        indicator.attachToRecyclerView(carousel)
    }
    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}