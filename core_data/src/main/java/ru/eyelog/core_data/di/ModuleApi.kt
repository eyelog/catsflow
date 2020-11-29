package ru.eyelog.core_data.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.eyelog.core_data.network.ApiCats
import ru.eyelog.core_data.network.interceptors.InterceptorAuth
import ru.eyelog.core_data.network.interceptors.InterceptorLogging
import ru.eyelog.core_common.annotations.ApplicationScope
import java.util.concurrent.TimeUnit

@Module
class ModuleApi {

    @ApplicationScope
    @Provides
    fun ApiCats(retrofit: Retrofit): ApiCats =
        retrofit.create(ApiCats::class.java)

    @ApplicationScope
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptorAuth = InterceptorAuth()
        val interceptorLogging =
            InterceptorLogging().apply { level = InterceptorLogging.Level.BODY }
        return OkHttpClient.Builder()
            .connectTimeout(NETWORK_CONNECT_TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(NETWORK_READ_TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_WRITE_TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(interceptorAuth)
            .addInterceptor(interceptorLogging)
            .build()
    }

    @ApplicationScope
    @Provides
    fun provideRetrofitJson(okHttpClient: OkHttpClient):
            Retrofit {
        val contentType = "application/json".toMediaType()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    companion object {
        private const val NETWORK_CONNECT_TIME_OUT_SECONDS = 20L
        private const val NETWORK_READ_TIME_OUT_SECONDS = 80L
        private const val NETWORK_WRITE_TIME_OUT_SECONDS = 40L
    }
}