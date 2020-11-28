package ru.eyelog.catsflow.di

import dagger.Binds
import dagger.Module
import ru.eyelog.catsflow.RouterCats
import ru.eyelog.catsflow.router.RouterApp
import ru.eyelog.catsflow.router.RouterAppImpl
import ru.eyelog.core_common.annotations.ApplicationScope
import ru.eyelog.feature_favorites.RouterFavoritesList
import ru.eyelog.feature_mainlist.RouterMainList

@Module
abstract class ModuleRouter {

    @ApplicationScope
    @Binds
    abstract fun bindRouterApp(router: RouterAppImpl): RouterApp

    @ApplicationScope
    @Binds
    abstract fun bindRouterNews(router: RouterAppImpl): RouterCats

    @ApplicationScope
    @Binds
    abstract fun bindRouterMainList(router: RouterAppImpl): RouterMainList

    @ApplicationScope
    @Binds
    abstract fun bindRouterFavoritesList(router: RouterAppImpl): RouterFavoritesList
}