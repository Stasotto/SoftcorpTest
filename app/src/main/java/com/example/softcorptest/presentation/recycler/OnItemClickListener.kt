package com.example.softcorptest.presentation.recycler

import com.example.softcorptest.presentation.models.Currency

interface OnItemClickListener {

    fun onSaveClickListener(currency: Currency, position: Int)

    fun onDeleteClickListener(currency: Currency, position: Int)
}