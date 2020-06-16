package com.sa.demo.exoxycarouseldemo.views.carousel

import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyModel
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
        fun onLoadMore(parentPosition: Int, page: Int, totalItemsCount: Int)
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
                    val listModel = arrayListOf<ItemListModel_>()
                    profile.image.forEachIndexed { builderIndex, url ->
                        listModel.add(
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
                            .models(listModel)
                            .onBind { model, view, position ->
                                Timber.e("carousel-index : $index ========>OnBind()")
                            }
                            .onUnbind { model, view ->
                                Timber.e("carousel-index : $index ========>OnUnBind()")
                            }
                    )
                }

                EnumItemType.CAROUSEL_LIST_INDICATOR -> {
                    val listModel = arrayListOf<ItemListModel_>()
                    profile.image.forEachIndexed { indicatorIndex, url ->
                        listModel.add(
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
                            .models(listModel)
                            .onBind { model, view, position ->
                                Timber.e("carousel-index : $index ========>OnBind()")
                            }
                            .onUnbind { model, view ->
                                Timber.e("carousel-index : $index ========>OnUnBind()")
                            }
                    )
                }

                EnumItemType.CAROUSEL_LIST_GROUP -> {
                    val groupModel: ArrayList<EpoxyModel<*>?>? = arrayListOf()

                    val listModel = arrayListOf<ItemListModel_>()
                    profile.image.forEachIndexed { groupIndex, url ->
                        listModel.add(
                            ItemListModel_()
                                .id("$url $groupIndex ${profile.id}")
                                .imageUrl(url)
                                .position(groupIndex)
                                .preloading(true)
                                .clickListener { model, parentView, clickedView, position ->
                                    //callback.onDeleteClicked(profile, position)
                                    //commented because it will call load more while we delete items
                                }
                                .onBind { model, view, position ->
                                    Timber.e("carousel : $index carousel-item-index : ${model.position()} ========>OnBind()")
                                }
                        )
                    }

                    //1st viewstub
                    groupModel?.add(
                        EndlessListCarouselModel_()
                            .id("carousel")
                            .numViewsToShowOnScreen(1.2F)
                            .padding(Carousel.Padding.dp(0, 4, 0, 16, 8))
                            .parentPosition(index)
                            .totalResult(12)//totla number of items to fetch from db or api
                            .adapterCallback(callback)
                            .models(listModel)
                    )

                    //2nd viewstub
                    groupModel?.add(IndicatorModel_().id("indicator"))

                    //3rd viewstub
                    groupModel?.add(ItemHorizontalLoaderModel_().id("loading").show(profile.hasMore))

                    //add to controller
                    add(GroupListCarouselModel_(groupModel).id(profile.id))
                }

                EnumItemType.CAROUSEL_LIST_INDICATOR_CUSTOM -> {
                    customIndicatorCarousel {
                        id(profile.id)
                        controller(customIndicatorController)
                        data(profile.image)
                    }
                }

                EnumItemType.CAROUSEL_GRID_DEFAULT -> {
                    val gridModel: MutableList<ItemGridModel_> = ArrayList()
                    profile.image.forEachIndexed { gridManualIndex, url ->
                        gridModel.add(
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
                            .models(gridModel)
                            .onBind { model: GridCarouselModel_?, view: GridCarousel?, position: Int -> }
                            .onUnbind { model, view -> }
                    )
                }

                EnumItemType.CAROUSEL_LIST_LOAD_MORE -> {
                    val listModel = arrayListOf<ItemListModel_>()
                    profile.image.forEachIndexed { loademoreIndex, url ->
                        listModel.add(
                            ItemListModel_()
                                .id("$url $loademoreIndex ${profile.id}")
                                .imageUrl(url)
                                .position(loademoreIndex)
                                .preloading(true)
                                .clickListener { model, parentView, clickedView, position ->
                                    callback.onDeleteClicked(profile, position)
                                }
                                .onBind { model, view, position ->
                                    Timber.e("carousel : $index carousel-item-index : ${model.position()} ========>OnBind()")
                                }
                        )
                    }


                    //need to create custom carousel
                    endlessListCarousel {
                        id(profile.id)
                        numViewsToShowOnScreen(1.2F)
                        padding(Carousel.Padding.dp(0, 4, 0, 16, 8))
                        parentPosition(index)
                        totalResult(12)//totla number of items to fetch from db or api
                        adapterCallback(callback)
                        models(listModel)
                    }
                }

                EnumItemType.CAROUSEL_GRID_GROUP -> {
                    add(GroupGridCarouselModel(profile))
                }
            }
        }

        hasLoadMore?.let {
            ItemLoaderModel_()
                .id("loading").addIf(hasLoadMore, this)
        }
    }
}