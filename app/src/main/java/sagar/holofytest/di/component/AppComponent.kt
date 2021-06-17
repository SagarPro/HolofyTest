package sagar.holofytest.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import sagar.holofytest.HolofyApp
import sagar.holofytest.di.module.ActivityModule
import sagar.holofytest.di.module.AppModule
import sagar.holofytest.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules =
[
    ActivityModule::class,
    ViewModelModule::class,
    AppModule::class,
    AndroidSupportInjectionModule::class
])

interface AppComponent : AndroidInjector<HolofyApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}