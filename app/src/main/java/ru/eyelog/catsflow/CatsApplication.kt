package ru.eyelog.catsflow

import android.app.Application
import android.content.res.Configuration
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import ru.eyelog.catsflow.di.ComponentApp
import ru.eyelog.catsflow.di.DaggerComponentApp
import ru.eyelog.catsflow.di.ModuleApp
import ru.eyelog.core_common.ComponentDependenciesProvider
import ru.eyelog.core_common.HasComponentDependencies
import javax.inject.Inject

class CatsApplication: Application(), HasComponentDependencies, LifecycleObserver {

    @Inject
    override lateinit var dependencies: ComponentDependenciesProvider
        protected set

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