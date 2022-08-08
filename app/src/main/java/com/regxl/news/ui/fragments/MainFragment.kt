package com.regxl.news.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.regxl.news.R
import com.regxl.news.adapters.ViewPagerAdapter
import com.regxl.news.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager2 = binding.viewPager2
        viewPager2.adapter = viewPagerAdapter
        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = when (position) {
                1 -> "Business"
                2 -> "Entertainment"
                3 -> "Health"
                4 -> "Science"
                5 -> "Sports"
                6 -> "Technology"
                else -> {
                    "General"
                }
            }

            tab.icon = when (position) {
                1 -> resources.getDrawable(R.drawable.business, context?.theme)
                2 -> resources.getDrawable(R.drawable.entertainment, context?.theme)
                3 -> resources.getDrawable(R.drawable.health, context?.theme)
                4 -> resources.getDrawable(R.drawable.science, context?.theme)
                5 -> resources.getDrawable(R.drawable.sports, context?.theme)
                6 -> resources.getDrawable(R.drawable.technology, context?.theme)
                else -> {
                    resources.getDrawable(R.drawable.general, context?.theme)
                }
            }
        }.attach()
    }

}