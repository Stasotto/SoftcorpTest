package com.example.softcorptest.presentation

import com.example.domain.models.CurrencyDomain
import com.example.softcorptest.presentation.models.Currency

fun Currency.toCurrencyDomain() = CurrencyDomain(
    name = name,
    value = value,
    isFavorites = isFavorites,
    primaryKey = primaryKey
)

fun CurrencyDomain.toCurrency() = Currency(
    name = name,
    value = value,
    isFavorites = isFavorites,
    primaryKey = primaryKey
)