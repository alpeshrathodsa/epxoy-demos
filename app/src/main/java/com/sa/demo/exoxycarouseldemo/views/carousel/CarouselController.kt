package com.sa.demo.exoxycarouseldemo.views.carousel

import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.Typed2EpoxyController
import com.sa.demo.exoxycarouseldemo.data.EnumItemType
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

    val customIndicatorController = CustomIndicatorCarouselModel.CustomCarouselController()

    override fun buildModels(listProfile: List<Profile>?, hasLoadMore: Boolean?) {
        listProfile?.forEachIndexed { index, profile ->
            itemHeader {
                id("Header ${profile.id} ${profile.name}")
                title(profile.name)
            }


            when (profile.type) {
                EnumItemType.CAROUSEL_LIST_DEFAULT -> {
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

                    //without indicator added using carousel builder
                    /*carousel {
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
                    }*/

                    /*=============================================OR add manually as below=============================================*/

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

                EnumItemType.CAROUSEL_LIST_INDICATOR -> {
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
                EnumItemType.CAROUSEL_LIST_INDICATOR_CUSTOM -> {
                    customIndicatorCarousel {
                        id(profile.id)
                        controller(customIndicatorController)
                        data(profile.image)
                    }
                }

                EnumItemType.CAROUSEL_LIST_GROUP -> {
                    //need to create custom carousel
                    add(ItemListGroupCarouselModel(profile, true, callback))
                }

                EnumItemType.CAROUSEL_LIST_LOAD_MORE -> {
                    //need to create custom carousel
                }

                EnumItemType.CAROUSEL_GRID_DEFAULT -> {
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

                EnumItemType.CAROUSEL_GRID_GROUP -> {
                    add(ItemGridGroupCarouselModel(profile))
                }
            }
        }

        hasLoadMore?.let {
            ItemLoaderModel_()
                .id("loading").addIf(hasLoadMore, this)
        }
    }
}