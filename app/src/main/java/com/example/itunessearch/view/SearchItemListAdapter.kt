package com.example.itunessearch.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.itunessearch.R
import com.example.itunessearch.databinding.ItemSearchBinding
import com.example.itunessearch.model.SearchItem
import com.example.itunessearch.util.getDate

class SearchItemListAdapter(private val searchItemList: ArrayList<SearchItem>) : RecyclerView
.Adapter<SearchItemListAdapter.SearchItemViewHolder>(), SearchItemClickListener {

    class SearchItemViewHolder(var view: ItemSearchBinding) : RecyclerView.ViewHolder(view.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateSearchItemList(newSearchItemList: List<SearchItem>) {
        searchItemList.clear()
        searchItemList.addAll(newSearchItemList)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearList() {
        searchItemList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemSearchBinding>(inflater, R.layout.item_search, parent, false)
        return SearchItemViewHolder(view)
    }

    override fun getItemCount(): Int = searchItemList.size

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        holder.view.searchItem = searchItemList[position]
        holder.view.listener = this
        holder.view.releaseDate.text = getDate(searchItemList[position], holder.itemView)

        holder.view.searchItemLayout.setOnClickListener {
            val action = SearchFragmentDirections.searchToDetail(searchItemList[position])
            Navigation.findNavController(it).navigate(action)
        }
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