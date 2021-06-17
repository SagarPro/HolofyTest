package sagar.holofytest.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sagar.holofytest.ui.VideoDetailsActivity
import sagar.holofytest.ui.VideoListActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeVideoListActivity(): VideoListActivity

    @ContributesAndroidInjector
    internal abstract fun contributeVideoDetailsActivity(): VideoDetailsActivity

}