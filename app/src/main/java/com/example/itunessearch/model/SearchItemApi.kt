package com.example.itunessearch.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchItemApi {

    @GET("search?&")
    fun getSearchItems(
        @Query("term") term: String,
        @Query("entity") entity: String
    ): Single<SearchResultResponse>
}
