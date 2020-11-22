package ru.eyelog.catsflow.activity.di

import androidx.appcompat.app.AppCompatActivity
import dagger.BindsInstance
import dagger.Component
import ru.eyelog.catsflow.activity.main.MainActivity
import ru.eyelog.catsflow.activity.main.MainViewModel

@Component(modules = [MainModule::class])
interface MainComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withActivity(activity: AppCompatActivity): Builder

        fun build(): MainComponent
    }

    fun inject(activity: MainActivity)

    fun provideViewModelFeed(): MainViewModel
}
