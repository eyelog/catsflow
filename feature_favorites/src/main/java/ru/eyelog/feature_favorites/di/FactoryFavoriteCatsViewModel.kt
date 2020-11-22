package ru.eyelog.feature_favorites.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.eyelog.feature_favorites.InteractorFavoritesList
import ru.eyelog.feature_favorites.RouterFavoritesList
import ru.eyelog.feature_favorites.ViewModelFavoritesList
import javax.inject.Inject

class FactoryFavoriteCatsViewModel @Inject constructor(
    private val interactor: InteractorFavoritesList,
    private val router: RouterFavoritesList
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(ViewModelFavoritesList::class.java)) {
            return ViewModelFavoritesList(interactor, router) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}