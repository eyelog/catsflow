package ru.eyelog.feature_mainlist.di

import ru.eyelog.feature_mainlist.ViewModelMainList
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import dagger.BindsInstance
import dagger.Component
import ru.eyelog.feature_mainlist.ui.MainListFragment
import ru.sibur.socialnetwork.core.utils.di.FeatureScope

@FeatureScope
@Component(
    dependencies = [DependenciesMainList::class],
    modules = [ModuleMainList::class]
)
interface ComponentMainList {

    @Component.Builder
    interface Builder {

        fun withDependencies(dep: DependenciesMainList): Builder

        @BindsInstance
        fun withActivity(fragment: FragmentActivity): Builder

        @BindsInstance
        fun withFragment(fragment: Fragment): Builder

        fun build(): ComponentMainList
    }

    fun inject(fragment: MainListFragment)

    fun provideViewModelMainList(): ViewModelMainList
}