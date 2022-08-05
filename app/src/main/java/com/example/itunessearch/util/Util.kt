package com.example.itunessearch.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.itunessearch.R

class Util {
    companion object {
        const val BASE_URL = "https://itunes.apple.com/"
    }
}

fun ImageView.loadImage(uri: String?) {
    val options = RequestOptions()
        .error(R.drawable.image_not_found)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}

