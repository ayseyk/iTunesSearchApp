package com.example.itunessearch.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.itunessearch.R
import com.example.itunessearch.databinding.CustomTabBinding
import com.example.itunessearch.databinding.FragmentSearchBinding
import com.example.itunessearch.model.SearchItem
import com.example.itunessearch.viewmodel.SearchViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchOptionsForApi: Array<String>
    private lateinit var searchOptions: Array<String>
    private lateinit var viewModel: SearchViewModel
    private lateinit var entity: String
    private lateinit var term: String
    private val listAdapter = SearchItemListAdapter(arrayListOf())

    private val searchItemListDataObserver = Observer<List<SearchItem>>{ list ->
        list?.let{
            if(list.isEmpty()) {
                noItems.visibility = View.VISIBLE
            } else {
                noItems.visibility = View.GONE
                searchItemsList.visibility = View.VISIBLE
                listAdapter.updateSearchItemList(it)
            }
        }
    }

    private val loadingLiveDataObserver = Observer<Boolean> { isLoading ->
        progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
        if(isLoading){
            listError.visibility = View.GONE
            searchItemsList.visibility = View.GONE
        }
    }

    private val errorLiveDataObserver = Observer<Boolean>{ isError ->
        listError.visibility = if(isError) View.VISIBLE else View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
        createTabs()
        setTabs()
        clickTabItem(0)
        initializeEvents()
    }

    private fun initializeViews() {
        searchOptionsForApi = resources.getStringArray(R.array.searchOptionsForApi)
        entity = searchOptionsForApi[0]
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        viewModel.apply {
            searchItems.observe(viewLifecycleOwner, searchItemListDataObserver)
            loading.observe(viewLifecycleOwner, loadingLiveDataObserver)
            loadError.observe(viewLifecycleOwner, errorLiveDataObserver)
        }

        searchItemsList.adapter = listAdapter
    }
    private fun createTabs() {
        tabLayout.apply {
            for(i in 0..3) {
                addTab(tabLayout.newTab())
            }
        }
    }

    private fun setTabs() {
        var tab = CustomTabBinding.inflate(layoutInflater)
        val iconList = arrayListOf(
            R.drawable.ic_baseline_music_note_24,
            R.drawable.ic_baseline_movie_24,
            R.drawable.ic_baseline_apps_24,
            R.drawable.ic_baseline_book_24
        )
        searchOptions = resources.getStringArray(R.array.searchOptions)

        for (i in 0..3) {
            tab.apply {
                optionIcon.setImageResource(iconList[i])
                optionTopic.text = searchOptions[i]
            }
            tabLayout.getTabAt(i)!!.customView = tab.root
            tab = CustomTabBinding.inflate(layoutInflater)
        }
    }

    fun clickTabItem(index: Int) {
        entity = searchOptionsForApi[index]
        refreshItemList()
    }

    private fun initializeEvents() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                clickTabItem(tab!!.position)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        refreshLayout.setOnRefreshListener {
            refreshItemList()
        }

        search_bar.doAfterTextChanged {
            refreshItemList()
        }
    }

    private fun refreshItemList() {
        term = binding.searchBar.text.toString()
        viewModel.refresh(term, entity)
        refreshLayout.isRefreshing = false
    }
}
