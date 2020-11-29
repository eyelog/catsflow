package ru.eyelog.core_data.repository

import ru.eyelog.core_data.database.AppDatabase
import ru.eyelog.core_data.database.dao.DaoCats
import ru.eyelog.core_data.mappers.getPhotoModel
import ru.eyelog.core_data.network.ApiCats
import javax.inject.Inject

class RepositoryCats @Inject constructor(
    private val apiAlbums: ApiCats,
    private val daoAlbums: DaoCats,
    private val appDatabase: AppDatabase
) {
    fun searchCats(
        order: String,
        offset: Int,
        limit: Int
    ) = apiAlbums.find(order, offset, limit)
            .map {
                it.map (::getPhotoModel)
            }
}