package ru.eyelog.core_data.di

import okhttp3.OkHttpClient
import ru.eyelog.core_common.ComponentDependencies
import javax.inject.Named

interface DependenciesGlide : ComponentDependencies {

    @Named("glideOkHttpClient")
    fun provideGlideOkHttpClient(): OkHttpClient
}
