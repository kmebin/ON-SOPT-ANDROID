package org.kmebin.Seminar27th.recycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import org.kmebin.Seminar27th.ProfileDetailActivity
import org.kmebin.Seminar27th.R

class ProfileAdapter (private val context : Context) : androidx.recyclerview.widget.RecyclerView.Adapter<ProfileViewHolder>(){
    var data = mutableListOf<ProfileData>()
    var layoutItem = R.layout.profile_item_linear

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {

        val view = LayoutInflater.from(context).inflate(layoutItem, parent, false)

        return ProfileViewHolder(view).apply {
            // item을 클릭하면 상세 화면으로 이동
            itemView.setOnClickListener {
                val curPosition : Int = adapterPosition
                val profile : ProfileData = data.get(curPosition)
                val intent = Intent(context, ProfileDetailActivity::class.java)

                intent.putExtra("profile", profile)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    fun changeLayout(layoutItem: Int) {
        this.layoutItem = layoutItem
    }
}