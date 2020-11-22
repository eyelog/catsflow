package ru.eyelog.feature_favorites

import ru.eyelog.core_data.repository.RepositoryCats
import javax.inject.Inject

class InteractorFavoritesList @Inject constructor(
    private val repositoryCats: RepositoryCats
) {
    fun getCatsList(
        order: String,
        offset: Int,
        limit: Int
    ) = repositoryCats.searchCats(order, offset, limit)
}