package com.regxl.news.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.regxl.news.ui.fragments.*

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 7

    override fun createFragment(position: Int): Fragment {

        val frag = when(position){
            1-> BusinessFragment()
            2-> EntertainmentFragment()
            3-> HealthFragment()
            4-> ScienceFragment()
            5-> SportsFragment()
            6-> TechnologyFragment()
            else -> {
                GeneralFragment()
            }
        }

        return frag
    }
}