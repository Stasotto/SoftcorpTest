package com.example.data.storage.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.models.CurrencyEntity

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency")
    fun getAll(): List<CurrencyEntity>

    @Delete
    fun delete(currency: CurrencyEntity)

    @Insert
    fun save(currency: CurrencyEntity)
}