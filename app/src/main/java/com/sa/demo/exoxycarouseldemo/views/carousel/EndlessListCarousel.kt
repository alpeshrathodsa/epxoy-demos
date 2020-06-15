package com.sa.demo.exoxycarouseldemo.views.carousel

import android.content.Context
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 15/6/20
 */

@ModelView(saveViewState = true, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
open class EndlessListCarousel(ctx: Context) : Carousel(ctx) {

    // The minimum amount of items to have below your current scroll position
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


    private var parentPosition: Int = -1

    @ModelProp(options = [ModelProp.Option.DoNotHash])
    open fun setParentPosition(position: Int) {
        parentPosition = position
    }

    private var totalResults: Int = 0

    @ModelProp(options = [ModelProp.Option.DoNotHash])
    open fun setTotalResult(count: Int) {
        totalResults = count
    }

    var adapterCallback: CarouselController.AdapterCallbacks? = null
        @CallbackProp set


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

    override fun onScrolled(dx: Int, dy: Int) {
        check(totalResults >= 1) { "totalResults must be greater than 0" }
        check(parentPosition >= 1) { "parentPosition must be greater than or equal to 0" }


        //super.onScrolled(dx, dy)
        var lastVisibleItemPosition = 0
        val totalItemCount: Int? = layoutManager?.itemCount

        lastVisibleItemPosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount!! < previousTotalItemCount) {
            currentPage = startingPageIndex
            previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                loading = true
            }
        }

        //do not continue if we have already loaded all items
        if (previousTotalItemCount >= totalResults) return


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
        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            currentPage++
            adapterCallback?.onLoadMore(parentPosition, currentPage, totalItemCount)
            loading = true
        }
    }
}