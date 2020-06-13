package com.sa.demo.exoxycarouseldemo.views.carousel

import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.Typed2EpoxyController
import com.airbnb.epoxy.carousel
import com.sa.demo.exoxycarouseldemo.data.ITEM_TYPE
import com.sa.demo.exoxycarouseldemo.data.Profile
import com.sa.demo.exoxycarouseldemo.modal.*
import timber.log.Timber
import java.util.*

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 29/5/20
 */
class CarouselController(private val callback: AdapterCallbacks) : Typed2EpoxyController<List<Profile>, Boolean>() {
    interface AdapterCallbacks {
        fun onLoadMore(profile: Profile, page: Int, totalItemsCount: Int)
        fun onDeleteClicked(profile: Profile, imagePosition: Int)
    }

    override fun buildModels(listProfile: List<Profile>?, hasLoadMore: Boolean?) {
        listProfile?.forEachIndexed { index, profile ->
            itemHeader {
                id("Header ${profile.id} ${profile.name}")
                title(profile.name)
            }


            when (profile.type) {
                ITEM_TYPE.CAROUSEL_LIST_BUILDER -> {
                    val imageItemModels = arrayListOf<ItemListModel_>()
                    profile.image.forEachIndexed { builderIndex, url ->
                        imageItemModels.add(
                            ItemListModel_()
                                .id("$url $builderIndex ${profile.id}")
                                .imageUrl(url)
                                .position(builderIndex)
                                .preloading(true)
                                .clickListener { model, parentView, clickedView, position ->
                                    callback.onDeleteClicked(profile, position)
                                }
                                .onBind { model, view, position ->
                                    Timber.e("carousel : $index carousel-item-index : ${model.position()} ========>OnBind()")
                                }
                        )
                    }

                    //added using carousel builder
                    carousel {
                        id(profile.id)
                        numViewsToShowOnScreen(1.2F)
                        padding(Carousel.Padding.dp(0, 4, 0, 16, 8))
                        models(imageItemModels)
                        onBind { model, view, position ->
                            Timber.e("carousel-index : $index ========>OnBind()")
                            //here you can bind scroll listener using view
                        }
                        onUnbind { model, view ->
                            Timber.e("carousel-index : $index ========>OnUnBind()")
                            //if any listener added in unbind please unbind here
                        }
                    }
                }

                ITEM_TYPE.CAROUSEL_LIST_MANUAL -> {
                    val imageItemModels = arrayListOf<ItemListModel_>()
                    profile.image.forEachIndexed { manualIndex, url ->
                        imageItemModels.add(
                            ItemListModel_()
                                .id("$url $manualIndex ${profile.id}")
                                .imageUrl(url)
                                .position(manualIndex)
                                .preloading(true)
                                .clickListener { model, parentView, clickedView, position ->
                                    callback.onDeleteClicked(profile, position)
                                }
                                .onBind { model, view, position ->
                                    Timber.e("carousel : $index carousel-item-index : ${model.position()} ========>OnBind()")
                                }
                        )
                    }

                    //without indicator added manually
                    add(
                        CarouselModel_()
                            .id(profile.id)
                            .numViewsToShowOnScreen(1.2F)
                            .padding(Carousel.Padding.dp(0, 4, 0, 16, 8))
                            .models(imageItemModels)
                            .onBind { model, view, position ->
                                Timber.e("carousel-index : $index ========>OnBind()")
                            }
                            .onUnbind { model, view ->
                                Timber.e("carousel-index : $index ========>OnUnBind()")
                            }
                    )
                }

                ITEM_TYPE.CAROUSEL_LIST_INDICATOR -> {
                    val imageItemModels = arrayListOf<ItemListModel_>()
                    profile.image.forEachIndexed { indicatorIndex, url ->
                        imageItemModels.add(
                            ItemListModel_()
                                .id("$url $indicatorIndex ${profile.id}")
                                .imageUrl(url)
                                .position(indicatorIndex)
                                .preloading(true)
                                .clickListener { model, parentView, clickedView, position ->
                                    callback.onDeleteClicked(profile, position)
                                }
                                .onBind { model, view, position ->
                                    Timber.e("carousel : $index carousel-item-index : ${model.position()} ========>OnBind()")
                                }
                        )
                    }

                    //with indicator added manually
                    add(
                        ItemIndicatorModel()
                            .id(profile.id)
                            .numViewsToShowOnScreen(1.2F)
                            .padding(Carousel.Padding.dp(0, 4, 0, 16, 8))
                            .models(imageItemModels)
                            .onBind { model, view, position ->
                                Timber.e("carousel-index : $index ========>OnBind()")
                            }
                            .onUnbind { model, view ->
                                Timber.e("carousel-index : $index ========>OnUnBind()")
                            }
                    )
                }

                ITEM_TYPE.CAROUSEL_LIST_GROUP_LOAD_MORE -> {
                    //need to create custom carousel
                    add(ItemListGroupCarouselModel(profile, true, callback))
                }

                ITEM_TYPE.CAROUSEL_GRID_GROUP -> {
                    add(ItemGridGroupCarouselModel(profile))
                }

                ITEM_TYPE.CAROUSEL_GRID_MANUAL -> {
                    val urls: List<String> = profile.image
                    val gridModals: MutableList<ItemGridModel_> = ArrayList()
                    profile.image.forEachIndexed { gridManualIndex, url ->
                        gridModals.add(
                            ItemGridModel_()
                                .id(url, profile.id.toLong())
                                .imageUrl(url)
                                .preloading(true)
                        )
                    }
                    add(
                        GridCarouselModel_()
                            .numViewsToShowOnScreen(2.5f)
                            .id(profile.id.toLong())
                            .title(profile.name)
                            .models(gridModals)
                            .onBind { model: GridCarouselModel_?, view: GridCarousel?, position: Int -> }
                            .onUnbind { model, view -> }
                    )


                }
            }
        }

        hasLoadMore?.let {
            ItemLoaderModel_()
                .id("loading").addIf(hasLoadMore, this)
        }
    }
}