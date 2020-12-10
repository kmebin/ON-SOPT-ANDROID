package org.kmebin.Seminar27th.recycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import org.kmebin.Seminar27th.ProfileDetailActivity
import org.kmebin.Seminar27th.R
import org.kmebin.Seminar27th.data.ListData

class ListAdapter (private val context : Context) : androidx.recyclerview.widget.RecyclerView.Adapter<ListViewHolder>(){
    var data = mutableListOf<ListData>()
    var layoutItem = R.layout.profile_item_linear

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        val view = LayoutInflater.from(context).inflate(layoutItem, parent, false)

        return ListViewHolder(view).apply {
            // item을 클릭하면 상세 화면으로 이동
            itemView.setOnClickListener {
                val curPosition : Int = adapterPosition
                val profile : ListData = data.get(curPosition)
                val intent = Intent(context, ProfileDetailActivity::class.java)

                intent.putExtra("profile", profile)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    fun changeLayout(layoutItem: Int) {
        this.layoutItem = layoutItem
    }
}