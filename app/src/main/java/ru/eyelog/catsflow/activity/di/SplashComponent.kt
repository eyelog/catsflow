package ru.eyelog.catsflow.activity.di

import androidx.appcompat.app.AppCompatActivity
import dagger.BindsInstance
import dagger.Component
import ru.eyelog.catsflow.activity.splash.SplashActivity
import ru.eyelog.catsflow.activity.splash.SplashViewModel

@Component(modules = [SplashModule::class])
interface SplashComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withActivity(activity: AppCompatActivity): Builder

        fun build(): SplashComponent
    }

    fun inject(activity: SplashActivity)

    fun provideViewModelFeed(): SplashViewModel
}