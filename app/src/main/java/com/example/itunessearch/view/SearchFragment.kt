package com.example.itunessearch.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.itunessearch.R
import com.example.itunessearch.databinding.CustomTabBinding
import com.example.itunessearch.databinding.FragmentSearchBinding
import com.google.android.material.tabs.TabLayout


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createTabs()
        setTabs()
        setTabEvent()
        clickTabItem(0)
    }

    private fun createTabs() {
        binding.tabLayout.apply {
            this.addTab(binding.tabLayout.newTab())
            this.addTab(binding.tabLayout.newTab())
            this.addTab(binding.tabLayout.newTab())
            this.addTab(binding.tabLayout.newTab())
        }
    }

    private fun setTabs() {

        var tab = CustomTabBinding.inflate(layoutInflater)
        val iconList = arrayListOf(R.drawable.ic_baseline_music_note_24, R.drawable
            .ic_baseline_movie_24, R.drawable.ic_baseline_apps_24, R.drawable.ic_baseline_book_24)
        val searchOptions = resources.getStringArray(R.array.searchOptions)

        for(i in 0..3) {
            tab.apply {
                this.optionIcon.setImageResource(iconList[i])
                this.optionTopic.text = searchOptions[i]
            }
            binding.tabLayout.getTabAt(i)!!.customView = tab.root
            tab = CustomTabBinding.inflate(layoutInflater)
        }
    }

    private fun setTabEvent() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                clickTabItem(tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    fun clickTabItem(index : Int)
    {
        when (index)
        {
            0 ->
                Toast.makeText(requireContext(), "1.tab", Toast.LENGTH_SHORT).show()
            1 ->
                Toast.makeText(requireContext(), "2.tab", Toast.LENGTH_SHORT).show()
        }

    }
}
