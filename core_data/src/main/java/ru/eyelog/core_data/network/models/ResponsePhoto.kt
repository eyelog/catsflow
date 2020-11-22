package ru.eyelog.core_data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePhoto(
    @SerialName("id") val id: String? = "",
    @SerialName("url") val url: String? = "",
    @SerialName("width") val width: Int? = 0,
    @SerialName("height") val height: Int? = 0
)