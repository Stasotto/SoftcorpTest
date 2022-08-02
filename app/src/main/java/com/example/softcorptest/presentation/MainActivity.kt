package com.example.softcorptest.presentation

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.softcorptest.R
import com.example.softcorptest.databinding.ActivityMainBinding
import com.example.softcorptest.presentation.contract.Navigator
import com.example.softcorptest.presentation.fragments.FavoritesCurrenciesFragment
import com.example.softcorptest.presentation.fragments.PopularCurrenciesFragment

typealias SortByListener = (Navigator.TypeOfSort) -> Unit

class MainActivity : AppCompatActivity(R.layout.activity_main), Navigator {
    companion object {
        private const val ID_SORT_BY_NAME = 1
        private const val ID_SORT_BY_NAME_DESC = 2
        private const val ID_SORT_BY_VALUE = 3
        private const val ID_SORT_BY_VALUE_DESC = 4
    }

    private val binding: ActivityMainBinding by viewBinding()
    private var sortActionListener: SortByListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openPopularCurrenciesFragment()
        binding.sort.setOnClickListener {
            showPopupMenu(it)
        }
        binding.botNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.popular -> if (supportFragmentManager.findFragmentByTag(
                        FavoritesCurrenciesFragment.TAG
                    )?.isVisible == true
                ) {
                    supportFragmentManager.popBackStack()

                }
                R.id.favorites -> {
                    if (supportFragmentManager.findFragmentByTag(
                            PopularCurrenciesFragment.TAG
                        )?.isVisible == true
                    ) {
                        openFragment(
                            FavoritesCurrenciesFragment.newInstance(),
                            FavoritesCurrenciesFragment.TAG
                        )
                    }
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun sortBy(listener: SortByListener) {
        sortActionListener = listener
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menu.add(0, ID_SORT_BY_NAME, Menu.NONE, R.string.sortByName)
        popupMenu.menu.add(0, ID_SORT_BY_NAME_DESC, Menu.NONE, R.string.sortByNameDesc)
        popupMenu.menu.add(0, ID_SORT_BY_VALUE, Menu.NONE, R.string.sortByValue)
        popupMenu.menu.add(0, ID_SORT_BY_VALUE_DESC, Menu.NONE, R.string.sortByValueDesc)

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                ID_SORT_BY_NAME -> sortActionListener?.invoke(Navigator.TypeOfSort.BY_NAME)
                ID_SORT_BY_NAME_DESC -> sortActionListener?.invoke(Navigator.TypeOfSort.BY_NAME_DESC)
                ID_SORT_BY_VALUE -> sortActionListener?.invoke(Navigator.TypeOfSort.BY_VALUE)
                ID_SORT_BY_VALUE_DESC -> sortActionListener?.invoke(Navigator.TypeOfSort.BY_VALUE_DESC)
            }
            true
        }
        popupMenu.show()
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(tag)
            .replace(R.id.fragment_container, fragment, tag)
            .commit()
    }

    private fun openPopularCurrenciesFragment() {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragment_container,
                PopularCurrenciesFragment.newInstance(),
                PopularCurrenciesFragment.TAG
            )
            .commit()
    }
}