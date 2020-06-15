package com.sa.demo.exoxycarouseldemo.modal

import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.*
import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator
import com.sa.demo.exoxycarouseldemo.R
import com.sa.demo.exoxycarouseldemo.helper.KotlinHolder
import com.sa.demo.exoxycarouseldemo.utils.EndlessRecyclerViewScrollListener
import timber.log.Timber

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 14/6/20
 */
@EpoxyModelClass(layout = R.layout.model_custom_indicator_carousel)
abstract class CustomIndicatorCarouselModel : EpoxyModelWithHolder<CustomIndicatorCarouselModel.CustomIndicatorCarouselHolder>() {

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var controller: CustomCarouselController

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var data: List<String>


    override fun bind(holder: CustomIndicatorCarouselHolder) {
        holder.carousel.setController(controller)
        holder.indicator.attachToRecyclerView(holder.carousel)

        controller.setData(data, false)

        holder.carousel.addOnScrollListener(object : EndlessRecyclerViewScrollListener(holder.carousel.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                Timber.e("Carousel Page : $page")
            }

        })
    }

    override fun unbind(holder: CustomIndicatorCarouselHolder) {
        holder.indicator.attachToRecyclerView(null)
        holder.carousel.clear()
    }

    override fun shouldSaveViewState(): Boolean {
        return true
    }


    class CustomIndicatorCarouselHolder : KotlinHolder() {
        val carousel by bind<Carousel>(R.id.carousel)
        val indicator by bind<IndefinitePagerIndicator>(R.id.rv_indicator)
    }


    class CustomCarouselController : Typed2EpoxyController<List<String>, Boolean>() {
        override fun buildModels(imageList: List<String>?, loadMore: Boolean?) {
            imageList?.forEachIndexed { index, url ->
                itemList {
                    id("$url $index")
                    imageUrl(url)
                    position(index)
                    preloading(true)
                }
            }

            loadMore?.let {
                if (it)
                    itemLoader {
                        id("loadding")
                    }
            }
        }
    }
}