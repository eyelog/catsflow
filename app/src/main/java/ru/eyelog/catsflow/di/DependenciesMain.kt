package ru.eyelog.catsflow.di

import ru.eyelog.catsflow.router.RouterApp
import ru.eyelog.core_data.utils.ComponentDependencies
import ru.eyelog.core_data.database.AppDatabase
import ru.eyelog.core_data.database.dao.DaoCats
import ru.eyelog.core_data.network.ApiCats

interface DependenciesMain : ComponentDependencies {

    fun provideApiCats(): ApiCats

    fun provideDaoCats(): DaoCats

    fun provideAppDatabase(): AppDatabase

    fun provideAppRouter(): RouterApp
}