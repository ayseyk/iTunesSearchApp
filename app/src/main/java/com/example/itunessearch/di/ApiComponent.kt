package com.example.itunessearch.di

import com.example.itunessearch.model.SearchItemApiService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: SearchItemApiService)
}