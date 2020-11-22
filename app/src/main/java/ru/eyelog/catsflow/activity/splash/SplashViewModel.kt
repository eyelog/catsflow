package ru.eyelog.catsflow.activity.splash

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Flowable.interval
import io.reactivex.disposables.Disposable
import ru.eyelog.catsflow.utils.extensions.disposeOnDestroy
import ru.eyelog.catsflow.utils.extensions.ioMain
import java.util.concurrent.TimeUnit

class SplashViewModel: ViewModel(), LifecycleObserver {

    val splashLiveData: LiveData<Boolean> get() = _splashLiveData
    private val _splashLiveData = MediatorLiveData<Boolean>()

    private lateinit var disposable: Disposable

    init {
        val counter = interval(LOADING_OFFSET, TimeUnit.MILLISECONDS)
        disposable = counter
            .ioMain()
            .subscribe {
                _splashLiveData.value = true
                disposable.dispose()
            }
            .disposeOnDestroy()
    }

    private companion object {
        private const val LOADING_OFFSET = 1600L
    }
}