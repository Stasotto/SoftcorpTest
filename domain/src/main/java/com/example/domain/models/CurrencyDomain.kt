package com.example.domain.models

data class CurrencyDomain(
    val name: String,
    val value: Double,
    val isFavorites: Boolean,
    val primaryKey: Int
)