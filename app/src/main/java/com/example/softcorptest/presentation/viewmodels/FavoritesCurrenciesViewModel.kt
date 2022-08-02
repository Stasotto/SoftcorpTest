package com.example.softcorptest.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.DeleteCurrencyFromDatabaseUseCase
import com.example.domain.usecases.GetCurrenciesFromDatabaseUseCase
import com.example.softcorptest.presentation.models.Currency
import com.example.softcorptest.presentation.toCurrency
import com.example.softcorptest.presentation.toCurrencyDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesCurrenciesViewModel(
    private val getCurrenciesFromDatabaseUseCase: GetCurrenciesFromDatabaseUseCase,
    private val deleteCurrencyFromDatabaseUseCase: DeleteCurrencyFromDatabaseUseCase
) : ViewModel() {

    private val _favoritesCurrencies = MutableStateFlow(emptyList<Currency>())
    val favoritesCurrencies: StateFlow<List<Currency>> = _favoritesCurrencies.asStateFlow()

    init {
        getCurrenciesFromDatabase()
    }

    fun deleteCurrencyFromDatabase(currency: Currency) {
        viewModelScope.launch {
            deleteCurrencyFromDatabaseUseCase.execute(currency.toCurrencyDomain())
            getCurrenciesFromDatabaseUseCase
        }
    }

    fun removeCurrency(position: Int) {
        val currenciesList = _favoritesCurrencies.value.toMutableList()
        currenciesList.removeAt(position)
        _favoritesCurrencies.value = currenciesList
    }

    private fun getCurrenciesFromDatabase() {
        viewModelScope.launch {
            _favoritesCurrencies.value =
                getCurrenciesFromDatabaseUseCase.execute().map { currencyDomain ->
                    currencyDomain.toCurrency()
                }

        }
    }

    fun sortCurrenciesByName(isDescending: Boolean) {
        _favoritesCurrencies.value = if (isDescending) {
            _favoritesCurrencies.value.sortedByDescending {
                it.name
            }
        } else {
            _favoritesCurrencies.value.sortedBy {
                it.name
            }
        }
    }

    fun sortCurrenciesByValue(isDescending: Boolean) {
        _favoritesCurrencies.value = if (isDescending) {
            _favoritesCurrencies.value.sortedByDescending {
                it.value
            }
        } else {
            _favoritesCurrencies.value.sortedBy {
                it.value
            }
        }
    }
}