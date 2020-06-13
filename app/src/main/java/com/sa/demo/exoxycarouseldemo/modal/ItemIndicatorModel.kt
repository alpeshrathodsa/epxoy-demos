package com.sa.demo.exoxycarouseldemo.modal

import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.CarouselModel_
import com.sa.demo.exoxycarouseldemo.utils.CirclePagerIndicatorDecoration

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 10/6/20
 */
class ItemIndicatorModel : CarouselModel_() {

    val indicator = CirclePagerIndicatorDecoration()

    override fun bind(carousel: Carousel) {
        super.bind(carousel)

        carousel.addItemDecoration(indicator)
    }

    override fun unbind(carousel: Carousel) {
        super.unbind(carousel)

        carousel.removeItemDecoration(indicator)
    }
}