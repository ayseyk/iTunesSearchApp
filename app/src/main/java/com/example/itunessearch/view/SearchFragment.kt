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
import com.example.itunessearch.util.Util.Companion.TEXT_LENGTH
import com.example.itunessearch.viewmodel.SearchViewModel
import com.google.android.material.tabs.TabLayout

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchOptionsForApi: Array<String>
    private lateinit var searchOptions: Array<String>
    private lateinit var viewModel: SearchViewModel
    private var entity: String? = null
    private var term: String? = null
    private val listAdapter = SearchItemListAdapter(arrayListOf())

    private val searchItemListDataObserver = Observer<List<SearchItem>> { list ->
        binding.isEmpty = list.isEmpty()
        if (list.isNotEmpty()) listAdapter.updateSearchItemList(list)
    }

    private val loadingLiveDataObserver = Observer<Boolean> { isLoading ->
        binding.isLoading = isLoading
    }

    private val errorLiveDataObserver = Observer<Boolean> { isError ->
        binding.isError = isError
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSearchBinding.inflate(layoutInflater)
        createTabs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
        initializeEvents()
    }

    private fun initializeViews() {
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        viewModel.apply {
            searchItems.observe(viewLifecycleOwner, searchItemListDataObserver)
            loading.observe(viewLifecycleOwner, loadingLiveDataObserver)
            loadError.observe(viewLifecycleOwner, errorLiveDataObserver)
        }
        binding.searchItemsList.adapter = listAdapter
    }

    private fun createTabs() {
        searchOptionsForApi = resources.getStringArray(R.array.search_options_for_api)
        entity = searchOptionsForApi[0]

        var tab = CustomTabBinding.inflate(layoutInflater)
        val iconList = arrayListOf(
            R.drawable.ic_baseline_music_note_24,
            R.drawable.ic_baseline_movie_24,
            R.drawable.ic_baseline_apps_24,
            R.drawable.ic_baseline_book_24
        )

        binding.tabLayout.apply {
            for (i in 0..3) {
                addTab(binding.tabLayout.newTab())
            }
        }

        searchOptions = resources.getStringArray(R.array.search_options)

        for (i in 0..3) {
            tab.apply {
                optionIcon.setImageResource(iconList[i])
                optionTopic.text = searchOptions[i]
            }
            binding.tabLayout.getTabAt(i)?.customView = tab.root
            tab = CustomTabBinding.inflate(layoutInflater)
        }
    }

    fun clickTabItem(index: Int) {
        entity = searchOptionsForApi[index]
        refreshItemList()
    }

    private fun initializeEvents() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    clickTabItem(tab.position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        binding.refreshLayout.setOnRefreshListener {
            refreshItemList()
        }

        binding.searchBar.doAfterTextChanged {
            refreshItemList()
        }
    }

    private fun refreshItemList() {
        binding.isInputShort = binding.searchBar.text.length <= TEXT_LENGTH
        if (binding.searchBar.text.length <= TEXT_LENGTH) {
            listAdapter.clearList()
        } else {
            term = binding.searchBar.text.toString()
            binding.refreshLayout.isRefreshing = false
            if (term != null && entity != null)
                viewModel.refresh(term!!, entity!!)
        }
    }
}
