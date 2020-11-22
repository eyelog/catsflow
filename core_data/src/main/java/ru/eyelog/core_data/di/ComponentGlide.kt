package ru.eyelog.core_data.di

import dagger.Component
import ru.eyelog.core_common.annotations.ApplicationScope

@ApplicationScope
@Component(
    dependencies = [DependenciesGlide::class],
    modules = [ModuleGlide::class]
)
interface ComponentGlide {

    @Component.Builder
    interface Builder {

        fun withDependencies(dep: DependenciesGlide): Builder

        fun build(): ComponentGlide
    }

    fun inject(moduleOkHttpGlide: CustomGlideModule)
}