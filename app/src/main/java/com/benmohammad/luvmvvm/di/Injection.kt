package com.benmohammad.luvmvvm.di

import androidx.lifecycle.ViewModelProvider
import com.benmohammad.luvmvvm.model.MuseumDataSource
import com.benmohammad.luvmvvm.model.MuseumRepository
import com.benmohammad.luvmvvm.viewmodel.ViewModelFactory

object Injection {

    private val museumDataSource: MuseumDataSource = MuseumRepository()
    private val museumViewModelFactory = ViewModelFactory(museumDataSource)

    fun provideRepository(): MuseumDataSource{
        return museumDataSource
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return museumViewModelFactory
    }
}