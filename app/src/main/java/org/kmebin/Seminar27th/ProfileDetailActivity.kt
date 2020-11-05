package org.kmebin.Seminar27th

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile_detail.*

class ProfileDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)

        tv_detailTitle.text = intent.getStringExtra("title")
        tv_detailSub.text = intent.getStringExtra("subTitle")
        tv_contents.text = intent.getStringExtra("contents")
    }
}