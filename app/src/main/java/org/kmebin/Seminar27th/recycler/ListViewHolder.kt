package org.kmebin.Seminar27th.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.kmebin.Seminar27th.R
import org.kmebin.Seminar27th.data.ListData

class ListViewHolder (itemView : View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
    private val firstName : TextView = itemView.findViewById(R.id.tv_first_name)
    private val email : TextView = itemView.findViewById(R.id.tv_email)
    val avatar : ImageView = itemView.findViewById(R.id.iv_list)

    fun onBind(listData : ListData){
        firstName.text = listData.firstName
        email.text = listData.email
        Glide.with(itemView).load(listData.avatar).into(avatar)
    }
}