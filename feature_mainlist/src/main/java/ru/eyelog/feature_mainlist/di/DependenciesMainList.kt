package ru.eyelog.feature_mainlist.di

import android.content.Context
import ru.eyelog.core_data.database.AppDatabase
import ru.eyelog.core_data.utils.ComponentDependencies
import ru.eyelog.core_data.database.dao.DaoCats
import ru.eyelog.core_data.network.ApiCats
import ru.eyelog.feature_mainlist.RouterMainList

interface DependenciesMainList: ComponentDependencies {

    fun provideContext(): Context

    fun provideRouterMainList(): RouterMainList

    fun provideAppDatabase(): AppDatabase

    fun provideDaoCats(): DaoCats

    fun provideApiCats(): ApiCats
}