package com.example.data.storage

import com.example.data.models.CurrencyEntity
import com.example.data.storage.retrofit.CurrencyDataApi
import com.example.data.toCurrencyEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApiStorageImpl(private val api: CurrencyDataApi) : ApiStorage {

    enum class Currencies {
        USD,
        EUR,
        RUB,
        CNY,
        JPY,
        SEK,
        TRY,
        UAH,
        GBP,
        BGN,
        BOB,
        CDF,
        CLP,
        BTC,
        DKK,
        CZK,
        HRK,
        HKD
    }

    override suspend fun getCurrencies(): List<CurrencyEntity> {
        val currenciesList = mutableListOf<CurrencyEntity>()
        val a = Currencies.values()
        CoroutineScope(Dispatchers.IO).launch {
            a.forEach { currencies ->
                launch {
                    currenciesList.add(api.getCurrencies(currencies.name).toCurrencyEntity())
                }
            }
        }.join()
        return currenciesList
    }
}