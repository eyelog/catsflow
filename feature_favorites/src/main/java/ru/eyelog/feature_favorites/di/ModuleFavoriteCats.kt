package ru.eyelog.feature_favorites.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ru.eyelog.feature_favorites.ViewModelFavoritesList

@Module
class ModuleFavoriteCats {
    @Provides
    fun provideFavoriteCatsViewModel(fragment: FragmentActivity, factory: FactoryFavoriteCatsViewModel): ViewModelFavoritesList {
        return ViewModelProvider(fragment, factory).get(ViewModelFavoritesList::class.java)
    }
}