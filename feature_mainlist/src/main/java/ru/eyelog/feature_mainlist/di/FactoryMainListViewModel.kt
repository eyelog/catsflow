package ru.eyelog.feature_mainlist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.eyelog.feature_mainlist.InteractorMainList
import ru.eyelog.feature_mainlist.RouterMainList
import ru.eyelog.feature_mainlist.ViewModelMainList
import javax.inject.Inject

class FactoryMainListViewModel @Inject constructor(
    private val interactor: InteractorMainList,
    private val router: RouterMainList
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(ViewModelMainList::class.java)) {
            return ViewModelMainList(interactor, router) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}