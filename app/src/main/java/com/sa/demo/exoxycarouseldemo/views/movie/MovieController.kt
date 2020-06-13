package com.sa.demo.exoxycarouseldemo.views.movie

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.sa.demo.exoxycarouseldemo.data.Movie
import com.sa.demo.exoxycarouseldemo.modal.ItemListModel_
import com.sa.demo.exoxycarouseldemo.modal.ItemLoaderModel_
import com.sa.demo.exoxycarouseldemo.modal.itemHeader


/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/6/20
 */
class MovieController : PagedListEpoxyController<Movie>() {

    var isLoading = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    override fun buildItemModel(currentPosition: Int, item: Movie?): EpoxyModel<*> {
        item?.let {
            return ItemListModel_()
                .id("movie${currentPosition}")
                .imageUrl("http://image.tmdb.org/t/p/w185/${item.posterPath}")
                .preloading(true)
        } ?: kotlin.run {
            return ItemLoaderModel_()
                .id("loading")
        }
    }

    /**
     * Adding models
     */
    override fun addModels(models: List<EpoxyModel<*>>) {

        itemHeader {
            id("horizontal_carousel")
            title("Vertical Paged List")
        }

        if (isLoading) {
            super.addModels(
                models.plus(
                    //Error View Model
                    ItemLoaderModel_().id("loading")
                ).distinct()
            )
        } else {
            super.addModels(models.distinct())
        }
    }

}