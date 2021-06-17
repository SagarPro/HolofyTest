package sagar.holofytest.ui

import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
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

        videoListAdapter = VideoListAdapter(this, this)
        val linearLayoutManager = LinearLayoutManager(this)
        rvVideos.layoutManager = linearLayoutManager
        rvVideos.itemAnimator = DefaultItemAnimator()
        rvVideos.adapter = videoListAdapter

        val video1 = VideoModel(
            RawResourceDataSource.buildRawResourceUri(R.raw.sample_video_3).toString(),
            "Video Title 1",
            "Video Description 1"
        )

        val video2 = VideoModel(
            RawResourceDataSource.buildRawResourceUri(R.raw.sample_video_2).toString(),
            "Video Title 2",
            "Video Description 2"
        )

        val video3 = VideoModel(
            RawResourceDataSource.buildRawResourceUri(R.raw.sample_video).toString(),
            "Video Title 3",
            "Video Description 3"
        )

        val videoList = ArrayList<VideoModel>()
        videoList.add(video1)
        videoList.add(video2)
        videoList.add(video3)

        videoListAdapter.addVideos(videoList)
    }

    override fun onItemClick(pos: Int, item: VideoModel, playerView: PlayerView, mediaItem: MediaItem) {

        val intent = Intent(this, VideoDetailsActivity::class.java)
        intent.putExtra("EXTRA_VIDEO_ITEM", item)
        intent.putExtra(
            "EXTRA_VIDEO_PLAYER_TRANSITION_NAME",
            ViewCompat.getTransitionName(playerView)
        )

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            playerView,
            ViewCompat.getTransitionName(playerView)!!
        )

        VideoVariables.videoCurrentPosition = playerView.player!!.currentPosition

        startActivity(intent, options.toBundle())

    }

}