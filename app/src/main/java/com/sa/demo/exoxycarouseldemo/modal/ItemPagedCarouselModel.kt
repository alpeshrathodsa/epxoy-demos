package com.sa.demo.exoxycarouseldemo.modal

import com.airbnb.epoxy.*
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.sa.demo.exoxycarouseldemo.R
import com.sa.demo.exoxycarouseldemo.data.Movie
import com.sa.demo.exoxycarouseldemo.helper.KotlinHolder

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 13/6/20
 */


@EpoxyModelClass(layout = R.layout.item_paged_carousel)
abstract class ItemPagedCarouselModel : EpoxyModelWithHolder<ItemPagedCarouselModel.CarouselHolder>() {

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var controller: CarouselController

    override fun bind(holder: CarouselHolder) {
        holder.carousel.setController(controller)
    }

    override fun unbind(holder: CarouselHolder) {
        holder.carousel.clear()
    }

    override fun shouldSaveViewState(): Boolean {
        return true
    }

    class CarouselHolder : KotlinHolder() {
        val carousel by bind<Carousel>(R.id.carousel)
    }

    class CarouselController : PagedListEpoxyController<Movie>() {
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

        override fun addModels(models: List<EpoxyModel<*>>) {
            if (isLoading) {
                super.addModels(
                    models.plus(
                        //Error View Model
                        ItemLoaderModel_()
                            .id("loading")
                    ).distinct()
                )
            } else {
                super.addModels(models.distinct())
            }
        }
    }
}