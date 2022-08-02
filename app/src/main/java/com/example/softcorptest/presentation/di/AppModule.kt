package com.example.softcorptest.presentation.di

import com.example.data.di.DataModule
import com.example.domain.di.DomainModule
import dagger.Module

@Module(includes = [DomainModule::class, DataModule::class])
class AppModule