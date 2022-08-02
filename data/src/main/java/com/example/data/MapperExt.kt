package com.example.data

import com.example.data.models.CurrencyEntity
import com.example.data.models.Response
import com.example.domain.models.CurrencyDomain

fun Response.toCurrencyEntity() = CurrencyEntity(
    name = base,
    value = rates.BYN,
)

fun CurrencyEntity.toCurrencyDomain() = CurrencyDomain(
    name = name,
    value = value,
    isFavorites = isFavorites,
    primaryKey = primaryKey
)

fun CurrencyDomain.toCurrencyEntity() = CurrencyEntity(
    name = name,
    value = value,
    isFavorites = true,
    primaryKey = primaryKey
)