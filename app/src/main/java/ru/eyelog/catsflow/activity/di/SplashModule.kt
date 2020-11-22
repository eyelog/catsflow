package ru.eyelog.catsflow.activity.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ru.eyelog.catsflow.activity.splash.SplashViewModel

@Module
class SplashModule {

    @Provides
    fun provideSplashViewModel(activity: AppCompatActivity): SplashViewModel {
        return ViewModelProvider(activity).get(SplashViewModel::class.java)
    }
}