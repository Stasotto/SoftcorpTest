package com.example.softcorptest.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.DeleteCurrencyFromDatabaseUseCase
import com.example.domain.usecases.GetCurrenciesFromApiUseCas
import com.example.domain.usecases.GetCurrenciesFromDatabaseUseCase
import com.example.domain.usecases.SaveCurrencyInDatabaseUseCase
import javax.inject.Inject

class ViewModelsFactory @Inject constructor(
    private val getCurrenciesFromDatabaseUseCase: GetCurrenciesFromDatabaseUseCase,
    private val saveCurrencyInDatabaseUseCase: SaveCurrencyInDatabaseUseCase,
    private val deleteCurrencyFromDatabaseUseCase: DeleteCurrencyFromDatabaseUseCase,
    private val getCurrenciesFromApiUseCas: GetCurrenciesFromApiUseCas
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        Log.d("MyLog", modelClass.toString())
        Log.d("MyLog", PopularCurrenciesViewModel::class.java.toString())
        return when (modelClass) {
            PopularCurrenciesViewModel::class.java -> PopularCurrenciesViewModel(
                getCurrenciesFromDatabaseUseCase,
                saveCurrencyInDatabaseUseCase,
                deleteCurrencyFromDatabaseUseCase,
                getCurrenciesFromApiUseCas
            ) as T
            else -> FavoritesCurrenciesViewModel(
                getCurrenciesFromDatabaseUseCase,
                deleteCurrencyFromDatabaseUseCase
            ) as T
        }
    }
}