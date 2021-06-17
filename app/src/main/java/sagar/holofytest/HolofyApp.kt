package sagar.holofytest

import android.annotation.SuppressLint
import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import sagar.holofytest.di.component.AppComponent
import sagar.holofytest.di.component.DaggerAppComponent

class HolofyApp : DaggerApplication() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        var component: AppComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        component = DaggerAppComponent
            .builder()
            .application(this@HolofyApp)
            .build()
        return component
    }

}