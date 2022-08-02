package com.example.data

import com.example.data.storage.ApiStorage
import com.example.data.storage.DatabaseStorage
import com.example.domain.models.CurrencyDomain
import com.example.domain.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrencyRepositoryImpl(
    private val api: ApiStorage,
    private val database: DatabaseStorage
) : CurrencyRepository {

    override suspend fun saveInDatabase(currency: CurrencyDomain) {
        withContext(Dispatchers.IO) {
            database.save(currency.toCurrencyEntity())
        }
    }

    override suspend fun deleteFromDataBase(currency: CurrencyDomain) {
        withContext(Dispatchers.IO) {
            database.delete(currency.toCurrencyEntity())
        }
    }

    override suspend fun getFromDatabase(): List<CurrencyDomain> {
        return withContext(Dispatchers.IO) {
            database.getAll().map { currencyEntity ->
                currencyEntity.toCurrencyDomain()
            }
        }
    }

    override suspend fun getFromApi(): List<CurrencyDomain> {
        return api.getCurrencies().map { currencyEntity ->
            currencyEntity.toCurrencyDomain()
        }
    }
}