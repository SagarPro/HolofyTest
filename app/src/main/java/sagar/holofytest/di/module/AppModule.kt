package sagar.holofytest.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import sagar.holofytest.HolofyApp
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return HolofyApp.context
    }

}