package org.kmebin.Seminar27th.util

import androidx.recyclerview.widget.ItemTouchHelper
import org.kmebin.Seminar27th.recycler.ListAdapter

fun itemTouchHelper(adapter : ListAdapter) : ItemTouchHelper {

    val helper = ItemTouchHelper(object :
        ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
            ItemTouchHelper.START){

        override fun onMove(
            recyclerView: androidx.recyclerview.widget.RecyclerView,
            from: androidx.recyclerview.widget.RecyclerView.ViewHolder,
            to: androidx.recyclerview.widget.RecyclerView.ViewHolder
        ): Boolean {
            val fromPosition = from.adapterPosition
            val toPosition = to.adapterPosition
            adapter.notifyItemMoved(fromPosition, toPosition)
            return false
        }

        override fun onSwiped(viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder, direction: Int) {
            adapter.data.removeAt(viewHolder.adapterPosition)
            adapter.notifyItemRemoved(viewHolder.adapterPosition)
        }
    })

    return helper
}