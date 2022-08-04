package com.example.itunessearch.model

import java.lang.reflect.Array

data class JsonObject(
    var resultCount: Int,
    var results : List<SearchItem>
)

