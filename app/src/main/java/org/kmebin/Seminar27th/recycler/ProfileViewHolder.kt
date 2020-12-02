package org.kmebin.Seminar27th.recycler

import android.view.View
import android.widget.TextView
import org.kmebin.Seminar27th.R
import org.kmebin.Seminar27th.data.ProfileData

class ProfileViewHolder (itemView : View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
    private val title : TextView = itemView.findViewById(R.id.tv_title)
    private val subTitle : TextView = itemView.findViewById(R.id.tv_subtitle)

    fun onBind(data : ProfileData){
        title.text = data.title
        subTitle.text = data.subTitle
    }
}