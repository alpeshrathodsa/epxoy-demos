package com.sa.demo.exoxycarouseldemo.utils

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import com.airbnb.epoxy.EpoxyRecyclerView
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.*
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 8/6/20
 */
open class ExoEpoxyRecyclerView : EpoxyRecyclerView, Player.EventListener {

    private val TAG = "ExoPlayerRecyclerView"

    private val mediaContainer: FrameLayout? = null
    private var videoSurfaceView: PlayerView? = null
    private var videoPlayer: SimpleExoPlayer? = null


    private lateinit var ctx: Context
    private var isVideoViewAdded = false

    // controlling volume state
    private var volumeState: VolumeState? = null

    constructor(ctx: Context) : super(ctx) {
        init(ctx)
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        init(ctx)
    }

    private fun init(ctx: Context) {
        this.ctx = ctx

        videoSurfaceView = PlayerView(this.ctx)
        videoSurfaceView?.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM)

        val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory: TrackSelection.Factory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector: TrackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        //Create the player using ExoPlayerFactory
        videoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector)
        // Disable Player Control
        videoSurfaceView?.useController = false
        // Bind the player to the view.
        videoSurfaceView?.player = videoPlayer
    }

    private fun toggleVolume() {
        if (videoPlayer != null) {
            if (volumeState == VolumeState.OFF) {
                Log.d(TAG, "togglePlaybackState: enabling volume.")
                setVolumeControl(VolumeState.ON)
            } else if (volumeState == VolumeState.ON) {
                Log.d(TAG, "togglePlaybackState: disabling volume.")
                setVolumeControl(VolumeState.OFF)
            }
        }
    }

    // Remove the old player
    private fun removeVideoView(videoView: PlayerView?) {
        videoView?.let {
            val parent = it.parent as ViewGroup ?: return
            val index = parent.indexOfChild(videoView)
            if (index >= 0) {
                parent.removeViewAt(index)
                isVideoViewAdded = false
            }
        }
    }

    private fun addVideoView() {
        mediaContainer!!.addView(videoSurfaceView)
        isVideoViewAdded = true
        videoSurfaceView!!.requestFocus()
        videoSurfaceView!!.visibility = VISIBLE
        videoSurfaceView!!.alpha = 1f
        //mediaCoverImage.setVisibility(GONE)
    }

    private fun resetVideoView() {
        if (isVideoViewAdded) {
            removeVideoView(videoSurfaceView)
            videoSurfaceView!!.visibility = INVISIBLE
            //mediaCoverImage.setVisibility(VISIBLE)
        }
    }

    fun releasePlayer() {
        videoPlayer?.apply {
            release()
        }
        videoPlayer = null
        //viewHolderParent = null
    }

    fun onPausePlayer() {
        videoPlayer?.stop(true)
    }

    private fun setVolumeControl(state: VolumeState) {
        volumeState = state
        if (state == VolumeState.OFF) {
            videoPlayer?.volume = 0f
            //animateVolumeControl()
        } else if (state == VolumeState.ON) {
            videoPlayer?.volume = 1f
            //animateVolumeControl()
        }
    }


    /**
     * Volume ENUM
     */
    private enum class VolumeState {
        ON, OFF
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
    }

}