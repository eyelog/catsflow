package ru.eyelog.feature_mainlist

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import ru.eyelog.core_common.abstractions.BaseViewModel
import ru.eyelog.core_data.models.CatPhoto
import timber.log.Timber

class ViewModelMainList (
    private val interactor: InteractorMainList,
    private val router: RouterMainList
) : BaseViewModel(), LifecycleObserver {

    val catsLiveData: LiveData<List<CatPhoto>> get() = _catsLiveData
    private val _catsLiveData = MediatorLiveData<List<CatPhoto>>()

    private val order = "Desc"
    private var offset = 0
    private val limit = 20

    fun searchCats() {

        Timber.i("Logcat searchCats() called")

        interactor.getCatsList(order, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    _catsLiveData.postValue(it)
                }
                .addTo(compositeDisposable)
    }
}
