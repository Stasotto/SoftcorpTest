package com.example.softcorptest.presentation.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.softcorptest.R
import com.example.softcorptest.databinding.ItemCurrencyBinding
import com.example.softcorptest.presentation.models.Currency

class CurrencyHolder(
    item: View,
    private val listener: OnItemClickListener
) : RecyclerView.ViewHolder(item) {

    companion object {
        fun from(parent: ViewGroup, listener: OnItemClickListener) = CurrencyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false),
            listener
        )
    }

    private val binding by lazy { ItemCurrencyBinding.bind(item) }

    @SuppressLint("SetTextI18n")
    fun bind(currency: Currency) = with(binding) {
        currencyName.text = "${currency.name}/BYN"
        currencyValue.text = currency.value.toString()

        if (currency.isFavorites) {
            favorites.setImageResource(R.drawable.ic_baseline_star_24)
        } else {
            favorites.setImageResource(R.drawable.ic_baseline_star_outline_24)
        }

        favorites.setOnClickListener {
            if (currency.isFavorites) {
                listener.onDeleteClickListener(currency, bindingAdapterPosition)
            } else {
                listener.onSaveClickListener(currency, bindingAdapterPosition)
            }
        }
    }
}