package ru.eyelog.catsflow.activity.mainscreen

import androidx.lifecycle.LifecycleObserver
import ru.eyelog.catsflow.InteractorCats
import ru.eyelog.catsflow.RouterCats
import ru.eyelog.core_common.abstractions.BaseViewModel

class MainViewModel(
    private val interactor: InteractorCats,
    private val router: RouterCats
): BaseViewModel(), LifecycleObserver {

}