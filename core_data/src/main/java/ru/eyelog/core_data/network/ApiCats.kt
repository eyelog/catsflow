package ru.eyelog.core_data.network

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.eyelog.core_data.network.models.ResponsePhoto

interface ApiCats {

    @GET("images/search")
    fun find(
        @Query("order") order: String,
        @Query("page") offset: Int,
        @Query("limit") limit: Int
    ): Flowable<List<ResponsePhoto>>
}
