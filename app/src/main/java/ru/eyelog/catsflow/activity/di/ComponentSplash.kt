package ru.eyelog.catsflow.activity.di

import androidx.appcompat.app.AppCompatActivity
import dagger.BindsInstance
import dagger.Component
import ru.eyelog.catsflow.activity.splash.SplashActivity
import ru.eyelog.catsflow.activity.splash.SplashViewModel
import ru.sibur.socialnetwork.core.utils.di.MainScope

@MainScope
@Component(modules = [ModuleSplash::class])
interface ComponentSplash {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withActivity(activity: AppCompatActivity): Builder

        fun build(): ComponentSplash
    }

    fun inject(activity: SplashActivity)

    fun provideViewModelFeed(): SplashViewModel
}