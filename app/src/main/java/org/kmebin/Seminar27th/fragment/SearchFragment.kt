package org.kmebin.Seminar27th.fragment

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*
import okhttp3.ResponseBody
import org.json.JSONObject
import org.kmebin.Seminar27th.R
import org.kmebin.Seminar27th.api.SoptServiceImpl
import org.kmebin.Seminar27th.data.ResponseKakaoData
import org.kmebin.Seminar27th.data.SearchData
import org.kmebin.Seminar27th.recycler.SearchAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        setHasOptionsMenu(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchAdapter = SearchAdapter(view.context)

        rv_search.adapter = searchAdapter
        rv_search.layoutManager = LinearLayoutManager(view.context)

        // 카카오 웹 검색 통신
        btn_search.setOnClickListener {
            val call: Call<ResponseKakaoData> = SoptServiceImpl.kakaoService.getWebSearch(et_search.text.toString())
            call.enqueue(object: Callback<ResponseKakaoData> {
                override fun onFailure(call: Call<ResponseKakaoData>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<ResponseKakaoData>,
                    response: Response<ResponseKakaoData>
                ) {
                    response.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let {
                            for (i in it.documents.indices){
                                searchAdapter.data.add(
                                    SearchData(stripHtml(it.documents[i].title), stripHtml(it.documents[i].contents), it.documents[i].datetime.slice(IntRange(0, 9)))
                                )
                            }
                            searchAdapter.notifyDataSetChanged()
                        } ?: showError(response.errorBody())
                }

                private fun showError(error: ResponseBody?) {
                    val e = error ?: return
                    val ob = JSONObject(e.string())
                    Toast.makeText(view.context, ob.getString("message"),Toast.LENGTH_SHORT).show()
                }

                // Html Tag 제거
                fun stripHtml(html: String) : String {
                    return Html.fromHtml(html).toString()
                }
            })
        }

        super.onViewCreated(view, savedInstanceState)
    }
}