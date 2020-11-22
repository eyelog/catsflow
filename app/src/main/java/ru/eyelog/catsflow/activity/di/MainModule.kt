package ru.eyelog.catsflow.activity.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ru.eyelog.catsflow.activity.main.MainViewModel

@Module
class MainModule {

    @Provides
    fun provideMainViewModel(activity: AppCompatActivity): MainViewModel {
        return ViewModelProvider(activity).get(MainViewModel::class.java)
    }
}