package ru.eyelog.catsflow.di

import dagger.Binds
import dagger.Module
import ru.eyelog.catsflow.RouterCats
import ru.eyelog.catsflow.router.RouterApp
import ru.eyelog.catsflow.router.RouterAppImpl
import ru.eyelog.feature_favorites.RouterFavoritesList
import ru.eyelog.feature_mainlist.RouterMainList

@Module
abstract class ModuleRouter {

    @Binds
    abstract fun bindRouterApp(router: RouterAppImpl): RouterApp

    @Binds
    abstract fun bindRouterNews(router: RouterAppImpl): RouterCats

    @Binds
    abstract fun bindRouterMainList(router: RouterAppImpl): RouterMainList

    @Binds
    abstract fun bindRouterFavoritesList(router: RouterAppImpl): RouterFavoritesList
}