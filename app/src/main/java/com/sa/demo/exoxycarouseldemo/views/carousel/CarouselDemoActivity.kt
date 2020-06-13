package com.sa.demo.exoxycarouseldemo.views.carousel

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sa.demo.exoxycarouseldemo.R
import com.sa.demo.exoxycarouseldemo.data.EnumItemType
import com.sa.demo.exoxycarouseldemo.data.Profile
import com.sa.demo.exoxycarouseldemo.utils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.activity_carousel_demo.*

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 4/6/20
 */
class CarouselDemoActivity : AppCompatActivity(), CarouselController.AdapterCallbacks {


    // Store a member variable for the listener
    private val sampleController by lazy { CarouselController(this) }
    private val dataList = ArrayList<Profile>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carousel_demo)


        rv_demo.addOnScrollListener(object : EndlessRecyclerViewScrollListener(rv_demo.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                updateController(arrayListOf(), true)
                Handler().postDelayed({
                    updateController(newList, false)
                    rv_demo.clearOnScrollListeners()
                }, 3000)

            }
        })

        rv_demo.setHasFixedSize(true)
        rv_demo.setController(sampleController)
        sampleController.setFilterDuplicates(true)
        updateController(recentlyActive, true)


        // Memory and disk cache is cleared to give accurate representation of load times
        /*Glide.get(this).clearMemory()
        object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                Glide.get(applicationContext).clearDiskCache()
            }
        }.execute()*/
        /*val listPreloader =
            EpoxyModelPreloader.with(listOf(R.id.image_view)) { requestManager: RequestManager, model: ItemListModel_, viewData ->

                requestManager.loadImage(model.imageUrl, true)
                *//*val options = RequestOptions
                    .diskCacheStrategyOf(DiskCacheStrategy.NONE)
                    .sizeMultiplier(0.1f)

                glide.asBitmap()
                    .load(model.imageUrl)
                    .apply(options)
                    .transition(BitmapTransitionOptions.withCrossFade())*//*
            }

        val gridPreloader =
            EpoxyModelPreloader.with(listOf(R.id.image_view)) { requestManager: RequestManager, model: ItemGridModal_, viewData ->

                requestManager.loadImage(model.imageUrl, true)
                *//*val options = RequestOptions
                    .diskCacheStrategyOf(DiskCacheStrategy.NONE)
                    .sizeMultiplier(0.1f)

                glide.asBitmap()
                    .load(model.imageUrl)
                    .apply(options)
                    .transition(BitmapTransitionOptions.withCrossFade())*//*
            }

        val preloader = EpoxyPreloader.with(
            epoxyController = sampleController,
            maxItemsToPreload = 5,
            modelPreloaders = listOf(listPreloader, gridPreloader),
            errorHandler = { _: Context, _: RuntimeException ->  },
            requestHolderFactory ={GlidePreloadRequestHolder(this)}
        )*/

        /*rv_demo.addGlidePreloader(
            Glide.with(this),
            preloader = glidePreloader { requestManager, model: ItemListModel_, _ ->
                requestManager.loadImage(model.imageUrl, true)
            }
        )

        rv_demo.addGlidePreloader(
            Glide.with(this),
            preloader = glidePreloader { requestManager, model: ItemGridModal_, _ ->
                requestManager.loadImage(model.imageUrl, true)
            }
        )*/
    }

    private fun updateController(list: List<Profile>, loadMore: Boolean) {
        dataList.addAll(list)
        sampleController.setData(dataList, loadMore)
    }

    val moreImages = ArrayList(
        listOf(
            "https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823__340.jpg",
            "https://cdn.pixabay.com/photo/2015/06/19/21/24/the-road-815297__340.jpg",
            "https://wonderfulengineering.com/wp-content/uploads/2016/01/nature-wallpapers-38.jpg",
            "https://cdn.pixabay.com/photo/2015/12/01/20/28/fall-1072821__340.jpg",
            "https://images-na.ssl-images-amazon.com/images/I/81WtcXeGZxL._SL1152_.jpg"
        )
    )

    val newList = listOf(
        Profile(
            6,
            "Rambo",
            ArrayList(
                listOf(
                    "https://i.redd.it/nbju2rir9xp11.jpg",
                    "https://i.redditmedia.com/pA8syU4qzqyqn8ggXxZntuM-JIvBWGQvRo1c44yIvPs.jpg?s=b92a768940b1fa07b54e47d5dbe95f99",
                    "https://i.redd.it/g7cv0byl5wp11.jpg",
                    "https://i.redd.it/c2ozz6pdhyp11.jpg",
                    "https://i.redd.it/y20c2oi9myp11.jpg",
                    "https://i.redd.it/6b740x7ptyp11.jpg",
                    "https://i.redd.it/qqi87wl8uup11.jpg"
                )
            ),
            "2 min ago",
            EnumItemType.CAROUSEL_LIST_GROUP
        ),
        Profile(
            7,
            "Johny",
            ArrayList(
                listOf(
                    "https://i.redditmedia.com/pA8syU4qzqyqn8ggXxZntuM-JIvBWGQvRo1c44yIvPs.jpg?s=b92a768940b1fa07b54e47d5dbe95f99",
                    "https://i.redd.it/nbju2rir9xp11.jpg",
                    "https://i.redd.it/g7cv0byl5wp11.jpg",
                    "https://i.redd.it/c2ozz6pdhyp11.jpg",
                    "https://i.redd.it/y20c2oi9myp11.jpg",
                    "https://i.redd.it/6b740x7ptyp11.jpg",
                    "https://i.redd.it/qqi87wl8uup11.jpg"
                )
            ),
            "16 min ago",
            EnumItemType.CAROUSEL_LIST_LOAD_MORE
        ),
        Profile(
            8,
            "Tony",
            ArrayList(
                listOf(
                    "https://i.redd.it/g7cv0byl5wp11.jpg",
                    "https://i.redditmedia.com/pA8syU4qzqyqn8ggXxZntuM-JIvBWGQvRo1c44yIvPs.jpg?s=b92a768940b1fa07b54e47d5dbe95f99",
                    "https://i.redd.it/nbju2rir9xp11.jpg",
                    "https://i.redd.it/c2ozz6pdhyp11.jpg",
                    "https://i.redd.it/y20c2oi9myp11.jpg",
                    "https://i.redd.it/6b740x7ptyp11.jpg",
                    "https://i.redd.it/qqi87wl8uup11.jpg"
                )
            ),
            "21 min ago",
            EnumItemType.CAROUSEL_GRID_DEFAULT
        ),
        Profile(
            9,
            "Jack",
            ArrayList(
                listOf(
                    "https://i.redd.it/c2ozz6pdhyp11.jpg",
                    "https://i.redd.it/qqi87wl8uup11.jpg",
                    "https://i.redditmedia.com/pA8syU4qzqyqn8ggXxZntuM-JIvBWGQvRo1c44yIvPs.jpg?s=b92a768940b1fa07b54e47d5dbe95f99",
                    "https://i.redd.it/nbju2rir9xp11.jpg",
                    "https://i.redd.it/y20c2oi9myp11.jpg",
                    "https://i.redd.it/6b740x7ptyp11.jpg",
                    "https://i.redd.it/g7cv0byl5wp11.jpg"
                )
            ),
            "2 min ago",
            EnumItemType.CAROUSEL_GRID_GROUP
        )/*,
        Profile(
            10,
            "Marcus",
            ArrayList(
                listOf(
                    "https://i.redd.it/y20c2oi9myp11.jpg",
                    "https://i.redd.it/g7cv0byl5wp11.jpg",
                    "https://i.redd.it/nbju2rir9xp11.jpg",
                    "https://i.redditmedia.com/pA8syU4qzqyqn8ggXxZntuM-JIvBWGQvRo1c44yIvPs.jpg?s=b92a768940b1fa07b54e47d5dbe95f99",
                    "https://i.redd.it/c2ozz6pdhyp11.jpg",
                    "https://i.redd.it/6b740x7ptyp11.jpg",
                    "https://i.redd.it/qqi87wl8uup11.jpg"
                )
            ),
            "16 min ago",
            ITEM_TYPE.CAROUSEL_LIST_INDICATOR
        ),
        Profile(
            11,
            "Lisa",
            ArrayList(
                listOf(
                    "https://i.redd.it/6b740x7ptyp11.jpg",
                    "https://i.redditmedia.com/pA8syU4qzqyqn8ggXxZntuM-JIvBWGQvRo1c44yIvPs.jpg?s=b92a768940b1fa07b54e47d5dbe95f99",
                    "https://i.redd.it/nbju2rir9xp11.jpg",
                    "https://i.redd.it/g7cv0byl5wp11.jpg",
                    "https://i.redd.it/c2ozz6pdhyp11.jpg",
                    "https://i.redd.it/y20c2oi9myp11.jpg",
                    "https://i.redd.it/qqi87wl8uup11.jpg"
                )
            ),
            "21 min ago",
            ITEM_TYPE.CAROUSEL_LIST_GROUP_LOAD_MORE
        )*/
    )

    val recentlyActive = listOf(
        Profile(
            0,
            "Jenifer",
            ArrayList(
                listOf(
                    "https://i.redd.it/hpz1lhk1jzp11.jpg",
                    "https://i.redditmedia.com/7PuRyOWNt8vu4uygz0Xajlms9URmNp2_9z4xC-jSseA.jpg?s=3ddfadc3de602db2d98985470b36aa00",
                    "https://i.redd.it/kkzgwnb0rtt11.jpg",
                    "https://i.redd.it/hosvfvb7yxp11.jpg",
                    "https://i.redd.it/x0v1ixpp3yp11.jpg",
                    "https://i.redd.it/u4ofokaxuyp11.jpg",
                    "https://i.redditmedia.com/OHQZkFAaBOP-PNiQB-reJojV37CGu-TIa7wtoci0hXU.jpg?s=e2f84c1fc171090229a566104e7c77b0"
                )
            ),
            "2 min ago",
            EnumItemType.CAROUSEL_LIST_DEFAULT
        ),
        Profile(
            1,
            "Marcus",
            ArrayList(
                listOf(
                    "https://i.redd.it/kkzgwnb0rtt11.jpg",
                    "https://i.redd.it/hosvfvb7yxp11.jpg",
                    "https://i.redditmedia.com/7PuRyOWNt8vu4uygz0Xajlms9URmNp2_9z4xC-jSseA.jpg?s=3ddfadc3de602db2d98985470b36aa00",
                    "https://i.redd.it/hpz1lhk1jzp11.jpg",
                    "https://i.redd.it/x0v1ixpp3yp11.jpg",
                    "https://i.redd.it/u4ofokaxuyp11.jpg",
                    "https://i.redditmedia.com/OHQZkFAaBOP-PNiQB-reJojV37CGu-TIa7wtoci0hXU.jpg?s=e2f84c1fc171090229a566104e7c77b0"
                )
            ),
            "16 min ago",
            EnumItemType.CAROUSEL_LIST_INDICATOR
        ),
        Profile(
            2,
            "Lisa",
            ArrayList(
                listOf(
                    "https://i.redd.it/x0v1ixpp3yp11.jpg",
                    "https://i.redd.it/u4ofokaxuyp11.jpg",
                    "https://i.redditmedia.com/7PuRyOWNt8vu4uygz0Xajlms9URmNp2_9z4xC-jSseA.jpg?s=3ddfadc3de602db2d98985470b36aa00",
                    "https://i.redd.it/hpz1lhk1jzp11.jpg",
                    "https://i.redd.it/kkzgwnb0rtt11.jpg",
                    "https://i.redd.it/hosvfvb7yxp11.jpg",
                    "https://i.redditmedia.com/OHQZkFAaBOP-PNiQB-reJojV37CGu-TIa7wtoci0hXU.jpg?s=e2f84c1fc171090229a566104e7c77b0"
                )
            ),
            "21 min ago",
            EnumItemType.CAROUSEL_LIST_INDICATOR_CUSTOM
        )
        /*,
        Profile(
            3,
            "Jenifer",
            listOf(
                "https://i.redditmedia.com/OHQZkFAaBOP-PNiQB-reJojV37CGu-TIa7wtoci0hXU.jpg?s=e2f84c1fc171090229a566104e7c77b0",
                "https://i.redd.it/hpz1lhk1jzp11.jpg",
                "https://i.redd.it/kkzgwnb0rtt11.jpg",
                "https://i.redd.it/hosvfvb7yxp11.jpg",
                "https://i.redd.it/x0v1ixpp3yp11.jpg",
                "https://i.redd.it/u4ofokaxuyp11.jpg",
                "https://i.redditmedia.com/7PuRyOWNt8vu4uygz0Xajlms9URmNp2_9z4xC-jSseA.jpg?s=3ddfadc3de602db2d98985470b36aa00"
            ),
            "2 min ago"
        ),
        Profile(
            4,
            "Marcus",
            listOf(
                "https://i.redd.it/hosvfvb7yxp11.jpg",
                "https://i.redd.it/x0v1ixpp3yp11.jpg",
                "https://i.redditmedia.com/7PuRyOWNt8vu4uygz0Xajlms9URmNp2_9z4xC-jSseA.jpg?s=3ddfadc3de602db2d98985470b36aa00",
                "https://i.redd.it/hpz1lhk1jzp11.jpg",
                "https://i.redd.it/kkzgwnb0rtt11.jpg",
                "https://i.redd.it/u4ofokaxuyp11.jpg",
                "https://i.redditmedia.com/OHQZkFAaBOP-PNiQB-reJojV37CGu-TIa7wtoci0hXU.jpg?s=e2f84c1fc171090229a566104e7c77b0"
            ),
            "16 min ago",
            showGrid = true
        ),
        Profile(
            5,
            "Lisa",
            listOf(
                "https://i.redd.it/u4ofokaxuyp11.jpg",
                "https://i.redd.it/x0v1ixpp3yp11.jpg",
                "https://i.redd.it/kkzgwnb0rtt11.jpg",
                "https://i.redditmedia.com/7PuRyOWNt8vu4uygz0Xajlms9URmNp2_9z4xC-jSseA.jpg?s=3ddfadc3de602db2d98985470b36aa00",
                "https://i.redd.it/hpz1lhk1jzp11.jpg",
                "https://i.redd.it/hosvfvb7yxp11.jpg",
                "https://i.redditmedia.com/OHQZkFAaBOP-PNiQB-reJojV37CGu-TIa7wtoci0hXU.jpg?s=e2f84c1fc171090229a566104e7c77b0"
            ),
            "21 min ago"
        )*/

    )

    override fun onLoadMore(profile: Profile, page: Int, totalItemsCount: Int) {
        //make loader visible here
        val profilePosition: Int = dataList.indexOf(profile)
        dataList[profilePosition].image.addAll(moreImages)
        updateController(dataList, false)
    }


    override fun onDeleteClicked(profile: Profile, imagePosition: Int) {
        val profilePosition: Int = dataList.indexOf(profile)
        dataList[profilePosition].image.removeAt(imagePosition)
        updateController(dataList, false)
    }
}
