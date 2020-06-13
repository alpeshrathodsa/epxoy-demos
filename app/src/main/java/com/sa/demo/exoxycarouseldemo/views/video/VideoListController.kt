package com.sa.demo.exoxycarouseldemo.views.video

import android.graphics.Color
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.VisibilityState
import com.sa.demo.exoxycarouseldemo.data.Video
import com.sa.demo.exoxycarouseldemo.modal.ItemVideoModel_
import com.sa.demo.exoxycarouseldemo.modal.VideoHolder
import com.sa.demo.exoxycarouseldemo.modal.itemVideo
import timber.log.Timber
import java.util.*

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 8/6/20
 */
class VideoListController(private val callback: AdapterCallbacks) : TypedEpoxyController<List<Video>>() {

    public interface AdapterCallbacks {
        fun onNewItemFocusedVisible(model: ItemVideoModel_, view: VideoHolder)
        fun onOldItemInvisible(model: ItemVideoModel_, view: VideoHolder)
        fun onNewItemFullImpressionVisible(model: ItemVideoModel_, view: VideoHolder)
    }

    private val RANDOM = Random()
    private val TAG = "VideoListController"


    override fun buildModels(data: List<Video>?) {

        data?.forEachIndexed { index, video ->

            itemVideo {
                video.videoPath?.let {
                    id("video_id_$index")
                    videoPath(it)
                    drawableRes(video.drawableRes)
                    color(randomColor())
                    position(index)
                    onBind { model, view, position ->
                        Timber.d("VideoTag-$position")
                    }
                    onVisibilityStateChanged { model, view, visibilityState ->
                        if (visibilityState == VisibilityState.VISIBLE) {
                            Timber.d("$TAG - ${model.position()} ====> VISIBLE")
                        } else if (visibilityState == VisibilityState.INVISIBLE) {
                            Timber.d("$TAG - ${model.position()} ====> INVISIBLE")//set visibility of thumbnail
                            callback.onOldItemInvisible(model, view)
                        } else if (visibilityState == VisibilityState.FOCUSED_VISIBLE) {
                            Timber.d("$TAG - ${model.position()} ====> FOCUSED VISIBLE")//set visibility of thumbnail here
                            callback.onNewItemFocusedVisible(model, view)
                        } else if (visibilityState == VisibilityState.UNFOCUSED_VISIBLE) {
                            Timber.d("$TAG - ${model.position()} ====> UNFOCUSED VISIBLE")
                        } else if (visibilityState == VisibilityState.FULL_IMPRESSION_VISIBLE) {
                            Timber.d("$TAG - ${model.position()} ====> FULL_IMPRESSION_VISIBLE")//play video here
                            callback.onNewItemFullImpressionVisible(model, view)
                        }
                    }
                    onVisibilityChanged { model, view, percentVisibleHeight, percentVisibleWidth, heightVisible, widthVisible ->
                        if (percentVisibleHeight > 0) {
                            Timber.d("$TAG - ${model.position()} ====> OnlyVisible")//play video here
                        }
                    }
                }
            }
        }
    }

    private fun randomColor(): Int {
        val r: Int = RANDOM.nextInt(256)
        val g: Int = RANDOM.nextInt(256)
        val b: Int = RANDOM.nextInt(256)
        return Color.rgb(r, g, b)
    }
}