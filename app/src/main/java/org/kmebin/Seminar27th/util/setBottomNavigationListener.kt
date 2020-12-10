package org.kmebin.Seminar27th.util

import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.kmebin.Seminar27th.R

fun BottomNavigationView.setBottomNavigationListener(viewPager: ViewPager) {
    this.setOnNavigationItemSelectedListener {
        when(it.itemId) {
            R.id.menu_profile -> viewPager.currentItem = 0
            R.id.menu_list -> viewPager.currentItem = 1
            R.id.menu_search -> viewPager.currentItem = 2
        }
        true
    }
}