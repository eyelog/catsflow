package ru.eyelog.core_data.mappers

import ru.eyelog.core_data.models.CatPhoto
import ru.eyelog.core_data.network.models.ResponsePhoto

fun getPhotoModel(responsePhoto: ResponsePhoto) = CatPhoto(
        id = responsePhoto.id ?: "",
        url = responsePhoto.url ?: "",
        height = responsePhoto.height ?: 0,
        width = responsePhoto.width ?: 0
    )