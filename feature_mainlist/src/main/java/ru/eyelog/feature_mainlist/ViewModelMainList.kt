package ru.eyelog.feature_mainlist

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import ru.eyelog.core_common.abstractions.BaseViewModel

class ViewModelMainList (
    private val interactor: InteractorMainList,
    private val router: RouterMainList
) : BaseViewModel(), LifecycleObserver {


}