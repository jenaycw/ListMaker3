package com.raywenderlich.listmaker2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

var listTitles = arrayOf("Shopping", "Chores","Android Tutorials" )

class ListSelectionRecyclerViewAdapter (private val lists : ArrayList<TaskList>):
RecyclerView.Adapter<ListSelectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.list_selection_view_holder,
           parent,
           false)

        return ListSelectionViewHolder(view)
    }
fun addList(list:TaskList){
    lists.add(list)
    notifyItemInserted(lists.size-1)
}
    override fun getItemCount(): Int {
       // return listTitles.size
        return listTitles.size
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.listPosition.text = (position + 1).toString()
        //changed from holder.listTitle.text=listTitles[position]
        holder.listTitle.text = listTitles.get(position)
    }
}