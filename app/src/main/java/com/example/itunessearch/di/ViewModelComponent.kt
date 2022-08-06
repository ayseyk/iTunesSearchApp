package com.example.itunessearch.di

import com.example.itunessearch.viewmodel.SearchViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ViewModelComponent {

    fun inject(viewModel: SearchViewModel)
}