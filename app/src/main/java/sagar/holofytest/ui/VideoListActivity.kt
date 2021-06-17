package sagar.holofytest.ui

import android.os.Bundle
import sagar.holofytest.R
import sagar.holofytest.base.BaseActivity
import sagar.holofytest.viewmodel.VideoViewModel

class VideoListActivity : BaseActivity<VideoViewModel>() {

    override fun provideLayout(): Int {
        return R.layout.activity_video_list
    }

    override fun provideViewModelClass(): Class<VideoViewModel> {
        return VideoViewModel::class.java
    }

    override fun initViews() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}