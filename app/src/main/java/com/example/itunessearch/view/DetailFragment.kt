package com.example.itunessearch.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.itunessearch.R
import com.example.itunessearch.model.SearchItem
import com.example.itunessearch.util.loadImage
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : Fragment() {

    private var searchItem: SearchItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            searchItem = DetailFragmentArgs.fromBundle(it).searchItem
        }

        itemImageDetail.loadImage(searchItem?.imageUrl)
        name.text = searchItem?.name
        longDescription.text = searchItem?.longDescription
        println("bu uzun açıklama: ${searchItem?.longDescription}")
        if (searchItem?.price != null) price.text =
            resources.getString(R.string.price_format, searchItem?.price.toString())


    }


}