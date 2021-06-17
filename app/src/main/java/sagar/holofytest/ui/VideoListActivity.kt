package sagar.holofytest.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import sagar.holofytest.R
import sagar.holofytest.base.BaseActivity
import sagar.holofytest.interfaces.ItemClickListener
import sagar.holofytest.model.VideoModel
import sagar.holofytest.ui.adapter.VideoListAdapter
import sagar.holofytest.utils.VideoVariables
import sagar.holofytest.viewmodel.VideoViewModel

class VideoListActivity : BaseActivity<VideoViewModel>(), ItemClickListener {

    private lateinit var rvVideos: RecyclerView
    private lateinit var videoListAdapter: VideoListAdapter

    override fun provideLayout(): Int {
        return R.layout.activity_video_list
    }

    override fun provideViewModelClass(): Class<VideoViewModel> {
        return VideoViewModel::class.java
    }

    override fun initViews() {
        rvVideos = findViewById(R.id.rvVideos)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.sharedElementExitTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.move)
        }

        videoListAdapter = VideoListAdapter(this, this)
        val linearLayoutManager = LinearLayoutManager(this)
        rvVideos.layoutManager = linearLayoutManager
        rvVideos.itemAnimator = DefaultItemAnimator()
        rvVideos.adapter = videoListAdapter

        val video1 = VideoModel(
            RawResourceDataSource.buildRawResourceUri(R.raw.sample_video_3).toString(),
            resources.getString(R.string.video_title_1),
            resources.getString(R.string.video_desc_1)
        )

        val video2 = VideoModel(
            RawResourceDataSource.buildRawResourceUri(R.raw.sample_video_2).toString(),
            resources.getString(R.string.video_title_2),
            resources.getString(R.string.video_desc_2)
        )

        val video3 = VideoModel(
            RawResourceDataSource.buildRawResourceUri(R.raw.sample_video).toString(),
            resources.getString(R.string.video_title_3),
            resources.getString(R.string.video_desc_3)
        )

        val videoList = ArrayList<VideoModel>()
        videoList.add(video1)
        videoList.add(video2)
        videoList.add(video3)

        videoListAdapter.addVideos(videoList)

        postponeEnterTransition()
        rvVideos.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    override fun onItemClick(pos: Int, item: VideoModel, playerView: PlayerView, cvPlayerView: CardView, tvTitle: TextView) {

        val intent = Intent(this, VideoDetailsActivity::class.java)
        intent.putExtra("EXTRA_VIDEO_ITEM", item)
        intent.putExtra(
            "EXTRA_VIDEO_PLAYER_TRANSITION_NAME",
            ViewCompat.getTransitionName(cvPlayerView)
        )
        intent.putExtra(
            "EXTRA_VIDEO_TITLE_TRANSITION_NAME",
            ViewCompat.getTransitionName(tvTitle)
        )

        val pair1 = Pair.create(cvPlayerView as View, ViewCompat.getTransitionName(cvPlayerView)!!)
        val pair2 = Pair.create(tvTitle as View, ViewCompat.getTransitionName(tvTitle)!!)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            pair1,
            pair2
        )

        VideoVariables.videoCurrentPosition = playerView.player!!.currentPosition

        startActivity(intent, options.toBundle())

    }

}