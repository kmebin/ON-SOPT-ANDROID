package org.kmebin.Seminar27th.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.kmebin.Seminar27th.HomeViewPagerAdapter
import org.kmebin.Seminar27th.R

class HomeFragment : Fragment() {

    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewPagerAdapter = HomeViewPagerAdapter(childFragmentManager)
        vp_home.adapter = homeViewPagerAdapter

        tl_home.setupWithViewPager(vp_home)
        tl_home.apply {
            getTabAt(0)?.text = "INFO"
            getTabAt(1)?.text = "OTHER"
        }
        super.onViewCreated(view, savedInstanceState)
    }

}