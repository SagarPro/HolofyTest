package sagar.holofytest.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import sagar.holofytest.R
import sagar.holofytest.base.BaseActivity
import sagar.holofytest.model.VideoModel
import sagar.holofytest.utils.VideoVariables
import sagar.holofytest.viewmodel.VideoViewModel

class VideoDetailsActivity : BaseActivity<VideoViewModel>() {

    private lateinit var exoPlayer: SimpleExoPlayer

    private lateinit var playerView: PlayerView
    private lateinit var tvTitle: TextView
    private lateinit var tvDesc: TextView

    override fun provideLayout(): Int {
        return R.layout.activity_video_details
    }

    override fun provideViewModelClass(): Class<VideoViewModel> {
        return VideoViewModel::class.java
    }

    override fun initViews() {
        playerView = findViewById(R.id.playerView)
        tvTitle = findViewById(R.id.tvTitle)
        tvDesc = findViewById(R.id.tvDesc)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = intent.extras
        val videoItem: VideoModel = extras?.getParcelable("EXTRA_VIDEO_ITEM")!!

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val videoPlayerTransitionName = extras.getString("EXTRA_VIDEO_PLAYER_TRANSITION_NAME")
            playerView.transitionName = videoPlayerTransitionName
        }

        tvTitle.text = videoItem.title
        tvDesc.text = videoItem.desc

        exoPlayer = SimpleExoPlayer.Builder(this).build()
        playerView.player = exoPlayer

        val mediaItem = MediaItem.fromUri(videoItem.url!!)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.seekTo(VideoVariables.videoCurrentPosition)
        exoPlayer.prepare()
        exoPlayer.play()
        exoPlayer.volume = 5f
        exoPlayer.repeatMode = SimpleExoPlayer.REPEAT_MODE_ONE

    }

    override fun onDestroy() {
        exoPlayer.stop()
        exoPlayer.release()
        super.onDestroy()
    }

}