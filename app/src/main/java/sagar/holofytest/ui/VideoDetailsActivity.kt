package sagar.holofytest.ui

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import sagar.holofytest.R
import sagar.holofytest.base.BaseActivity
import sagar.holofytest.model.VideoModel
import sagar.holofytest.utils.VideoVariables
import sagar.holofytest.viewmodel.VideoViewModel

class VideoDetailsActivity : BaseActivity<VideoViewModel>() {

    //private lateinit var exoPlayer: SimpleExoPlayer

    private lateinit var cvPlayerView: CardView
    private lateinit var playerView: PlayerView
    private lateinit var ivPlayVideo: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvDesc: TextView

    private var isVideoPlaying = true

    override fun provideLayout(): Int {
        return R.layout.activity_video_details
    }

    override fun provideViewModelClass(): Class<VideoViewModel> {
        return VideoViewModel::class.java
    }

    override fun initViews() {
        cvPlayerView = findViewById(R.id.cvPlayerView)
        playerView = findViewById(R.id.playerView)
        ivPlayVideo = findViewById(R.id.ivPlayVideo)
        tvTitle = findViewById(R.id.tvTitle)
        tvDesc = findViewById(R.id.tvDesc)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //set player(transition player) to avoid blank screen while loading video into playerView
        playerView.player = null
        playerView.player = VideoVariables.currentPlayer!!
        playerView.player?.volume = 5f

        isVideoPlaying = true
        ivPlayVideo.visibility = View.GONE

        //safe check for pre lollipop android os
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.sharedElementEnterTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.move)
            window.sharedElementExitTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.move)
        }

        val extras = intent.extras
        //get videoItem through extras
        val videoItem: VideoModel = extras?.getParcelable("EXTRA_VIDEO_ITEM")!!

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //set transition name for cardView
            val videoPlayerTransitionName = extras.getString("EXTRA_VIDEO_PLAYER_TRANSITION_NAME")
            cvPlayerView.transitionName = videoPlayerTransitionName
        }

        tvTitle.text = videoItem.title
        tvDesc.text = videoItem.desc

        //creating new player is making screen blank before playing video
        //so instead of this we are directly setting player(transition player) to playerView
        /*exoPlayer = SimpleExoPlayer.Builder(this).build()
        playerView.player = exoPlayer

        val mediaItem = MediaItem.fromUri(videoItem.url!!)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.seekTo(VideoVariables.videoCurrentPosition)
        exoPlayer.prepare()
        exoPlayer.play()
        exoPlayer.volume = 5f
        exoPlayer.repeatMode = SimpleExoPlayer.REPEAT_MODE_ONE*/

        cvPlayerView.setOnClickListener {
            checkForPlayingVideo()
        }

        ivPlayVideo.setOnClickListener {
            checkForPlayingVideo()
        }

    }

    private fun checkForPlayingVideo(){
        if (isVideoPlaying){
            isVideoPlaying = false
            ivPlayVideo.visibility = View.VISIBLE
            playerView.player?.pause()
        } else {
            isVideoPlaying = true
            ivPlayVideo.visibility = View.GONE
            playerView.player?.play()
        }
    }

    override fun onDestroy() {
        //if new exoplayer instance is created, we have to release
        //exoPlayer.stop()
        //exoPlayer.release()

        //set back player to its original owner
        VideoVariables.currentPlayerView!!.player = null
        VideoVariables.currentPlayerView!!.player = VideoVariables.currentPlayer!!
        VideoVariables.currentPlayerView!!.player?.volume = 0f

        //check if video is paused, play video before exiting this activity
        if (!isVideoPlaying){
            isVideoPlaying = true
            ivPlayVideo.visibility = View.GONE
            VideoVariables.currentPlayerView!!.player?.play()
        }

        super.onDestroy()
    }

}