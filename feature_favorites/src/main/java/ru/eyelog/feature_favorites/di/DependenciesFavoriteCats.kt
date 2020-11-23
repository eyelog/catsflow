package ru.eyelog.feature_favorites.di

import android.content.Context
import ru.eyelog.core_data.database.AppDatabase
import ru.eyelog.core_common.ComponentDependencies
import ru.eyelog.core_data.database.dao.DaoCats
import ru.eyelog.core_data.network.ApiCats
import ru.eyelog.feature_favorites.RouterFavoritesList

interface DependenciesFavoriteCats: ComponentDependencies {

    fun provideRouterCats(): RouterFavoritesList

    fun provideAppDatabase(): AppDatabase

    fun provideDaoCats(): DaoCats

    fun provideApiCats(): ApiCats
}