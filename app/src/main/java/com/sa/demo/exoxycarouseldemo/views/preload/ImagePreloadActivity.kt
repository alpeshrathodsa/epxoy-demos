package com.sa.demo.exoxycarouseldemo.views.preload

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sa.demo.exoxycarouseldemo.R
import com.sa.demo.exoxycarouseldemo.data.Profile
import kotlinx.android.synthetic.main.activity_image_preload.*

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 13/6/20
 */
class ImagePreloadActivity : AppCompatActivity() {

    private val sampleController by lazy { ImagePreloadController() }
    private val dataList = ArrayList<Profile>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_preload)

        rv_image_preload.setHasFixedSize(true)
        rv_image_preload.setController(sampleController)
        sampleController.setFilterDuplicates(true)
        updateController(recentlyActive, true)
    }

    private fun updateController(list: List<Profile>, loadMore: Boolean) {
        dataList.addAll(list)
        sampleController.setData(dataList, loadMore)
    }

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
            "2 min ago"
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
            "16 min ago"
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
            "21 min ago"
        )
    )
}