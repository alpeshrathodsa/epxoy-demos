package com.sa.demo.exoxycarouseldemo.views.carousel

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp


/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 29/5/20
 */
@ModelView(saveViewState = true, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
open class GridCarousel(ctx: Context) : Carousel(ctx) {

    override fun createLayoutManager(): LayoutManager {
        return GridLayoutManager(context, 2, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun getSnapHelperFactory(): SnapHelperFactory? {
        return null;
    }

    // 2. Or you can use lateinit
    @TextProp
    lateinit var title: CharSequence

    /*// 1.You can make it nullable like this and annotate the setter
    var scrollListener: OnScrollListener? = null
        @CallbackProp set

    @AfterPropsSet
    fun userProps() {
        scrollListener?.let {
            this.addOnScrollListener(it)
        }
    }*/


    //uncomment below code when you want to add scroll listener
    /*
    private var position = 0

    @ModelProp
    fun setPosition(position: Int) {
        this.position = position
    }

    @ModelProp(options = [ModelProp.Option.DoNotHash])
    fun addCallback(callback: CarouselController.AdapterCallbacks) {
        super.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                //Log.d("OnBind${profile.id} : ", "Has More $index")
                callback.onLoadMore(position, page, totalItemsCount)
            }
        })
    }*/

}