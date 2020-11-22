package ru.eyelog.core_data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "photo_table")
data class DBCat(
    @PrimaryKey
    @ColumnInfo(name = "photo_id")
    val id: String,
    @ColumnInfo(name = "photo_url")
    val url: String,
    @ColumnInfo(name = "photo_width")
    val width: Int,
    @ColumnInfo(name = "photo_height")
    val height: Int
)