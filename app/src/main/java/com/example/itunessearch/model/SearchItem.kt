package com.example.itunessearch.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchItem(
    @SerializedName("artworkUrl100")
    val imageUrl: String?,
    @SerializedName("trackCensoredName")
    val name: String?,
    @SerializedName("collectionPrice")
    val price: Double?,
    val releaseDate: String?,
    @SerializedName("description")
    val longDescription: String?
): Parcelable