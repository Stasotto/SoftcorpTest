package com.example.softcorptest.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.softcorptest.presentation.models.Currency

class CurrenciesDiffCallback(
    private val oldList: List<Currency>,
    private val newList: List<Currency>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList == newList
    }
}