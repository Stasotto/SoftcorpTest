package com.example.data.storage

import com.example.data.models.CurrencyEntity

interface DatabaseStorage {

    suspend fun getAll(): List<CurrencyEntity>

    suspend fun delete(currency: CurrencyEntity)

    suspend fun save(currency: CurrencyEntity)
}