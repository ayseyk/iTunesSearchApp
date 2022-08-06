package com.example.itunessearch.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.itunessearch.R
import com.example.itunessearch.databinding.FragmentDetailBinding
import com.example.itunessearch.model.SearchItem

class DetailFragment : Fragment() {

    private var searchItem: SearchItem? = null
    private lateinit var dataBinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            searchItem = DetailFragmentArgs.fromBundle(it).searchItem
        }
        dataBinding.searchItem = searchItem
    }
}