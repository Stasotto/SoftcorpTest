package com.example.softcorptest.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.DeleteCurrencyFromDatabaseUseCase
import com.example.domain.usecases.GetCurrenciesFromApiUseCas
import com.example.domain.usecases.GetCurrenciesFromDatabaseUseCase
import com.example.domain.usecases.SaveCurrencyInDatabaseUseCase
import com.example.softcorptest.presentation.models.Currency
import com.example.softcorptest.presentation.toCurrency
import com.example.softcorptest.presentation.toCurrencyDomain
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PopularCurrenciesViewModel(
    private val getCurrenciesFromDatabaseUseCase: GetCurrenciesFromDatabaseUseCase,
    private val saveCurrencyInDatabaseUseCase: SaveCurrencyInDatabaseUseCase,
    private val deleteCurrencyFromDatabaseUseCase: DeleteCurrencyFromDatabaseUseCase,
    private val getCurrenciesFromApiUseCas: GetCurrenciesFromApiUseCas
) : ViewModel() {

    private val _defaultCurrenciesFromApi = MutableStateFlow(emptyList<Currency>())
    private val _currencies = MutableStateFlow(emptyList<Currency>())
    val currencies: StateFlow<List<Currency>> = _currencies.asStateFlow()

    fun getCurrencies() {
        viewModelScope.launch {
            val currenciesFromDatabase = async {
                return@async getCurrenciesFromDatabase()
            }.await()
            val currenciesFromApi = async {
                if (_defaultCurrenciesFromApi.value.isEmpty()) {
                    return@async getCurrenciesFromApi()
                } else {
                    return@async _defaultCurrenciesFromApi.value
                }
            }.await()

            checkSavedCurrencies(currenciesFromDatabase, currenciesFromApi)
        }
    }

    fun updateCurrency(position: Int) {
        val currency = _currencies.value[position]
        val currenciesList = _currencies.value.toMutableList()
        currenciesList[position] = currency.copy(isFavorites = !currency.isFavorites)
        _currencies.value = currenciesList
    }

    fun sortCurrenciesByName(isDescending: Boolean) {
        _currencies.value = if (isDescending) {
            _currencies.value.sortedByDescending {
                it.name
            }
        } else {
            _currencies.value.sortedBy {
                it.name
            }
        }
    }

    fun sortCurrenciesByValue(isDescending: Boolean) {
        _currencies.value = if (isDescending) {
            _currencies.value.sortedByDescending {
                it.value
            }
        } else {
            _currencies.value.sortedBy {
                it.value
            }
        }
    }

    fun saveCurrencyInDatabase(currency: Currency) {
        viewModelScope.launch {
            saveCurrencyInDatabaseUseCase.execute(currency.toCurrencyDomain())
        }
    }

    fun deleteCurrencyFromDatabase(currency: Currency) {
        viewModelScope.launch {
            deleteCurrencyFromDatabaseUseCase.execute(currency.toCurrencyDomain())
        }
    }


    private fun checkSavedCurrencies(
        currenciesFromDatabase: List<Currency>?,
        currenciesFromApi: List<Currency>?
    ) {
        val currenciesList = currenciesFromApi?.toMutableList() ?: return
        _defaultCurrenciesFromApi.value = currenciesFromApi
        currenciesList.forEachIndexed { index, currency ->
            currenciesFromDatabase?.forEach {
                if (currency.name == it.name) {
                    currenciesList[index] = it
                }
            }
            _currencies.value = currenciesList
        }
    }

    private suspend fun getCurrenciesFromApi(): List<Currency> {
        return getCurrenciesFromApiUseCas.execute().map { currencyDomain ->
            currencyDomain.toCurrency()
        }
    }

    private suspend fun getCurrenciesFromDatabase(): List<Currency> {
        return getCurrenciesFromDatabaseUseCase.execute().map { currencyDomain ->
            currencyDomain.toCurrency()
        }
    }
}