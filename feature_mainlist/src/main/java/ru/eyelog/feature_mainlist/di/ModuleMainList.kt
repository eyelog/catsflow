package ru.eyelog.feature_mainlist.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ru.eyelog.feature_mainlist.ViewModelMainList


@Module
class ModuleMainList {
    @Provides
    fun provideViewModelMainList(fragment: FragmentActivity, factory: FactoryMainListViewModel): ViewModelMainList {
        return ViewModelProvider(fragment, factory).get(ViewModelMainList::class.java)
    }
}