package ru.eyelog.catsflow.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.eyelog.catsflow.router.RouterAppImpl
import ru.eyelog.core_data.utils.ApplicationScope
import javax.inject.Named

@Module
class ModuleApp(private var appContext: Context) {

    @ApplicationScope
    @Provides
    fun getContext(): Context = appContext

    @ApplicationScope
    @Provides
    fun provideRouter(
        @Named("container") containerId: Int
    ): RouterAppImpl {
        return RouterAppImpl(containerId)
    }


}