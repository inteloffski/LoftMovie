package com.example.detail.adapters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.detail.presentation.DetailActorsPresentation.DetailActorsFragment
import com.example.detail.presentation.DetailDescriptionPresentation.DetailDescriptionFragment

const val COUNT_TAB = 2

class DetailPagerAdapter(val fragment: Fragment) : FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = COUNT_TAB

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> DetailDescriptionFragment.newInstance()
            1 -> DetailActorsFragment.newInstance()
            else -> DetailDescriptionFragment.newInstance()
        }
    }

}


