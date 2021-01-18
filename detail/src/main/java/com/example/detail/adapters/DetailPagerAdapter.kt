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

class DetailPagerAdapter(val fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragmentDescription = DetailDescriptionFragment()
        val fragmentActors = DetailActorsFragment()


        fragmentDescription.arguments = Bundle().apply {
            putInt("Description", 1)
        }

        fragmentActors.arguments = Bundle().apply {
            putInt("Actors", 2)
        }

        return if (fragment == fragmentActors) {
            fragment
        } else
            fragmentDescription
    }

}


