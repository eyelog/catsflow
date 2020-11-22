package ru.eyelog.core_data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.eyelog.core_data.database.AppDatabase
import ru.eyelog.core_data.database.dao.DaoCats
import ru.eyelog.core_common.ApplicationScope

@Module
class ModuleRoom {
    @ru.eyelog.core_common.ApplicationScope
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "cats_db")
            .fallbackToDestructiveMigration()
            .build()

    @ru.eyelog.core_common.ApplicationScope
    @Provides
    fun providesDaoCats(db: AppDatabase): DaoCats = db.daoCats()
}