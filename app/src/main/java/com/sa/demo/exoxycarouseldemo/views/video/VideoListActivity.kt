package com.sa.demo.exoxycarouseldemo.views.video

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.airbnb.epoxy.EpoxyVisibilityTracker
import com.airbnb.epoxy.addGlidePreloader
import com.airbnb.epoxy.glidePreloader
import com.bumptech.glide.Glide
import com.github.ybq.android.spinkit.SpinKitView
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.*
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.sa.demo.exoxycarouseldemo.R
import com.sa.demo.exoxycarouseldemo.data.Video
import com.sa.demo.exoxycarouseldemo.modal.ItemVideoModel_
import com.sa.demo.exoxycarouseldemo.modal.VideoHolder
import com.sa.demo.exoxycarouseldemo.views.loadImage
import kotlinx.android.synthetic.main.activity_video_list.*
import timber.log.Timber

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 8/6/20
 */
class VideoListActivity : AppCompatActivity(), VideoListController.AdapterCallbacks, Player.EventListener {
    private val TAG = "ExoPlayerRecyclerView"
    private val AppName = "Epoxy ExoPlayer"

    private val videoList = ArrayList<Video>()

    private val videoListController by lazy { VideoListController(this) }
    private val dataList = ArrayList<Video>()

    private var videoSurfaceView: PlayerView? = null
    private var videoPlayer: SimpleExoPlayer? = null

    //ui component of viewholder class
    private var viewHolderParent: View? = null
    private var mediaContainer: FrameLayout? = null
    private var mediaCoverImage: ImageView? = null
    private var progressBar: SpinKitView? = null

    //variable declaration
    private var isVideoViewAdded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_list)
        prepareList()

        createPlayer()

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rv_video)

        // Attach the visibility tracker to the RecyclerView. This will enable` visibility events.
        val epoxyVisibilityTracker = EpoxyVisibilityTracker()
        epoxyVisibilityTracker.attach(rv_video)

        rv_video.setHasFixedSize(true)
        rv_video.setController(videoListController)
        videoListController.setFilterDuplicates(true)
        updateController(videoList)

        rv_video.addGlidePreloader(
            Glide.with(this),
            preloader = glidePreloader { requestManager, model: ItemVideoModel_, _ ->
                requestManager.loadImage(model.videoPath, true)
            }
        )
    }

    private fun updateController(list: List<Video>) {
        dataList.addAll(list)
        videoListController.setData(dataList)
    }

    fun prepareList() {
        videoList.addAll(
            listOf(
                Video("https://s3.ap-south-1.amazonaws.com/filmyfolks/videos/05/12052020161952_download (46).mp4", getImage("f1")),
                Video("https://s3.ap-south-1.amazonaws.com/filmyfolks/videos/05/12052020162933_download (55).mp4", getImage("f2")),
                Video("https://s3.ap-south-1.amazonaws.com/filmyfolks/videos/05/11052020102821_download - 2020-05-10T164829.941.mp4", getImage("f3")),
                Video("https://s3.ap-south-1.amazonaws.com/filmyfolks/videos/05/12052020171505_download (87).mp4", getImage("f4")),
                Video("https://s3.ap-south-1.amazonaws.com/filmyfolks/videos/05/11052020122539_download - 2020-05-11T092845.677.mp4", getImage("f5")),
                Video("https://s3.ap-south-1.amazonaws.com/filmyfolks/videos/05/09052020152813_download (95).mp4", getImage("f6"))
            )
        )
    }

    fun getImage(imageName: String?): Int {
        return resources.getIdentifier(imageName, "drawable", packageName)
    }

    private fun playVideo(model: ItemVideoModel_, view: VideoHolder) {
        if (videoSurfaceView == null) {
            return
        }

        // remove any old surface views from previously playing videos
        videoSurfaceView?.visibility = INVISIBLE
        removeVideoView(videoSurfaceView!!)

        viewHolderParent = view.itemView
        mediaCoverImage = view.thumbnail
        progressBar = view.progressBar
        mediaContainer = view.mediaContainer

        videoSurfaceView!!.player = videoPlayer
        //viewHolderParent?.setOnClickListener(videoViewClickListener)

        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(this, Util.getUserAgent(this, AppName))

        model.videoPath.let {
            val videoSource: MediaSource = ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(it))
            videoPlayer!!.prepare(videoSource)
            videoPlayer!!.playWhenReady = true
        }
    }

    private fun createPlayer() {
        videoSurfaceView = PlayerView(this@VideoListActivity)
        videoSurfaceView?.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM

        val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory: TrackSelection.Factory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector: TrackSelector = DefaultTrackSelector(videoTrackSelectionFactory)


        //Create the player using ExoPlayerFactory
        videoPlayer = ExoPlayerFactory.newSimpleInstance(this@VideoListActivity, trackSelector)
        // Disable Player Control
        videoSurfaceView?.useController = false
        // Bind the player to the view.
        videoSurfaceView?.player = videoPlayer
        //add player event listener
        videoPlayer?.addListener(this)
    }

    private fun releasePlayer() {
        if (videoPlayer != null) {
            videoPlayer?.release()
            videoPlayer = null
        }
        viewHolderParent = null
    }

    private fun addVideoView() {
        mediaContainer?.addView(videoSurfaceView)
        isVideoViewAdded = true
        videoSurfaceView!!.requestFocus()
        videoSurfaceView!!.alpha = 0.0F
        videoSurfaceView!!.visibility = VISIBLE
        videoSurfaceView!!.animate().alpha(1.0f)
        //mediaCoverImage?.visibility = GONE
    }

    // Remove the old player in case of resetting or while playing new video
    private fun removeVideoView(videoView: PlayerView?) {
        videoView?.let {
            val parent: ViewGroup? = it.parent as ViewGroup?
            parent?.let { viewgroup ->
                val index = viewgroup.indexOfChild(it)
                if (index >= 0) {
                    viewgroup.removeViewAt(index)
                    isVideoViewAdded = false
                    //viewHolderParent.setOnClickListener(null)
                }
            }

        }

    }

    private fun resetVideoView() {
        if (isVideoViewAdded) {
            removeVideoView(videoSurfaceView!!)
            videoSurfaceView!!.visibility = INVISIBLE
            mediaCoverImage?.visibility = VISIBLE
        }
    }

    override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {

    }

    override fun onSeekProcessed() {

    }

    override fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?) {

    }

    override fun onPlayerError(error: ExoPlaybackException?) {

    }

    override fun onLoadingChanged(isLoading: Boolean) {

    }

    override fun onPositionDiscontinuity(reason: Int) {

    }

    override fun onRepeatModeChanged(repeatMode: Int) {

    }

    override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {

    }

    override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {

    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        when (playbackState) {
            Player.STATE_BUFFERING -> {
                Timber.e("onPlayerStateChanged: Buffering video.")
                progressBar?.alpha = 0.0F
                progressBar?.visibility = VISIBLE
                progressBar?.animate()?.alpha(1.0f)?.duration = 500
            }
            Player.STATE_ENDED -> {
                Timber.d("onPlayerStateChanged: Video ended.")
                videoPlayer?.seekTo(0)
            }
            Player.STATE_IDLE -> {
            }
            Player.STATE_READY -> {
                Timber.e("onPlayerStateChanged: Ready to play.")
                progressBar?.visibility = GONE
                if (!this.isVideoViewAdded) {
                    addVideoView()
                }
            }
            else -> {
            }
        }
    }

    override fun onDestroy() {
        releasePlayer()
        super.onDestroy()
    }

    override fun onNewItemFocusedVisible(model: ItemVideoModel_, view: VideoHolder) {
        //display progress and thumb of new item
        if (viewHolderParent != null && viewHolderParent?.equals(view.itemView)!!) {
            return
        }
        playVideo(model, view)
    }

    override fun onOldItemInvisible(model: ItemVideoModel_, view: VideoHolder) {
        //remove old surfaceview hide progress and
    }

    override fun onNewItemFullImpressionVisible(model: ItemVideoModel_, view: VideoHolder) {
        //assign viewholder property to local varible here
    }

}