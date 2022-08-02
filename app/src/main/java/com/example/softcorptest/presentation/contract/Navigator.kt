package com.example.softcorptest.presentation.contract

import androidx.fragment.app.Fragment
import com.example.softcorptest.presentation.SortByListener


val Fragment.navigator: Navigator
    get() = requireActivity() as Navigator

interface Navigator {

    enum class TypeOfSort {
        BY_NAME,
        BY_NAME_DESC,
        BY_VALUE,
        BY_VALUE_DESC
    }

    fun sortBy(listener: SortByListener)
}