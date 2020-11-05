package org.kmebin.Seminar27th

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.kmebin.Seminar27th.util.addViewPagerListener
import org.kmebin.Seminar27th.util.setBottomNavigationListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vp_main.adapter = MainPagerAdapter(supportFragmentManager)
    }

    override fun onStart() {
        super.onStart()

        vp_main.addViewPagerListener(bottom_navi)
        bottom_navi.setBottomNavigationListener(vp_main)
    }
}