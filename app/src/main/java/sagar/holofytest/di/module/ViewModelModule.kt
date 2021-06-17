package sagar.holofytest.di.module

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import sagar.holofytest.di.factory.ViewModelFactory
import sagar.holofytest.di.interfaces.ApplicationContext
import sagar.holofytest.di.interfaces.ViewModelKey
import sagar.holofytest.viewmodel.VideoViewModel

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(VideoViewModel::class)
    abstract fun bindVideoViewModel(videoViewModel: VideoViewModel): ViewModel

}