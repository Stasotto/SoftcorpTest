package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.CurrencyRepositoryImpl
import com.example.data.storage.ApiStorage
import com.example.data.storage.ApiStorageImpl
import com.example.data.storage.DatabaseStorage
import com.example.data.storage.DatabaseStorageImpl
import com.example.data.storage.retrofit.CurrencyDataApi
import com.example.data.storage.retrofit.RetrofitClient
import com.example.data.storage.room.AppDatabase
import com.example.data.storage.room.CurrencyDao
import com.example.domain.repository.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DataBindModule::class])
class DataModule {

    @Provides
    fun provideCurrencyRepositoryImpl(api: ApiStorage, database: DatabaseStorage) =
        CurrencyRepositoryImpl(api, database)

    @Provides
    fun provideApiStorageImpl(api: CurrencyDataApi) = ApiStorageImpl(api)

    @Provides
    fun provideDatabaseStorageImpl(dao: CurrencyDao) = DatabaseStorageImpl(dao)

    @Provides
    fun provideRoomDatabase(context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "currencies"
    ).build()

    @Provides
    fun provideCurrencyDao(database: AppDatabase) = database.getCurrencyDao()

    @Provides
    fun provideCurrencyDataApi() = RetrofitClient.getCurrencyApi()
}

@Module
interface DataBindModule {
    @Suppress("FunctionName")
    @Binds
    fun bindCurrencyRepositoryImpl_to_CurrencyRepository(
        repository: CurrencyRepositoryImpl
    ): CurrencyRepository

    @Suppress("FunctionName")
    @Binds
    fun bindApiStorageImpl_to_ApiStorage(api: ApiStorageImpl): ApiStorage

    @Suppress("FunctionName")
    @Binds
    fun bindDatabaseStorageImpl_to_DatabaseStorage(database: DatabaseStorageImpl): DatabaseStorage

}