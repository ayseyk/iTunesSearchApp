package com.example.itunessearch.model

data class SearchResultResponse(
    var resultCount: Int?,
    var results : List<SearchItem>?
)

