package ru.eyelog.catsflow.activity.di

import androidx.fragment.app.FragmentActivity
import dagger.BindsInstance
import dagger.Component
import ru.eyelog.catsflow.activity.mainscreen.MainActivity
import ru.sibur.socialnetwork.core.utils.di.MainScope

@MainScope
@Component(
    dependencies = [DependenciesMain::class],
    modules = [ModuleMain::class]
)
interface ComponentMain {

    @Component.Builder
    interface Builder {

        fun withDependencies(dep: DependenciesMain): Builder

        @BindsInstance
        fun withActivity(activity: FragmentActivity): Builder

        fun build(): ComponentMain
    }

    fun inject(activity: MainActivity)
}
