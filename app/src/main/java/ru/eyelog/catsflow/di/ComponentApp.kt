package ru.eyelog.catsflow.di

import dagger.BindsInstance
import dagger.Component
import ru.eyelog.catsflow.CatsApplication
import ru.eyelog.catsflow.activity.di.DependenciesMain
import ru.eyelog.feature_favorites.di.DependenciesFavoriteCats
import ru.eyelog.feature_mainlist.di.DependenciesMainList
import ru.eyelog.feature_favorites.di.ModuleFavoriteCats
import ru.eyelog.feature_mainlist.di.ModuleMainList
import ru.eyelog.core_data.di.ModuleApi
import ru.eyelog.core_data.di.ModuleRoom
import ru.eyelog.core_common.annotations.ApplicationScope
import ru.eyelog.core_data.di.DependenciesGlide
import ru.eyelog.core_data.di.ModuleGlide
import javax.inject.Named

@ApplicationScope
@Component(
    modules = [
        ModuleApp::class,
        ModuleComponentDependencies::class,
        ModuleApi::class,
        ModuleRoom::class,
        ModuleRouter::class,
        ModuleMainList::class,
        ModuleFavoriteCats::class,
        ModuleGlide::class
    ]
)
interface ComponentApp :
    DependenciesMain,
    DependenciesMainList,
    DependenciesFavoriteCats,
    DependenciesGlide {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContainerId(@Named("container") containerId: Int): Builder

        fun appModule(appModule: ModuleApp): Builder

        fun build(): ComponentApp
    }

    fun inject(app: CatsApplication)
}