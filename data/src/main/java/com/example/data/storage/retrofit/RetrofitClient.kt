package com.example.data.storage.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    const val API_KEY = "YourApiKey"
    private const val BASE_URL = "https://api.apilayer.com/fixer/"

    private var gson = GsonBuilder()
        .setLenient()
        .create()

    fun getCurrencyApi(): CurrencyDataApi = getClient().create(CurrencyDataApi::class.java)

    private fun getClient() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}