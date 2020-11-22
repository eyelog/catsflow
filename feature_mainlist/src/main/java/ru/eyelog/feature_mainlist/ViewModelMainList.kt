package ru.eyelog.feature_mainlist

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel

class ViewModelMainList (
    private val interactor: InteractorMainList,
    private val router: RouterMainList
) : ViewModel(), LifecycleObserver {


}