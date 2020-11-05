package org.kmebin.Seminar27th

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import org.kmebin.Seminar27th.fragment.HomeFragment
import org.kmebin.Seminar27th.fragment.ProfileFragment
import org.kmebin.Seminar27th.fragment.SettingsFragment

class MainPagerAdapter(fm: FragmentManager)
    : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var fragments = listOf<Fragment>()

    override fun getItem(position: Int): Fragment = when(position){
        0 -> HomeFragment()
        1 -> ProfileFragment()
        2 -> SettingsFragment()
        else -> throw IllegalStateException("Unexpected position $position")
    }

    override fun getCount(): Int = 3
}