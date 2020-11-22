package ru.eyelog.feature_favorites.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import dagger.BindsInstance
import dagger.Component
import ru.eyelog.feature_favorites.ViewModelFavoritesList
import ru.eyelog.feature_favorites.ui.FavoriteCatsFragment

@Component(
    dependencies = [DependenciesFavoriteCats::class],
    modules = [ModuleFavoriteCats::class]
)
interface ComponentFavoriteCats {

    @Component.Builder
    interface Builder {

        fun withDependencies(dep: DependenciesFavoriteCats): Builder

        @BindsInstance
        fun withActivity(fragment: FragmentActivity): Builder

        @BindsInstance
        fun withFragment(fragment: Fragment): Builder

        fun build(): ComponentFavoriteCats
    }

    fun inject(fragment: FavoriteCatsFragment)

    fun provideViewModelFavoritesList(): ViewModelFavoritesList
}