package ru.eyelog.catsflow

import ru.eyelog.core_data.repository.RepositoryCats
import javax.inject.Inject

class InteractorCats @Inject constructor(
    private val repositoryCats: RepositoryCats
) {
    fun getCatsList(
        order: String,
        offset: Int,
        limit: Int
    ) = repositoryCats.searchCats(order, offset, limit)
}