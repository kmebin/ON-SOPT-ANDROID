package org.kmebin.Seminar27th.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.kmebin.Seminar27th.R
import org.kmebin.Seminar27th.data.SearchData

class SearchViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

	private val title : TextView = itemView.findViewById(R.id.tv_search_title)
	private val contents : TextView = itemView.findViewById(R.id.tv_search_contents)
	private val datetime : TextView = itemView.findViewById(R.id.tv_search_date)

	fun onBind(data : SearchData){
		title.text = data.title
		contents.text = data.contents
		datetime.text = data.datetime
	}
}