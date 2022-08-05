package com.example.itunessearch.view

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.itunessearch.R
import com.example.itunessearch.model.SearchItem
import com.example.itunessearch.util.loadImage
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.android.synthetic.main.item_search.view.*
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlin.coroutines.coroutineContext


class SearchItemListAdapter(private val searchItemList: ArrayList<SearchItem>) : RecyclerView
.Adapter<SearchItemListAdapter.SearchItemViewHolder>(), SearchItemClickListener {

    class SearchItemViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    fun updateSearchItemList(newSearchItemList: List<SearchItem>) {
        searchItemList.clear()
        searchItemList.addAll(newSearchItemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return SearchItemViewHolder(view)
    }

    override fun getItemCount(): Int = searchItemList.size

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {

        holder.view.itemImage.loadImage(searchItemList[position].imageUrl)

        holder.apply {
            view.itemName.text = searchItemList[position].name
            view.itemPrice.text = searchItemList[position].price.toString()
            view.releaseDate.text = searchItemList[position].releaseDate
        }
        /*holder.view.searchItemsList.setOnClickListener {
            onClick(holder.view)
        }*/
    }

    override fun onClick(v: View) {
        for (item in searchItemList) {
            if (v.tag == item.name) {
                val action = SearchFragmentDirections.searchToDetail(item)
                Navigation.findNavController(v).navigate(action)
            }
        }
    }
}