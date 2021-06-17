package sagar.holofytest.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import sagar.holofytest.di.interfaces.ApplicationContext
import javax.inject.Singleton

@Module
class ContextModule(private val context: Context) {

    @ApplicationContext
    @Provides
    @Singleton
    fun context(): Context {
        return context.applicationContext
    }

}