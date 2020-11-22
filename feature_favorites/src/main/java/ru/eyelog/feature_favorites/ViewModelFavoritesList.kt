package ru.eyelog.feature_favorites

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel

class ViewModelFavoritesList (
    private val interactor: InteractorFavoritesList,
    private val router: RouterFavoritesList
) : ViewModel(), LifecycleObserver {


}
