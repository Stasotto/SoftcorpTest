package com.example.softcorptest.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.softcorptest.R
import com.example.softcorptest.appComponent
import com.example.softcorptest.databinding.FragmentPopularCurrenciesBinding
import com.example.softcorptest.presentation.contract.Navigator
import com.example.softcorptest.presentation.contract.navigator
import com.example.softcorptest.presentation.models.Currency
import com.example.softcorptest.presentation.recycler.CurrenciesAdapter
import com.example.softcorptest.presentation.recycler.OnItemClickListener
import com.example.softcorptest.presentation.viewmodels.PopularCurrenciesViewModel
import com.example.softcorptest.presentation.viewmodels.ViewModelsFactory
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class PopularCurrenciesFragment : Fragment(R.layout.fragment_popular_currencies) {

    companion object {
        const val TAG = "Popular"
        fun newInstance() = PopularCurrenciesFragment()
    }

    private val binding by viewBinding<FragmentPopularCurrenciesBinding>()
    private val adapter by lazy { CurrenciesAdapter(listener) }
    private val viewModel by viewModels<PopularCurrenciesViewModel> {
        factory
    }

    private val listener: OnItemClickListener = object : OnItemClickListener {

        override fun onSaveClickListener(currency: Currency, position: Int) {
            viewModel.saveCurrencyInDatabase(currency)
            viewModel.updateCurrency(position)
        }

        override fun onDeleteClickListener(currency: Currency, position: Int) {
            viewModel.deleteCurrencyFromDatabase(currency)
            viewModel.updateCurrency(position)
        }
    }

    @Inject
    lateinit var factory: ViewModelsFactory

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCurrencies()
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
            viewModel.currencies.collect {
                adapter.currencyList = it
            }
        }
    }
}