package org.kmebin.Seminar27th.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import kotlinx.android.synthetic.main.fragment_recycler.*
import org.kmebin.Seminar27th.R
import org.kmebin.Seminar27th.recycler.ProfileAdapter
import org.kmebin.Seminar27th.recycler.ProfileData
import org.kmebin.Seminar27th.util.itemTouchHelper

class ProfileFragment : Fragment() {

    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recycler, container, false)

        setHasOptionsMenu(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        profileAdapter = ProfileAdapter(view.context)

        rv.adapter = profileAdapter
        rv.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            view.context,
            androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
            false
        )

        profileAdapter.data = mutableListOf(
            ProfileData("이름", "김희빈", "안녕하세요. 김희빈입니다."),
            ProfileData("나이", "23", "1998년 5월 31일생입니다."),
            ProfileData("파트", "안드로이드", "안드로이드 파트 YB입니다."),
            ProfileData("Github", "www.github.com/kmebin", "repository : SOPT-27th-Android"),
            ProfileData("Blog", "blowhui.tistory.com", "J가 되고 싶은 P의 개발 블로그"),
            ProfileData("Sopt", "www.sopt.org", "SHOUT OUR PASSION TOGETHER! 27기 ON SOPT")
        )

        profileAdapter.notifyDataSetChanged()

        // ItemTouchHelper로 아이템의 이동 및 삭제
        val helper : ItemTouchHelper = itemTouchHelper(profileAdapter)
        helper.attachToRecyclerView(rv)

        super.onViewCreated(view, savedInstanceState)
    }

    // Option Menu로 RecyclerView의 Layout 변경
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_layout, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.linear -> {
                profileAdapter.changeLayout(R.layout.profile_item_linear)
                rv.apply {
                    rv.adapter = profileAdapter
                    rv.layoutManager =
                        androidx.recyclerview.widget.LinearLayoutManager(view?.context)
                }
                return true
            }
            R.id.grid -> {
                profileAdapter.changeLayout(R.layout.profile_item_grid)
                rv.apply {
                    rv.adapter = profileAdapter
                    rv.layoutManager =
                        androidx.recyclerview.widget.GridLayoutManager(view?.context, 2)
                }
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}