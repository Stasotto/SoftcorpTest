package com.example.data.storage.retrofit

import com.example.data.models.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyDataApi {
    //https://api.apilayer.com/fixer/latest?base=USD&symbols=BYN&apikey=YorApiKey
    @GET("latest?")
    suspend fun getCurrencies(
        @Query("base")
        nameOfCurrency: String,
        @Query("symbols")
        targetCurrency: String = "BYN",
        @Query("apikey")
        apiKey: String = RetrofitClient.API_KEY
    ): Response
}