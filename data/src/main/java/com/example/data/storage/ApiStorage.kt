package com.example.data.storage

import com.example.data.models.CurrencyEntity

interface ApiStorage {

    suspend fun getCurrencies():List<CurrencyEntity>
}