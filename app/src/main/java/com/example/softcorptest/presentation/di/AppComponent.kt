package com.example.softcorptest.presentation.di

import android.content.Context
import com.example.softcorptest.presentation.fragments.FavoritesCurrenciesFragment
import com.example.softcorptest.presentation.fragments.PopularCurrenciesFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(fragment: PopularCurrenciesFragment)

    fun inject(fragment: FavoritesCurrenciesFragment)
}