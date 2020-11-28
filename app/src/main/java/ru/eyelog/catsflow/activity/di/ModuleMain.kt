package ru.eyelog.catsflow.activity.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ru.eyelog.catsflow.activity.mainscreen.MainViewModel

@Module
class ModuleMain {

    @Provides
    fun provideViewModelMain(activity: FragmentActivity, factory: MainViewModelFactory): MainViewModel =
        ViewModelProvider(activity, factory).get(MainViewModel::class.java)
}