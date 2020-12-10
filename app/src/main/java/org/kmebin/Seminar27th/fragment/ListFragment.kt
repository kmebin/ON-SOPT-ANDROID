package org.kmebin.Seminar27th.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import okhttp3.ResponseBody
import org.json.JSONObject
import org.kmebin.Seminar27th.R
import org.kmebin.Seminar27th.api.SoptServiceImpl
import org.kmebin.Seminar27th.data.ListData
import org.kmebin.Seminar27th.data.ResponseDummyData
import org.kmebin.Seminar27th.recycler.ListAdapter
import org.kmebin.Seminar27th.util.itemTouchHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment : Fragment() {

    private lateinit var listAdapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        setHasOptionsMenu(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        listAdapter = ListAdapter(view.context)

        rv.adapter = listAdapter
        rv.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        // 더미데이터 통신
        val call: Call<ResponseDummyData> = SoptServiceImpl.dummyService.getDummy(page = 2)
        call.enqueue(object: Callback<ResponseDummyData> {
            override fun onFailure(call: Call<ResponseDummyData>, t: Throwable) {
            }

            override fun onResponse(
                    call: Call<ResponseDummyData>,
                    response: Response<ResponseDummyData>
            ) {
                response.takeIf{ it.isSuccessful }
                    ?.body()
                    ?.let{
                        listAdapter.data = mutableListOf(
                                ListData(
                                        it.data[0].first_name,it.data[0].email,it.data[0].avatar
                                ),
                                ListData(
                                        it.data[1].first_name,it.data[1].email,it.data[1].avatar
                                ),
                                ListData(
                                        it.data[2].first_name,it.data[2].email,it.data[2].avatar
                                ),
                                ListData(
                                        it.data[3].first_name,it.data[3].email,it.data[3].avatar
                                ),
                                ListData(
                                        it.data[4].first_name,it.data[4].email,it.data[4].avatar
                                ),
                                ListData(
                                        it.data[5].first_name,it.data[5].email,it.data[5].avatar
                                )
                        )
//                        for (i in 0 until it.data.size){
//                            listAdapter.data = mutableListOf(
//                                ListData(it.data[i].first_name, it.data[i].email, it.data[i].avatar)
//                            )
//                        }
                        listAdapter.notifyDataSetChanged()
                    } ?: showError(response.errorBody())
            }

            private fun showError(error: ResponseBody?) {
                val e = error ?: return
                val ob = JSONObject(e.string())
                Toast.makeText(view.context, ob.getString("message"),Toast.LENGTH_SHORT).show()
            }
        })

//        profileAdapter.data = mutableListOf(
//            ProfileData("이름", "김희빈", "안녕하세요. 김희빈입니다."),
//            ProfileData("나이", "23", "1998년 5월 31일생입니다."),
//            ProfileData("파트", "안드로이드", "안드로이드 파트 YB입니다."),
//            ProfileData("Github", "www.github.com/kmebin", "repository : SOPT-27th-Android"),
//            ProfileData("Blog", "blowhui.tistory.com", "J가 되고 싶은 P의 개발 블로그"),
//            ProfileData("Sopt", "www.sopt.org", "SHOUT OUR PASSION TOGETHER! 27기 ON SOPT")
//        )
//
//        profileAdapter.notifyDataSetChanged()

        // ItemTouchHelper로 아이템의 이동 및 삭제
        val helper : ItemTouchHelper = itemTouchHelper(listAdapter)
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
                listAdapter.changeLayout(R.layout.profile_item_linear)
                rv.apply {
                    rv.adapter = listAdapter
                    rv.layoutManager =
                        androidx.recyclerview.widget.LinearLayoutManager(view?.context)
                }
                return true
            }
            R.id.grid -> {
                listAdapter.changeLayout(R.layout.profile_item_grid)
                rv.apply {
                    rv.adapter = listAdapter
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