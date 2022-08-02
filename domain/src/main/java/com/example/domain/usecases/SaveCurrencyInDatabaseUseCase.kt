package com.example.domain.usecases

import com.example.domain.models.CurrencyDomain
import com.example.domain.repository.CurrencyRepository

interface SaveCurrencyInDatabaseUseCase {

    suspend fun execute(currency: CurrencyDomain)
}

class SaveCurrencyInDatabaseUseCaseImpl(
    private val repository: CurrencyRepository
) : SaveCurrencyInDatabaseUseCase {

    override suspend fun execute(currency: CurrencyDomain) {
        repository.saveInDatabase(currency)
    }
}