package com.example.data.storage

import com.example.data.models.CurrencyEntity
import com.example.data.storage.room.CurrencyDao

class DatabaseStorageImpl(private val dao: CurrencyDao) : DatabaseStorage {

    override suspend fun getAll(): List<CurrencyEntity> {
        return dao.getAll()
    }

    override suspend fun delete(currency: CurrencyEntity) {
        dao.delete(currency)
    }

    override suspend fun save(currency: CurrencyEntity) {
        dao.save(currency)
    }
}