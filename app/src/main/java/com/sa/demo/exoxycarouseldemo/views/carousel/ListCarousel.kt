package com.sa.demo.exoxycarouseldemo.views.carousel

import android.content.Context
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.sa.demo.exoxycarouseldemo.data.Profile


/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 10/6/20
 */
@ModelView(saveViewState = true, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
open class ListCarousel(ctx: Context) : Carousel(ctx) {

    override fun createLayoutManager(): LayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun getSnapHelperFactory(): SnapHelperFactory? {
        return null;
    }

    @Nullable
    override fun getLayoutManager(): LayoutManager? {
        return super.getLayoutManager()
    }

    @ModelProp
    lateinit var profile: Profile

    var adapterCallback: CarouselController.AdapterCallbacks? = null
        @CallbackProp set

    /*// The minimum amount of items to have below your current scroll position
    // before loading more.
    private val visibleThreshold = 5

    // The current offset index of data you have loaded
    private var currentPage = 0

    // The total number of items in the dataset after the last load
    private var previousTotalItemCount = 0

    // True if we are still waiting for the last set of data to load.
    private var loading = true

    // Sets the starting page index
    private val startingPageIndex = 0


    open fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    override fun onScrolled(dx: Int, dy: Int) {
        //super.onScrolled(dx, dy)
        var lastVisibleItemPosition = 0
        val totalItemCount: Int? = layoutManager?.itemCount

        lastVisibleItemPosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount!! < previousTotalItemCount) {
            currentPage = startingPageIndex
            previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                loading = true
            }
        }
        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            currentPage++
            if (currentPage < 2) {
                adapterCallback?.onLoadMore(profile, currentPage, totalItemCount)
                loading = true
            }
        }
    }*/


    // 2. Or you can use lateinit
    /*@TextProp
    lateinit var title: CharSequence*/
    // 1.You can make it nullable like this and annotate the setter
    /*var adapterCallback: CarouselController.AdapterCallbacks? = null
        @CallbackProp set

    @AfterPropsSet
    fun useProps() {
        addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                //Log.d("OnBind${profile.id} : ", "Has More $index")
                if (page < 2)//only for testing purpose to stop load more after loading once
                    adapterCallback?.onLoadMore(profile, page, totalItemsCount)
            }
        })
    }*/


    /*@CallbackProp
    fun adapterCallback(callback: CarouselController.AdapterCallbacks?) {
        super.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                //Log.d("OnBind${profile.id} : ", "Has More $index")
                if (page < 2)//only for testing purpose to stop load more after loading once
                    callback?.onLoadMore(profile, page, totalItemsCount)
            }
        })
    }*/
}