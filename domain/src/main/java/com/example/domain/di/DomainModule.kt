package com.example.domain.di

import com.example.domain.repository.CurrencyRepository
import com.example.domain.usecases.*
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DomainBindModule::class])
class DomainModule {
    @Provides
    fun provideDeleteCurrencyFromDatabaseUseCaseImpl(repository: CurrencyRepository) =
        DeleteCurrencyFromDatabaseUseCaseImpl(repository)

    @Provides
    fun provideGetCurrenciesFromApiUseCaseImpl(repository: CurrencyRepository) =
        GetCurrenciesFromApiUseCasImpl(repository)

    @Provides
    fun provideGetCurrenciesFromDatabaseUseCaseImpl(repository: CurrencyRepository) =
        GetCurrenciesFromDatabaseUseCaseImpl(repository)

    @Provides
    fun provideSaveCurrencyInDatabaseUseCaseImpl(repository: CurrencyRepository) =
        SaveCurrencyInDatabaseUseCaseImpl(repository)
}

@Module
interface DomainBindModule {

    @Suppress("FunctionName")
    @Binds
    fun bindDeleteCurrencyFromDatabaseUseCaseImpl_to_DeleteCurrencyFromDatabaseUseCase(
        useCase: DeleteCurrencyFromDatabaseUseCaseImpl
    ): DeleteCurrencyFromDatabaseUseCase

    @Suppress("FunctionName")
    @Binds
    fun bindGetCurrenciesFromApiUseCaseImpl_to_GetCurrenciesFromApiUseCase(
        useCase: GetCurrenciesFromApiUseCasImpl
    ): GetCurrenciesFromApiUseCas

    @Suppress("FunctionName")
    @Binds
    fun bindGetCurrenciesFromDatabaseUseCaseImpl_to_CurrenciesFromDatabaseUseCase(
        useCase: GetCurrenciesFromDatabaseUseCaseImpl
    ): GetCurrenciesFromDatabaseUseCase

    @Suppress("FunctionName")
    @Binds
    fun bindSaveCurrencyInDatabaseUseCaseImpl_to_SaveCurrencyInDatabaseUseCase(
        useCase: SaveCurrencyInDatabaseUseCaseImpl
    ): SaveCurrencyInDatabaseUseCase
}