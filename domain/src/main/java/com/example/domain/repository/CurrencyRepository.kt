package com.example.domain.repository

import com.example.domain.models.CurrencyDomain

interface CurrencyRepository {

    suspend fun saveInDatabase(currency: CurrencyDomain)

    suspend fun deleteFromDataBase(currency: CurrencyDomain)

    suspend fun getFromDatabase(): List<CurrencyDomain>

    suspend fun getFromApi(): List<CurrencyDomain>
}