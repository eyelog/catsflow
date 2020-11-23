package ru.eyelog.catsflow.activity.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ru.eyelog.catsflow.activity.mainscreen.MainViewModel
import ru.eyelog.core_common.abstractions.BaseActivity

@Module
class ModuleMain {

    @Provides
    fun provideViewModelMain(activity: AppCompatActivity, factory: MainViewModelFactory): MainViewModel =
        ViewModelProvider(activity, factory).get(MainViewModel::class.java)
}