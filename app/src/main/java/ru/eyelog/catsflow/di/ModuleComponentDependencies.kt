package ru.eyelog.catsflow.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.eyelog.feature_favorites.di.DependenciesFavoriteCats
import ru.eyelog.feature_mainlist.di.DependenciesMainList
import ru.eyelog.core_common.ComponentDependencies

@Module
abstract class ModuleComponentDependencies {

    @Binds
    @IntoMap
    @ComponentDependenciesKey(DependenciesMain::class)
    abstract fun provideDependenciesMain(component: ComponentApp): ComponentDependencies

//    @Binds
//    @IntoMap
//    @ComponentDependenciesKey(DependenciesGlide::class)
//    abstract fun provideDependenciesGlide(component: ComponentApp): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(DependenciesMainList::class)
    abstract fun provideDependenciesMainList(component: ComponentApp): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(DependenciesFavoriteCats::class)
    abstract fun provideDependenciesFavoriteCats(component: ComponentApp): ComponentDependencies
}