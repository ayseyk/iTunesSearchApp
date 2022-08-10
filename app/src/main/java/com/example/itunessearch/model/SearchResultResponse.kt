package com.example.itunessearch.model

data class SearchResultResponse(
    val resultCount: Int?,
    val results : List<SearchItem>?
)