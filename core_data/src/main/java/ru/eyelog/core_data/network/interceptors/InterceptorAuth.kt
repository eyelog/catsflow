package ru.eyelog.core_data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class InterceptorAuth @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("x-api-key", "ec055a9b-cf45-4345-9fce-25597209e622")
            .build()
        return chain.proceed(newRequest)
    }
}