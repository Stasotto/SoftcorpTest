package com.example.domain.usecases

import com.example.domain.models.CurrencyDomain
import com.example.domain.repository.CurrencyRepository

interface GetCurrenciesFromDatabaseUseCase {
    suspend fun execute(): List<CurrencyDomain>
}

class GetCurrenciesFromDatabaseUseCaseImpl(
    private val repository: CurrencyRepository
) : GetCurrenciesFromDatabaseUseCase {
    override suspend fun execute(): List<CurrencyDomain> {
        return repository.getFromDatabase()
    }
}