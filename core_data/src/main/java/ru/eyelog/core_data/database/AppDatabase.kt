package ru.eyelog.core_data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.eyelog.core_data.database.dao.DaoCats
import ru.eyelog.core_data.database.entities.DBCat

@Database(
    entities = [
        DBCat::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun daoCats(): DaoCats
}