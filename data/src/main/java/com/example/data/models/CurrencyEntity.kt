package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyEntity(
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val value: Double,
    @ColumnInfo
    val isFavorites: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val primaryKey: Int = 0
)
