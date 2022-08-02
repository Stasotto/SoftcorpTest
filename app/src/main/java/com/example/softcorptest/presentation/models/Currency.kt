package com.example.softcorptest.presentation.models

data class Currency(
    val name: String,
    val value: Double,
    val isFavorites: Boolean,
    val primaryKey: Int
)