package com.example.itunessearch.model

import com.example.itunessearch.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class SearchItemApiService {

    @Inject
    lateinit var itemApi: SearchItemApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getSearchItems(term: String, entity: String): Single<JsonObject> {
        return itemApi.getSearchItems(term, entity)
    }
}