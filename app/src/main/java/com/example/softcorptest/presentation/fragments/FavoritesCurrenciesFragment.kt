package com.example.softcorptest.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.softcorptest.R
import com.example.softcorptest.appComponent
import com.example.softcorptest.databinding.FragmentFavoritesCurrenciesBinding
import com.example.softcorptest.presentation.contract.Navigator
import com.example.softcorptest.presentation.contract.navigator
import com.example.softcorptest.presentation.models.Currency
import com.example.softcorptest.presentation.recycler.CurrenciesAdapter
import com.example.softcorptest.presentation.recycler.OnItemClickListener
import com.example.softcorptest.presentation.viewmodels.FavoritesCurrenciesViewModel
import com.example.softcorptest.presentation.viewmodels.ViewModelsFactory
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class FavoritesCurrenciesFragment : Fragment(R.layout.fragment_favorites_currencies) {

    companion object {
        const val TAG = "Favorites"
        fun newInstance() = FavoritesCurrenciesFragment()
    }

    private val binding by viewBinding<FragmentFavoritesCurrenciesBinding>()
    private val viewModel by lazy {
        ViewModelProvider(this, factory).get(FavoritesCurrenciesViewModel::class.java)
    }

    @Inject
    lateinit var factory: ViewModelsFactory

    private val adapter by lazy { CurrenciesAdapter(listener) }

    private val listener: OnItemClickListener = object : OnItemClickListener {

        override fun onSaveClickListener(currency: Currency, position: Int) {
        }

        override fun onDeleteClickListener(currency: Currency, position: Int) {
            viewModel.deleteCurrencyFromDatabase(currency)
            viewModel.removeCurrency(position)
        }

    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        navigator.sortBy { typeOfSort ->
            when (typeOfSort) {
                Navigator.TypeOfSort.BY_NAME -> viewModel.sortCurrenciesByName(false)
                Navigator.TypeOfSort.BY_NAME_DESC -> viewModel.sortCurrenciesByName(true)
                Navigator.TypeOfSort.BY_VALUE -> viewModel.sortCurrenciesByValue(false)
                Navigator.TypeOfSort.BY_VALUE_DESC -> viewModel.sortCurrenciesByValue(true)
            }
        }
    }

    private fun setUpRecycler() = with(binding) {
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
        val itemAnimator = recycler.itemAnimator
        if (itemAnimator is DefaultItemAnimator) {
            itemAnimator.supportsChangeAnimations = false
        }

        lifecycleScope.launchWhenStarted {
            viewModel.favoritesCurrencies.collect {
                adapter.currencyList = it
            }
        }
    }
}