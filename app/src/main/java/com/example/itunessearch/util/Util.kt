package com.example.itunessearch.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.itunessearch.R
import com.example.itunessearch.model.SearchItem

class Util {
    companion object {
        const val TEXT_LENGTH = 2
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

fun getDate(searchItem: SearchItem, view: View): String {
    val dateList = searchItem.releaseDate?.split("-", "T")
    return view.resources.getString(
        R.string.date_format,
        dateList?.get(2), dateList?.get(1), dateList?.get(0)
    )
}
@BindingAdapter("android:imageUrl")
fun loadImageFromUrl(view: ImageView, url: String?) {
    view.loadImage(url)
}

//"1959-07-27T07:00:00Z"
/*
@RequiresApi(Build.VERSION_CODES.O) //alternative date formatter fun
fun getFormattedDate(view: TextView, date: String?) = LocalDateTime.parse(
    date, DateTimeFormatter.ISO_DATE_TIME
).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
*/
