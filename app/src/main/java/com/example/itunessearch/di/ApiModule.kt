package com.example.itunessearch.di

import com.example.itunessearch.model.SearchItemApi
import com.example.itunessearch.model.SearchItemApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class ApiModule {

    companion object {
        const val BASE_URL = "https://itunes.apple.com/"
    }

    @Provides
    fun provideSearchItemApi(): SearchItemApi {  //it will inject the api where we want
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(SearchItemApi::class.java)
    }

    @Provides
    open fun provideSearchItemApiService(): SearchItemApiService {
        return SearchItemApiService()
    }
}