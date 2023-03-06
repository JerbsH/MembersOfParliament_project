package com.example.membersofparliament_project

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.membersofparliament_project.databinding.RecyclerviewItemBinding

class EveryoneAdapter(val members: List<String>):RecyclerView.Adapter<EveryoneAdapter.EveryoneViewHolder>() {
    /*
    * Jere Hippeläinen
    * 2113583
    * 6.3.2023
    *
    * Adapter for the view that lists every member without selecting a party
    */
    inner class EveryoneViewHolder(private val itemBinding: RecyclerviewItemBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bindItem(members: String){
            itemBinding.name.text = members
        }
        init {
        /*
        * Creates an OnClickListener for single item.
        * gets position of clicked item and send it to the action as an argument
        */
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                val selected = members[position]
                val action = ShowEveryoneDirections.actionShowEveryoneToSingleMember(selected)
                Log.d("test", selected)
                Navigation.findNavController(itemView).navigate(action)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EveryoneViewHolder {
        return EveryoneViewHolder(RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun getItemCount(): Int {
        return members.size
    }
    override fun onBindViewHolder(holder: EveryoneViewHolder, position: Int) {
        val item = members[position]
        holder.bindItem(item)
    }
}