package com.example.itunessearch.model

import com.google.gson.annotations.SerializedName

data class SearchItem(
    @SerializedName("artworkUrl100")
    val imageUrl: String?,
    @SerializedName("trackCensoredName")
    val name: String?,
    @SerializedName("collectionPrice")
    val price: Double?,
    val releaseDate: String?,
    val longDescription: String?
)