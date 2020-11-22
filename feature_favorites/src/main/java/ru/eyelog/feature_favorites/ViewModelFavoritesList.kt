package ru.eyelog.feature_favorites

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import ru.eyelog.core_common.abstractions.BaseViewModel

class ViewModelFavoritesList (
    private val interactor: InteractorFavoritesList,
    private val router: RouterFavoritesList
) : BaseViewModel(), LifecycleObserver {


}
