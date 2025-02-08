package com.samarbaeffruslan.detail.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.samarbaeffruslan.detail.presentation.DetailActorsPresentation.DetailActorsFragment
import com.samarbaeffruslan.detail.presentation.DetailDescriptionPresentation.DetailDescriptionFragment

const val COUNT_TAB = 2

class DetailPagerAdapter(val fragment: Fragment) : FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = COUNT_TAB

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 ->  DetailActorsFragment.newInstance()
            1 -> DetailDescriptionFragment.newInstance()
            else -> DetailDescriptionFragment.newInstance()
        }
    }

}


