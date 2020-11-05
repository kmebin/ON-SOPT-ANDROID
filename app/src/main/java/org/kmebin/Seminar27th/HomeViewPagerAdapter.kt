package org.kmebin.Seminar27th

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import org.kmebin.Seminar27th.fragment.HomeInfoFragment
import org.kmebin.Seminar27th.fragment.HomeOtherFragment

class HomeViewPagerAdapter(fm: FragmentManager)
    : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getItem(position: Int): Fragment = when(position){
        0 -> HomeInfoFragment()
        1 -> HomeOtherFragment()
        else -> throw IllegalStateException("Unexpected position $position")
    }

    override fun getCount(): Int = 2
}