package com.example.itunessearch.model

import com.example.itunessearch.util.Util.Companion.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SearchItemApiService() {

    private var itemApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(SearchItemApi::class.java)

    fun getSearchItems(term: String, entity: String) : Single<JsonObject> {
        return itemApi.getSearchItems(term, entity)
    }
}