package com.example.softcorptest.presentation.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.softcorptest.presentation.models.Currency

class CurrenciesAdapter(
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CurrencyHolder>() {

    var currencyList: List<Currency> = emptyList()
        set(value) {
            val diffCallback = CurrenciesDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        return CurrencyHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        holder.bind(currencyList[position])
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }
}