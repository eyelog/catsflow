package ru.eyelog.catsflow.activity.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.eyelog.catsflow.InteractorCats
import ru.eyelog.catsflow.activity.mainscreen.MainViewModel
import ru.eyelog.catsflow.router.RouterApp
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val interactor: InteractorCats,
    private val router: RouterApp
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(interactor, router) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
