package ru.eyelog.core_data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import ru.eyelog.core_data.database.entities.DBCat

@Dao
abstract class DaoCats {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertPhotos(vararg photo: DBCat): LongArray

    @Query("SELECT * FROM photo_table")
    abstract fun getPhotosList(): Single<List<DBCat>>

    @Query(
        """
            SELECT * FROM photo_table
            ORDER BY photo_id DESC
            LIMIT :limit OFFSET :offset
        """
    )
    abstract fun searchPhotos(
        offset: Int,
        limit: Int
    ): Single<List<DBCat>>

    @Query("SELECT * FROM photo_table WHERE photo_id = :id")
    abstract fun getPhotoById(id: Long): Single<DBCat>
}