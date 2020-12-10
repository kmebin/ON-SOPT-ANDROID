package org.kmebin.Seminar27th.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.kmebin.Seminar27th.R
import org.kmebin.Seminar27th.data.SearchData

class SearchAdapter (private val context: Context) : RecyclerView.Adapter<SearchViewHolder>(){
	var data = mutableListOf<SearchData>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
		val view = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false)

		return SearchViewHolder(view)
	}

	override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
		holder.onBind(data[position])
	}

	override fun getItemCount(): Int = data.size
}