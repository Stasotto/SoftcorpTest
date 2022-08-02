package com.example.domain.usecases

import com.example.domain.models.CurrencyDomain
import com.example.domain.repository.CurrencyRepository

interface GetCurrenciesFromApiUseCas {

    suspend fun execute(): List<CurrencyDomain>
}

class GetCurrenciesFromApiUseCasImpl(
    private val repository: CurrencyRepository
) : GetCurrenciesFromApiUseCas {

    override suspend fun execute(): List<CurrencyDomain> {
        return repository.getFromApi()
    }
}