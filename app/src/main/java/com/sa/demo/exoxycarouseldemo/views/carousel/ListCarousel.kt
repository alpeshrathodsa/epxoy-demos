package com.sa.demo.exoxycarouseldemo.views.carousel

import android.content.Context
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView


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

    // 2. Or you can use lateinit
    /*@TextProp
    lateinit var title: CharSequence*/
    // 1.You can make it nullable like this and annotate the setter
    /*var adapterCallback: CarouselController.AdapterCallbacks? = null
        @CallbackProp set

    @AfterPropsSet
    fun useProps() {

    }*/
}