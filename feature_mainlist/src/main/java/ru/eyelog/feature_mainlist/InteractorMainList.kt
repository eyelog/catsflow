package ru.eyelog.feature_mainlist

import ru.eyelog.core_data.repository.RepositoryCats
import javax.inject.Inject

class InteractorMainList @Inject constructor(
    private val repositoryCats: RepositoryCats
) {
    fun getCatsList(
        order: String,
        offset: Int,
        limit: Int
    ) = repositoryCats.searchCats(order, offset, limit)
}