package ru.eyelog.catsflow

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import ru.eyelog.catsflow.di.ComponentApp
import ru.eyelog.catsflow.di.DaggerComponentApp
import ru.eyelog.catsflow.di.ModuleApp

class CatsApplication: Application(), LifecycleObserver {

    lateinit var appComponent: ComponentApp

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        appComponent = DaggerComponentApp.builder()
            .appModule(ModuleApp(this))
            .withContainerId(R.id.mainFragmentSpace)
            .build()
        appComponent.inject(this)
    }
}