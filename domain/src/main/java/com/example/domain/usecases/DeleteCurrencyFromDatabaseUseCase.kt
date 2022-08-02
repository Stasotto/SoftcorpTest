package com.example.domain.usecases

import com.example.domain.models.CurrencyDomain
import com.example.domain.repository.CurrencyRepository

interface DeleteCurrencyFromDatabaseUseCase {

    suspend fun execute(currency: CurrencyDomain)
}

class DeleteCurrencyFromDatabaseUseCaseImpl(
    private val repository: CurrencyRepository
) : DeleteCurrencyFromDatabaseUseCase {

    override suspend fun execute(currency: CurrencyDomain) {
        repository.deleteFromDataBase(currency)
    }
}