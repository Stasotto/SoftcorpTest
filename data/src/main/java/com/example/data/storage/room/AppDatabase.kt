package com.example.data.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.models.CurrencyEntity

@Database(
    entities = [CurrencyEntity::class],
    version = AppDatabase.VERSION
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val VERSION = 1
    }

    abstract fun getCurrencyDao(): CurrencyDao
}